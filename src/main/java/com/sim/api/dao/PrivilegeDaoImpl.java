package com.sim.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sim.api.mapper.PrivilegeRowMapper;
import com.sim.api.model.PrivilegeMst;
import com.sim.api.utils.DBConstants;

@Repository("privilegeDao")
public class PrivilegeDaoImpl implements PrivilegeDao {

	private JdbcTemplate jdbcTemplate; 
	private static final Logger LOGGER = Logger.getLogger(PrivilegeDaoImpl.class);
	
	@Autowired 
    @Qualifier("transactionManager") 
    private PlatformTransactionManager transactionManager;
    
	@Autowired
	private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
	}
	
	@Override
	public void insertPrivilegeMst(PrivilegeMst privilegeMst) {
		StringBuilder sql = new StringBuilder();
		try{
			sql.append("  INSERT INTO ").append(DBConstants.PRIVILEGE); 
			sql.append("  (ROLE_ID, PROGRAM_ID, MAKER, CHECKER, VIEWER, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY)  ");
			sql.append("  VALUES ( ? , ? , ? , ? , ? , SYSDATE() , ? , SYSDATE() , ? )  ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, privilegeMst.getRoleId() );
					ps.setInt(2, privilegeMst.getProgramId() );
					ps.setString(3, YES.equals(privilegeMst.getMaker())?YES : NO );
					ps.setString(4, YES.equals(privilegeMst.getChecker())?YES : NO );
					ps.setString(5, YES.equals(privilegeMst.getViewer())?YES : NO );
					ps.setString(6, privilegeMst.getCreatedBy()!=null?privilegeMst.getCreatedBy():"SYSTEM");
					ps.setString(7, privilegeMst.getLastUpdatedBy()!=null?privilegeMst.getLastUpdatedBy():"SYSTEM");
				}
			});

		}catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
		
	}

	@Override
	public int checkComparePrivilegeMst(PrivilegeMst privilegeMst) {
			int result = 0;
			StringBuilder sql = new StringBuilder();
			try {
				sql.append(" SELECT COUNT(0) FROM  ");
				sql.append(DBConstants.PRIVILEGE);
				sql.append(" where ROLE_ID  = ? ");
				sql.append(" 	and PROGRAM_ID  = ? ");
				sql.append(" 	and VIEWER  = ? ");
				sql.append(" 	and MAKER = ? ");
				sql.append(" 	and CHECKER = ? ");
				result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { 
						privilegeMst.getRoleId(),privilegeMst.getProgramId(),
						privilegeMst.getViewer() , privilegeMst.getMaker() , privilegeMst.getChecker()
				}, Integer.class);
			} catch (Exception e) {
				LOGGER.error(e);
				throw e;
			}
			return result;
	}
	@Override
	public int checkDupplicatePrivilegeMst(PrivilegeMst privilegeMst) {
			int result = 0;
			StringBuilder sql = new StringBuilder();
			try {
				sql.append(" SELECT COUNT(0) FROM  ");
				sql.append(DBConstants.PRIVILEGE);
				sql.append(" where ROLE_ID  = ? ");
				sql.append(" 	and PROGRAM_ID  = ? ");
				result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { privilegeMst.getRoleId(),privilegeMst.getProgramId() }, Integer.class);
			} catch (Exception e) {
				LOGGER.error(e);
				throw e;
			}
			return result;
	}
	
	@Override
	public void updatePrivilegeMst(PrivilegeMst privilegeMst) {
	    try{
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ").append(DBConstants.PRIVILEGE);
	    	sql.append(" SET  VIEWER = ? ");
	    	sql.append("      , MAKER  = ? ");
	    	sql.append("      , CHECKER  = ? ");
	    	sql.append("      , LAST_UPDATED_DATE  = SYSDATE() ");
	    	sql.append("      , LAST_UPDATED_BY  = ? ");
	    	sql.append(" WHERE ROLE_ID = ?");
	    	sql.append(" and   PROGRAM_ID = ?");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setString(1, privilegeMst.getViewer());
	                ps.setString(2, privilegeMst.getMaker());
	                ps.setString(3, privilegeMst.getChecker());
	                ps.setString(4, privilegeMst.getLastUpdatedBy());
	                ps.setInt(5, privilegeMst.getRoleId());
	                ps.setInt(6, privilegeMst.getProgramId());
	            }
	        });
	    }catch (Exception e) {
        	LOGGER.error(" >>> updatePrivilegeMst Error !!! : "+e);
        	throw e;
        }
	}
	
	
	@Override
	public PrivilegeMst getPrivilegeMstByRoleIdByProgramId(int roleId, int programId) {
		PrivilegeMst result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ");
			sql.append(DBConstants.PRIVILEGE);
			sql.append(" WHERE ROLE_ID = ? ");
			sql.append(" and PROGRAM_ID =  ? ");
			result = jdbcTemplate.queryForObject(sql.toString(),  new Object[] { roleId ,programId  }, new PrivilegeRowMapper());
		} catch(EmptyResultDataAccessException  ex){
			LOGGER.error(">> getPrivilegeMstByRoleIdByProgramId ERROR :"+ ex);
		}
		return result;	
		
	}

	@Override
	public void deletePrivilegeMstByRoleIdByProgramId(int roleId, int programId) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.PRIVILEGE);
			sql.append(" where ROLE_ID = ? ");
			sql.append(" and PROGRAM_ID = ? ");
			jdbcTemplate.update(sql.toString(), new Object[] { roleId,programId });
			transactionManager.commit(status);
		}catch (Exception e) {
			transactionManager.rollback(status);
			LOGGER.error(e);
			throw e;
		}
		
	}
	@Override
	public void deletePrivilegeMstByRoleId(int roleId) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.PRIVILEGE);
			sql.append(" where ROLE_ID = ? ");
			jdbcTemplate.update(sql.toString(), new Object[] { roleId });
			transactionManager.commit(status);
		}catch (Exception e) {
			transactionManager.rollback(status);
			LOGGER.error(e);
			throw e;
		}
		
	}
	
	@Override
	public List<PrivilegeMst> getPrivilegeMstListByRoleId(int roleId) {
		List<PrivilegeMst> results = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("	select ROLE_ID , PROGRAM_ID , MAKER , CHECKER , VIEWER  ");
			sql.append("	from ").append(DBConstants.PRIVILEGE);
			sql.append("			where ROLE_ID = ? ");
			results = jdbcTemplate.query(sql.toString(),new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement) throws
				SQLException {
					preparedStatement.setInt(1, roleId);
				}
			}, new RowMapper<PrivilegeMst>() {	 
				@Override
				public PrivilegeMst mapRow(ResultSet rs, int numRow) throws SQLException {
					PrivilegeMst privilege = new PrivilegeMst();
					privilege.setRoleId(rs.getInt("ROLE_ID"));
					privilege.setProgramId(rs.getInt("PROGRAM_ID"));
					privilege.setMaker(rs.getString("MAKER"));
					privilege.setChecker(rs.getString("CHECKER"));
					privilege.setViewer(rs.getString("VIEWER"));
					return privilege;
				}

			});
		} catch(EmptyResultDataAccessException  ex){
			LOGGER.error(">> getPrivilegeMstListByRoleId ERROR :"+ ex);
		}
		return results;
	}	
}
