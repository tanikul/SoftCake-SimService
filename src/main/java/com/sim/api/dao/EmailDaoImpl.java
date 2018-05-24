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
import com.sim.api.mapper.EmailRowMapper;
import com.sim.api.model.Email;
import com.sim.api.utils.DBConstants;

@Repository("emailDao")
public class EmailDaoImpl implements EmailDao {

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
	public Email getEmailById(int id) {
		Email result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ");
			sql.append(DBConstants.EMAIL);
			sql.append(" WHERE EMAIL_ID = ? ");
			result = jdbcTemplate.queryForObject(sql.toString(),  new Object[] { id }, new EmailRowMapper());
		} catch(EmptyResultDataAccessException  ex){
			LOGGER.error(">> getEmailById ERROR :"+ ex);
		}
		return result;	
	}

	@Override
	public void updateEmail(Email email) {
		try{
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ").append(DBConstants.EMAIL);
	    	sql.append(" SET FROM = ? ");
	    	sql.append("      , DESCRIPTION  = ? ");
	    	sql.append("      , ACTIVE_STATUS  = ? ");
	    	sql.append(" WHERE EMAIL_ID = ?");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setString(1, email.getFrom());
	                ps.setString(2, email.getDescription());
	                ps.setString(3, email.getActiveStatus());
	                ps.setInt(4, email.getEmailId());
	            }
	        });
	    }catch (Exception e) {
        	LOGGER.error(" >>> updateEmail Error !!! : "+e);
        	throw e;
        }
	}

}
