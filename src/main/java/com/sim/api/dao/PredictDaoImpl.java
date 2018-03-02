package com.sim.api.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import com.sim.api.mapper.PredictRowMapper;
import com.sim.api.model.Predict;
import com.sim.api.utils.DBConstants;

@Repository("predictDao")
public class PredictDaoImpl implements PredictDao {

	private JdbcTemplate jdbcTemplate; 
	private static final Logger LOGGER = Logger.getLogger(PredictDaoImpl.class);
	
	@Autowired 
    @Qualifier("transactionManager") 
    private PlatformTransactionManager transactionManager;
    
	@Autowired
	private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
	}
	
	@Override
	public Predict getPredictById(int id) {
		Predict result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ");
			sql.append(DBConstants.PREDICT);
			sql.append(" WHERE PREDICT_ID = ? ");
			result = jdbcTemplate.queryForObject(sql.toString(),  new Object[] { id }, new PredictRowMapper());
		} catch(EmptyResultDataAccessException  ex){
			LOGGER.error(">> getPredictById ERROR :"+ ex);
		}
		return result;	
	}

	@Override
	public void updatePredict(Predict predict) {
	    try{
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ").append(DBConstants.PREDICT);
	    	sql.append(" SET  PREDICT_CONTENT = ? ");
	    	sql.append("      , LAST_UPDATED_DATE  = SYSDATE() ");
	    	sql.append("      , LAST_UPDATED_BY  = ? ");
	    	sql.append(" WHERE PREDICT_ID = ?");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setString(1, predict.getPredictContent());
	                ps.setString(2, predict.getLastUpdatedBy());
	                ps.setInt(3, predict.getPredictId());
	            }
	        });
	    }catch (Exception e) {
        	LOGGER.error(" >>> updatePredict Error !!! : "+e);
        	throw e;
        }
	}
}
