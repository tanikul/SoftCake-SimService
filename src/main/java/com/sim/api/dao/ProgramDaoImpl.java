package com.sim.api.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import com.sim.api.datatable.SearchDataTable;
import com.sim.api.mapper.ProgramRowMapper;
import com.sim.api.model.ProgramMst;
import com.sim.api.utils.DBConstants;

@Repository("programDao")
public class ProgramDaoImpl  implements ProgramDao{

	private JdbcTemplate jdbcTemplate; 
	private static final Logger LOGGER = Logger.getLogger(ProgramDaoImpl.class);

	@Autowired 
	@Qualifier("transactionManager") 
	private PlatformTransactionManager transactionManager;

	@Autowired
	private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
	}


	@Override
	public List<ProgramMst> searchProgramMstByDataTable(SearchDataTable<ProgramMst> dataTable) {

		List<ProgramMst> results = null;
		StringBuilder sql = new StringBuilder();

		try{
			sql.append("  select x.*  , ");
			sql.append("  		'N' DEFVIEWER, "); 
			sql.append("  		'N' DEFCHECKER, "); 
			sql.append("  		'N' DEFMAKER, x.PROGRAM_TYPE");
			sql.append(" 	from  ").append(DBConstants.PROGRAM).append("  x  ");
			sql.append("  	where x.ACTIVE_STATUS = 'Y' ");
			sql.append("  	order by x.group_level,x.PROGRAM_LEVEL ");

			results = jdbcTemplate.query(sql.toString(),new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					
				} 
			},new  ProgramRowMapper());
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

		return results;

	}

	@Override
	public int countSearchProgramByDataTableTotal(SearchDataTable<ProgramMst> dataTable) {
		int  result = 0;
		StringBuilder sql = new StringBuilder();
		
		try{
			
			sql.append(" select count(0)  from ").append(DBConstants.PROGRAM);
			sql.append(" where ACTIVE_STATUS = 'Y' ");

			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, Integer.class);
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

		return result;
	}

	@Override
	public int countSearchProgramByDataTableFilter(SearchDataTable<ProgramMst> dataTable) {
		return 0;
	}

	@Override
	public List<ProgramMst> searchProgramMstDefaultPriviligeByRoleId(int roldId) {
		List<ProgramMst> results = null;
		StringBuilder sql = new StringBuilder();

		try{

			sql.append("  	select x.*  ,  ");
			sql.append("  		y.VIEWER DEFVIEWER, "); 
			sql.append("  		y.CHECKER DEFCHECKER, "); 
			sql.append("  		y.MAKER  DEFMAKER, x.PROGRAM_TYPE");
			sql.append("  		from  ").append(DBConstants.PROGRAM).append("  x  ");
			sql.append("  	   LEFT JOIN ").append(DBConstants.PRIVILEGE).append(" y   ");
			sql.append("  	 ON  x.PROGRAM_ID = y.PROGRAM_ID and y.ROLE_ID = ? ");
			sql.append("  	where ");
			sql.append("  	 ACTIVE_STATUS = 'Y' ");
			sql.append("  	order by group_level,PROGRAM_LEVEL ");

			results = jdbcTemplate.query(sql.toString(),new Object[] { roldId } ,new  ProgramRowMapper());
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

		return results;
	}

}
