package com.sim.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import com.sim.api.model.Sim;
import com.sim.api.utils.DBConstants;

@Repository
public class SimDaoImpl implements SimDao {

	private JdbcTemplate jdbcTemplate; 
	private static final Logger logger = Logger.getLogger(SimDaoImpl.class);
	
	@Autowired 
    @Qualifier("transactionManager") 
    private PlatformTransactionManager transactionManager;
	
	@Autowired
	private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
	}
	
	
	@Override
	public List<Sim> SearchSim(int page, Sim sim) {
		List<Sim> result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT * FROM ").append(DBConstants.SIM);
			//sql.append(" LIMIT ").append(36).append(" OFFSET ").append((page - 1) * 36);
			result = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;/*
	            	preparedStatement.setString(i++, searchDataTable.getCorpCode());
	            	preparedStatement.setString(i++, searchDataTable.getCorpCode());
	                preparedStatement.setString(i++, searchDataTable.getCorpCode());
	            	preparedStatement.setString(i++, searchDataTable.getCorpCode());
	            	preparedStatement.setString(i++, searchDataTable.getCorpCode());*/
	            }
	        }, new RowMapper<Sim>() {
					@Override
					public Sim mapRow(ResultSet rs, int numRow) throws SQLException {
						Sim sim = new Sim();
						sim.setPeriodType(rs.getString("PERIOD_TYPE"));
						sim.setActiveStatus(rs.getString("ACTIVE_STATUS"));
						sim.setPrice(rs.getBigDecimal("PRICE"));
						sim.setSimNumber(rs.getString("SIM_NUMBER"));
						return sim;
					}
			    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}

	@Override
	public int countSimTotal(Sim sim) {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		try {
			sql.append("SELECT COUNT(0) FROM ");
			sql.append(DBConstants.SIM);
			sql.append(" WHERE CASE WHEN (? = '' || ? IS NULL) THEN 1 = 1 ELSE SIM_NUMBER = ? END");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { sim.getSimNumber(),
					sim.getSimNumber(),
					sim.getSimNumber() }, Integer.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
}
