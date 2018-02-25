package com.sim.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import com.sim.api.datatable.SearchDataTable;
import com.sim.api.datatable.Order;
import com.sim.api.mapper.UserRowMapper;
import com.sim.api.model.User;
import com.sim.api.utils.DBConstants;

@Repository("userDaoImpl") 
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate; 
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired 
    @Qualifier("transactionManager") 
    private PlatformTransactionManager transactionManager;
	
	@Autowired
	private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
	}
	
	@Override
	public List<User> searchUser(SearchDataTable<User> searchDataTable) {
		List<User> results = null;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		StringBuilder order = new StringBuilder();
		String[] orderColumns = null;
		try {
			orderColumns = new String[]{"a.LAST_UPDATED_DATE", "a.USER_ID", "a.FIRST_NAME", "a.LAST_NAME", "a.ACTIVE_STATUS" };

			sql.append("SELECT (SELECT COUNT(0) FROM BOOKING WHERE a.USER_ID = MERCHANT_ID) CNT_BOOKING, a.USER_ID, a.FIRST_NAME, a.LAST_NAME, a.ACTIVE_STATUS ");
			sql.append(" FROM ");
			sql.append(DBConstants.USER).append(" a ");	
			where.add(" a.ROLE NOT IN ('ADMIN')");
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getUserId())){
				where.add(" a.USER_ID LIKE ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getFirstName())){
				where.add(" a.FIRST_NAME LIKE ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getLastName())){
				where.add(" a.LAST_NAME LIKE ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
				where.add(" a.ACTIVE_STATUS = ?");
			}
			if(!where.isEmpty()){
				sql.append(" WHERE ");
				for(String strWhere : where){
					sql.append(strWhere).append(" AND ");
				}
				sql = new StringBuilder(sql.substring(0, sql.length() - 4));
			}
			if(!searchDataTable.getOrder().isEmpty()){
				for(Order field : searchDataTable.getOrder()){
					order.append(orderColumns[field.getColumn()]).append(" ").append(field.getDir());
				}
				if(order.length() > 0){
					sql.append(" ORDER BY ").append(order);
				}
			}
			sql.append(" LIMIT ").append(searchDataTable.getLength()).append(" OFFSET ").append(searchDataTable.getStart());
			results = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
		            @Override
		            public void setValues(PreparedStatement preparedStatement) throws
		                    SQLException {
		            	int i = 1;
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getUserId())){
		                	preparedStatement.setString(i++, "%" + searchDataTable.getDataSearch().getUserId() + "%");
		    			}
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getFirstName())){
		                	preparedStatement.setString(i++, "%" + searchDataTable.getDataSearch().getFirstName() + "%");
		    			}
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getLastName())){
		                	preparedStatement.setString(i++, "%" + searchDataTable.getDataSearch().getLastName() + "%");
		    			}
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
		    				preparedStatement.setString(i++, searchDataTable.getDataSearch().getActiveStatus());
		    			}
		            }
		        }, new RowMapper<User>() {
				 
					@Override
					public User mapRow(ResultSet rs, int numRow) throws SQLException {
						User user = new User();
						user.setUserId(rs.getString("USER_ID"));
						user.setFirstName(rs.getString("FIRST_NAME"));
						user.setLastName(rs.getString("LAST_NAME"));
						user.setActiveStatus(rs.getString("ACTIVE_STATUS"));
						return user;
					}
			    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return results;
	}
	
	@Override
	public int countUserFilter(SearchDataTable<User> searchDataTable) {
		int results = 0;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		try {		
			sql.append("SELECT COUNT(0) ");
			sql.append(" FROM ");
			sql.append(DBConstants.USER).append(" a ");	
			where.add(" CASE WHEN (? = '' || ? IS NULL) THEN 1=1 ELSE a.USER_ID LIKE ? END");
			where.add(" CASE WHEN (? = '' || ? IS NULL) THEN 1=1 ELSE a.FIRST_NAME LIKE ? END");
			where.add(" CASE WHEN (? = '' || ? IS NULL) THEN 1=1 ELSE a.LAST_NAME LIKE ? END");
			where.add(" CASE WHEN (? = '' || ? IS NULL) THEN 1=1 ELSE a.ACTIVE_STATUS = ? END");
			where.add(" ROLE NOT IN ('ADMIN')");
			if(!where.isEmpty()){
				sql.append(" WHERE ");
				for(String strWhere : where){
					sql.append(strWhere).append(" AND ");
				}
				sql = new StringBuilder(sql.substring(0, sql.length() - 4));
			}
			
			results = jdbcTemplate.queryForObject(sql.toString(), new Object[]{
					searchDataTable.getDataSearch().getUserId(),
					searchDataTable.getDataSearch().getUserId(),
					"%" + searchDataTable.getDataSearch().getUserId() + "%",
					searchDataTable.getDataSearch().getFirstName(),
					searchDataTable.getDataSearch().getFirstName(),
					"%" + searchDataTable.getDataSearch().getFirstName() + "%",
					searchDataTable.getDataSearch().getLastName(),
					searchDataTable.getDataSearch().getLastName(),
					"%" + searchDataTable.getDataSearch().getLastName() + "%",
					searchDataTable.getDataSearch().getActiveStatus(),
					searchDataTable.getDataSearch().getActiveStatus(),
					searchDataTable.getDataSearch().getActiveStatus()
			}, Integer.class);
		}catch (Exception e) {
        	logger.error(e);
        	throw e;
        }	
		return results;
	}
	
	@Override
	public int countUserTotal(SearchDataTable<User> searchDataTable) {
		int results = 0;
		StringBuilder sql = new StringBuilder();
		try {		
			sql.append("SELECT COUNT(0) FROM ").append(DBConstants.USER).append(" WHERE ROLE NOT IN ('ADMIN')");
			results = jdbcTemplate.queryForObject(sql.toString(), new Object[]{ 
				}, Integer.class);
		}catch (Exception e) {
        	logger.error(e);
        	throw e;
        }	
		return results;
	}
	
	@Override
	public User checkValidateToken(String tokenId) {
		User result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT a.*, b.DESCRIPTION PREFIX_NAME FROM ");
			sql.append(DBConstants.USER).append(" a LEFT JOIN ").append(DBConstants.MASTER_SETUP);
			sql.append(" b ON b.GROUP_TYPE = 'PREFIX' AND a.PREFIX = b.ID WHERE a.TOKEN_ID = ? AND a.ACTIVE_STATUS = 'Y'");
			result = jdbcTemplate.queryForObject(sql.toString(),  new Object[] { tokenId }, new UserRowMapper());
		} catch(EmptyResultDataAccessException e){
			logger.debug(e);
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}

	@Override
	public User checkLogin(User user) {
		User result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT a.*, b.DESCRIPTION PREFIX_NAME FROM ");
			sql.append(DBConstants.USER).append(" a LEFT JOIN ").append(DBConstants.MASTER_SETUP);
			sql.append(" b ON b.GROUP_TYPE = 'PREFIX' AND a.PREFIX = b.ID WHERE a.USER_ID = ? AND a.PASSWORD = ? AND a.ACTIVE_STATUS = 'Y'");
			result = (User) jdbcTemplate.queryForObject(sql.toString(),  new Object[] { user.getUserId(), user.getPassword() }, new UserRowMapper());
		} catch(EmptyResultDataAccessException e){
			logger.debug(e);
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public void insertUser(User user) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{	
	        StringBuilder sql = new StringBuilder();
	    	sql.append("INSERT INTO ");
	    	sql.append(DBConstants.USER);
	    	sql.append(" (  ");
	    	sql.append(" USER_ID, PASSWORD, PREFIX, FIRST_NAME, LAST_NAME, ACTIVE_STATUS, ROLE, ADDRESS, PROVINCE, POSTCODE, ");
	    	sql.append(" MOBILE, EMAIL, ACTIVATE_EMAIL, ");
	    	sql.append(" CREATED_DATE, CREATED_BY,");
	    	sql.append(" LAST_UPDATED_DATE , LAST_UPDATED_BY ");
	    	sql.append(" ) VALUES ");
	    	sql.append("( ?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE(),?,SYSDATE(),? ) ");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	    		@Override
	    		public void setValues(PreparedStatement ps) throws SQLException {
	    			ps.setString(1, user.getUserId());
	    			ps.setString(2, user.getPassword());
	    			ps.setInt(3, user.getPrefix());
	    			ps.setString(4, user.getFirstName());
	    			ps.setString(5, user.getLastName());
	    			ps.setString(6, user.getActiveStatus());
	    			ps.setString(7, user.getRole());
	    			ps.setString(8, user.getAddress());
	    			ps.setInt(9, user.getProvince());
	    			ps.setString(10, user.getPostcode());
	    			ps.setString(11, user.getMobile());
	    			ps.setString(12, user.getEmail());
	    			ps.setString(13, user.getActivateEmail());
	    			ps.setString(14, user.getCreatedBy());
	    			ps.setString(15, user.getLastUpdatedBy());
	    		}
	    	});
	    	transactionManager.commit(status);
		}catch (Exception e) {
	    	transactionManager.rollback(status);
    		logger.error(e);
    		throw e;
        }
	}
	
	@Override
	public void updateTokenLogin(User user) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.USER).append(" SET TOKEN_ID = ? WHERE USER_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
		        @Override
		        public void setValues(PreparedStatement ps) throws SQLException {
		        	ps.setString(1, user.getTokenId());
		        	ps.setString(2, user.getUserId());
		        }
		    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
	}
	
	@Override
	public User loadUserById(String userId) {
		User result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT a.*, b.DESCRIPTION PREFIX_NAME FROM ");
			sql.append(DBConstants.USER).append(" a LEFT JOIN ").append(DBConstants.MASTER_SETUP);
			sql.append(" b ON b.GROUP_TYPE = 'PREFIX' AND a.PREFIX = b.ID WHERE a.USER_ID = ?");
			result = jdbcTemplate.queryForObject(sql.toString(),  new Object[] { userId }, new UserRowMapper());
		} catch(EmptyResultDataAccessException e){
			logger.debug(e);
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public void deleteUserById(String userId) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.USER).append(" WHERE USER_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
		        @Override
		        public void setValues(PreparedStatement ps) throws SQLException {
		        	ps.setString(1, userId);
		        }
		    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
	}
	
	@Override
	public int checkDuplicateUser(String userId) {
		int results = 0;
		StringBuilder sql = new StringBuilder();
		try {		
			sql.append("SELECT COUNT(0) FROM ").append(DBConstants.USER).append(" WHERE USER_ID = ?");
			results = jdbcTemplate.queryForObject(sql.toString(), new Object[]{ userId }, Integer.class);
		}catch (Exception e) {
        	logger.error(e);
        	throw e;
        }	
		return results;
	}
}
