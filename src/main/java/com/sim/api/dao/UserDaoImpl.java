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
import org.springframework.dao.DataAccessException;
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
import com.sim.api.model.PrivilegeJson;
import com.sim.api.model.ProgramJson;
import com.sim.api.model.RoleJson;
import com.sim.api.model.User;
import com.sim.api.utils.Constants;
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
			if(Constants.USER_TYPE_ADMIN.equals(searchDataTable.getDataSearch().getUserType())) {
				orderColumns = new String[]{"a.LAST_UPDATED_DATE", "a.USER_ID", "a.FIRST_NAME", "a.LAST_NAME", "a.ACTIVE_STATUS" };
				sql.append("SELECT a.USER_ID, a.FIRST_NAME, a.LAST_NAME, a.ACTIVE_STATUS, a.EMAIL, 0 CNT_BOOKING, 0 CNT_REQUEST, p.DESCRIPTION PREFIX_NAME, r.ROLE_NAME, r.ROLE_ID ");
			}else {
				orderColumns = new String[]{"a.LAST_UPDATED_DATE", "a.USER_ID", "a.FIRST_NAME", "a.LAST_NAME", "a.EMAIL" };
				sql.append("SELECT (SELECT COUNT(0) FROM ").append(DBConstants.BOOKING).append(" WHERE a.USER_ID = MERCHANT_ID) CNT_BOOKING, (SELECT COUNT(0) FROM ").append(DBConstants.REQUEST_SIM).append(" WHERE a.USER_ID = MERCHANT_ID) CNT_REQUEST, a.USER_ID, a.FIRST_NAME, a.LAST_NAME, a.EMAIL, a.ACTIVE_STATUS, p.DESCRIPTION PREFIX_NAME, NULL ROLE_NAME, 0 ROLE_ID ");
			}
			sql.append(" FROM ");
			sql.append(DBConstants.USER).append(" a ");
			sql.append(" LEFT JOIN ").append(DBConstants.MASTER_SETUP).append(" p ON p.GROUP_TYPE = 'PREFIX' AND a.PREFIX = p.ID ");
			if(Constants.USER_TYPE_ADMIN.equals(searchDataTable.getDataSearch().getUserType())) {
				sql.append(" LEFT JOIN ").append(DBConstants.ROLE).append(" r ON a.ROLE = r.ROLE_ID ");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getUserId())){
				where.add(" a.USER_ID LIKE ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getFirstName()) || StringUtils.isNotBlank(searchDataTable.getDataSearch().getLastName())){
				where.add(" (a.FIRST_NAME LIKE ? OR a.LAST_NAME LIKE ?)");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
				where.add(" a.ACTIVE_STATUS = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRole())){
				where.add(" a.ROLE =  ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getUserType())){
				where.add(" a.USER_TYPE =  ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getEmail())){
				where.add(" a.EMAIL LIKE  ?");
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
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getFirstName()) || StringUtils.isNotBlank(searchDataTable.getDataSearch().getLastName())){
		        			preparedStatement.setString(i++, "%" + searchDataTable.getDataSearch().getFirstName() + "%");
		        			preparedStatement.setString(i++, "%" + searchDataTable.getDataSearch().getLastName() + "%");
		    			}
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
		    				preparedStatement.setString(i++, searchDataTable.getDataSearch().getActiveStatus());
		    			}
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRole())){
		    				preparedStatement.setString(i++, searchDataTable.getDataSearch().getRole());
		    			}
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getUserType())){
		    				preparedStatement.setString(i++, searchDataTable.getDataSearch().getUserType());
		    			}
		                if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getEmail())){
		    				preparedStatement.setString(i++, searchDataTable.getDataSearch().getEmail());
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
						user.setCntBooking(rs.getInt("CNT_BOOKING"));
						user.setCntRequest(rs.getInt("CNT_REQUEST"));
						user.setEmail(rs.getString("EMAIL"));
						user.setPrefixName(rs.getString("PREFIX_NAME"));
						RoleJson role = new RoleJson();
						role.setRoleId(rs.getInt("ROLE_ID"));
						role.setRoleName(rs.getString("ROLE_NAME"));
						user.setRoleJson(role);
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
			sql.append(" SELECT a.*, b.DESCRIPTION PREFIX_NAME, p.PROVINCE_NAME FROM ");
			sql.append(DBConstants.USER).append(" a LEFT JOIN ").append(DBConstants.MASTER_SETUP);
			sql.append(" b ON b.GROUP_TYPE = 'PREFIX' AND a.PREFIX = b.ID ");
			sql.append(" LEFT JOIN ").append(DBConstants.PROVINCE).append(" p ON a.PROVINCE = p.PROVINCE_ID ");
			sql.append(" WHERE a.TOKEN_ID = ? AND a.ACTIVE_STATUS = 'Y'");
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
			sql.append(" SELECT a.*, b.DESCRIPTION PREFIX_NAME, p.PROVINCE_NAME FROM ");
			sql.append(DBConstants.USER).append(" a LEFT JOIN ").append(DBConstants.MASTER_SETUP);
			sql.append(" b ON b.GROUP_TYPE = 'PREFIX' AND a.PREFIX = b.ID ");
			sql.append(" LEFT JOIN ").append(DBConstants.PROVINCE).append(" p ON a.PROVINCE = p.PROVINCE_ID ");
			sql.append(" WHERE a.USER_ID = ? AND a.PASSWORD = ? AND a.ACTIVE_STATUS = 'Y'");
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
	    	sql.append(" LAST_UPDATED_DATE , LAST_UPDATED_BY, LINE, WEBSITE, NICKNAME, USER_TYPE ");
	    	sql.append(" ) VALUES ");
	    	sql.append("( ?,?,?,?,?,?, CASE WHEN ? = '").append(Constants.USER_TYPE_CUSTOMER).append("' THEN (SELECT ROLE_ID FROM ROLE WHERE ROLE_NAME = 'USER_DEFAULT') ELSE ? END,?,?,?,?,?,?,SYSDATE(),?,SYSDATE(),?,?,?,?,? ) ");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	    		@Override
	    		public void setValues(PreparedStatement ps) throws SQLException {
	    			ps.setString(1, user.getUserId());
	    			ps.setString(2, user.getPassword());
	    			ps.setInt(3, user.getPrefix());
	    			ps.setString(4, user.getFirstName());
	    			ps.setString(5, user.getLastName());
	    			ps.setString(6, user.getActiveStatus());
	    			ps.setString(7, user.getUserType());
	    			ps.setString(8, user.getRole());
	    			ps.setString(9, user.getAddress());
	    			ps.setInt(10, user.getProvince());
	    			ps.setString(11, user.getPostcode());
	    			ps.setString(12, user.getMobile());
	    			ps.setString(13, user.getEmail());
	    			ps.setString(14, user.getActivateEmail());
	    			ps.setString(15, user.getCreatedBy());
	    			ps.setString(16, user.getLastUpdatedBy());
	    			ps.setString(17, user.getLine());
	    			ps.setString(18, user.getWebsite());
	    			ps.setString(19, user.getNickName());
	    			ps.setString(20, user.getUserType());
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
			sql.append(" SELECT a.*, b.DESCRIPTION PREFIX_NAME, p.PROVINCE_NAME FROM ");
			sql.append(DBConstants.USER).append(" a LEFT JOIN ").append(DBConstants.MASTER_SETUP);
			sql.append(" b ON b.GROUP_TYPE = 'PREFIX' AND a.PREFIX = b.ID ");
			sql.append(" LEFT JOIN ").append(DBConstants.PROVINCE).append(" p ON a.PROVINCE = p.PROVINCE_ID ");
			sql.append(" WHERE a.USER_ID = ?");
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
	
	@Override
	public List<PrivilegeJson> getRightUserByRoleId(String roleId) {
		List<PrivilegeJson> results = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT p.ROLE_ID, ");
			sql.append("p.PROGRAM_ID, ");
			sql.append("p.MAKER, ");
			sql.append("p.CHECKER, ");
			sql.append("p.VIEWER, ");
			sql.append("pg.GROUP_LEVEL, ");
			sql.append("pg.PROGRAM_GROUP, ");
			sql.append("pg.PROGRAM_NAME, pg.PROGRAM_TYPE, pg.PROGRAM_LEVEL, ");
			sql.append("pg.PROGRAM_REF, pg.ACTIVE_STATUS, pg.ELEMENT_ID, pg.POSITION ");
			sql.append(" FROM ").append(DBConstants.ROLE).append(" r ");
			sql.append("INNER JOIN ").append(DBConstants.PRIVILEGE).append(" p ON p.ROLE_ID = r.ROLE_ID ");
			sql.append("INNER JOIN ").append(DBConstants.PROGRAM).append(" pg ON pg.PROGRAM_ID = p.PROGRAM_ID AND pg.ACTIVE_STATUS = 'Y' ");
			sql.append("WHERE r.ROLE_ID = ? ");
			sql.append("AND r.ACTIVE_STATUS = 'Y' ORDER BY pg.GROUP_LEVEL, pg.PROGRAM_LEVEL ASC");
			results = jdbcTemplate.query(sql.toString(),  new Object[]{ roleId }, new RowMapper<PrivilegeJson>() {
				
				@Override
				public PrivilegeJson mapRow(ResultSet rs, int numRow) throws SQLException {
					PrivilegeJson privilegeMst = new PrivilegeJson();
					privilegeMst.setRoleId(rs.getInt("ROLE_ID"));
					privilegeMst.setProgramId(rs.getInt("PROGRAM_ID"));
					privilegeMst.setMaker(rs.getString("MAKER"));
					privilegeMst.setChecker(rs.getString("CHECKER"));
					privilegeMst.setViewer(rs.getString("VIEWER"));
					privilegeMst.setProgramJson(new ProgramJson(rs.getInt("PROGRAM_ID"), rs.getString("PROGRAM_NAME"), rs.getString("PROGRAM_REF"), rs.getInt("PROGRAM_LEVEL"), rs.getString("PROGRAM_GROUP"), rs.getInt("GROUP_LEVEL"), rs.getString("PROGRAM_TYPE"), rs.getString("ELEMENT_ID"), rs.getString("POSITION")));
					return privilegeMst;
				}
		    });
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return results;
	}
	
	@Override
	public List<PrivilegeJson> getRightUserDefault() {
		List<PrivilegeJson> results = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT p.ROLE_ID, ");
			sql.append("p.PROGRAM_ID, ");
			sql.append("p.MAKER, ");
			sql.append("p.CHECKER, ");
			sql.append("p.VIEWER, ");
			sql.append("pg.GROUP_LEVEL, ");
			sql.append("pg.PROGRAM_GROUP, ");
			sql.append("pg.PROGRAM_NAME, pg.PROGRAM_TYPE, pg.PROGRAM_LEVEL, ");
			sql.append("pg.PROGRAM_REF, pg.ACTIVE_STATUS, pg.ELEMENT_ID, pg.POSITION ");
			sql.append(" FROM ").append(DBConstants.ROLE).append(" r ");
			sql.append("INNER JOIN ").append(DBConstants.PRIVILEGE).append(" p ON p.ROLE_ID = r.ROLE_ID ");
			sql.append("INNER JOIN ").append(DBConstants.PROGRAM).append(" pg ON pg.PROGRAM_ID = p.PROGRAM_ID AND pg.ACTIVE_STATUS = 'Y' ");
			sql.append("WHERE r.ROLE_NAME = 'USER_DEFAULT' ");
			sql.append("AND r.ACTIVE_STATUS = 'Y' ORDER BY pg.GROUP_LEVEL, pg.PROGRAM_LEVEL ASC");
			results = jdbcTemplate.query(sql.toString(),  new Object[]{ }, new RowMapper<PrivilegeJson>() {
				
				@Override
				public PrivilegeJson mapRow(ResultSet rs, int numRow) throws SQLException {
					PrivilegeJson privilegeMst = new PrivilegeJson();
					privilegeMst.setRoleId(rs.getInt("ROLE_ID"));
					privilegeMst.setProgramId(rs.getInt("PROGRAM_ID"));
					privilegeMst.setMaker(rs.getString("MAKER"));
					privilegeMst.setChecker(rs.getString("CHECKER"));
					privilegeMst.setViewer(rs.getString("VIEWER"));
					privilegeMst.setProgramJson(new ProgramJson(rs.getInt("PROGRAM_ID"), rs.getString("PROGRAM_NAME"), rs.getString("PROGRAM_REF"), rs.getInt("PROGRAM_LEVEL"), rs.getString("PROGRAM_GROUP"), rs.getInt("GROUP_LEVEL"), rs.getString("PROGRAM_TYPE"), rs.getString("ELEMENT_ID"), rs.getString("POSITION")));
					return privilegeMst;
				}
		    });
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return results;
	}
	
	@Override
	public void updateConfirmation(User user) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.USER).append(" SET ACTIVE_STATUS = 'Y' WHERE ACTIVATE_EMAIL = ? AND USER_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
		        @Override
		        public void setValues(PreparedStatement ps) throws SQLException {
		        	ps.setString(1, user.getActivateEmail());
		        	ps.setString(2, user.getUserId());
		        }
		    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
	}
	
	@Override
	public void updateUser(User user) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{	
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ");
	    	sql.append(DBConstants.USER);
	    	sql.append(" SET ");
	    	sql.append(" PASSWORD = (CASE WHEN (SHA2(?, 256) = PASSWORD || ? = '' || ? IS NULL) THEN PASSWORD ELSE SHA2(?, 256) END), PREFIX = ?, FIRST_NAME = ?, LAST_NAME = ?, ADDRESS = ?, PROVINCE = ?, POSTCODE = ?, ");
	    	sql.append(" MOBILE = ?, EMAIL = ?, ");
	    	sql.append(" LAST_UPDATED_DATE = SYSDATE() , LAST_UPDATED_BY = ?, LINE = ?, WEBSITE = ?, NICKNAME = ? WHERE USER_ID = ?");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	    		@Override
	    		public void setValues(PreparedStatement ps) throws SQLException {
	    			ps.setString(1, user.getPassword());
	    			ps.setString(2, user.getPassword());
	    			ps.setString(3, user.getPassword());
	    			ps.setString(4, user.getPassword());
	    			ps.setInt(5, user.getPrefix());
	    			ps.setString(6, user.getFirstName());
	    			ps.setString(7, user.getLastName());
	    			ps.setString(8, user.getAddress());
	    			ps.setInt(9, user.getProvince());
	    			ps.setString(10, user.getPostcode());
	    			ps.setString(11, user.getMobile());
	    			ps.setString(12, user.getEmail());
	    			ps.setString(13, user.getLastUpdatedBy());
	    			ps.setString(14, user.getLine());
	    			ps.setString(15, user.getWebsite());
	    			ps.setString(16, user.getNickName());
	    			ps.setString(17, user.getUserId());
	    			
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
	public void updateUserAdmin(User user) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{	
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ");
	    	sql.append(DBConstants.USER);
	    	sql.append(" SET ");
	    	sql.append(" PREFIX = ?, FIRST_NAME = ?, LAST_NAME = ?, ROLE = ?, ACTIVE_STATUS = ?, ");
	    	sql.append(" LAST_UPDATED_DATE = SYSDATE() , LAST_UPDATED_BY = ? WHERE USER_ID = ?");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	    		@Override
	    		public void setValues(PreparedStatement ps) throws SQLException {
	    			ps.setInt(1, user.getPrefix());
	    			ps.setString(2, user.getFirstName());
	    			ps.setString(3, user.getLastName());
	    			ps.setString(4, user.getRole());
	    			ps.setString(5, user.getActiveStatus());
	    			ps.setString(6, user.getLastUpdatedBy());
	    			ps.setString(7, user.getUserId());
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
	public int updatePassword(String userId, String oldPassword, String newPassword) {
		int result = 0;
		try{	
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ");
	    	sql.append(DBConstants.USER);
	    	sql.append(" SET ");
	    	sql.append(" PASSWORD = SHA2(?, 256), ");
	    	sql.append(" LAST_UPDATED_DATE = SYSDATE() , LAST_UPDATED_BY = ? WHERE USER_ID = ? AND PASSWORD = SHA2(?, 256)");
	    	result = jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	    		@Override
	    		public void setValues(PreparedStatement ps) throws SQLException {
	    			ps.setString(1, newPassword);
	    			ps.setString(2, userId);
	    			ps.setString(3, userId);
	    			ps.setString(4, oldPassword);	    			
	    		}
	    	});
	    }catch (Exception e) {
	    	logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public int updateForgotPassword(String email) {
		int result = 0;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.USER).append(" SET FORGOT_PASSWORD = SHA2(?, 256) WHERE EMAIL = ?");
			result = jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
		        @Override
		        public void setValues(PreparedStatement ps) throws SQLException {
		        	ps.setString(1, new Date().getTime() + "");
		        	ps.setString(2, email);
		        }
		    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public User selectUserByEmail(String email) {
		User result = null;
		StringBuilder sql = new StringBuilder();
		try {		
			sql.append("SELECT * FROM ").append(DBConstants.USER).append(" WHERE EMAIL = ?");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[]{ email }, new RowMapper<User>() {
				 
					@Override
					public User mapRow(ResultSet rs, int numRow) throws SQLException {
						User user = new User();
						user.setUserId(rs.getString("USER_ID"));
						user.setFirstName(rs.getString("FIRST_NAME"));
						user.setLastName(rs.getString("LAST_NAME"));
						user.setActiveStatus(rs.getString("ACTIVE_STATUS"));
						user.setEmail(rs.getString("EMAIL"));
						user.setForgotPassword(rs.getString("FORGOT_PASSWORD"));
						return user;
					}
			    });
		}catch (DataAccessException dae) {
	    	result = null;
	    }catch (Exception e) {
        	logger.error(e);
        	throw e;
        }	
		return result;
	}
	
	@Override
	public User selectUserByEmailAndForgotPassword(User user) {
		User result = null;
		StringBuilder sql = new StringBuilder();
		try {		
			sql.append("SELECT * FROM ").append(DBConstants.USER).append(" WHERE EMAIL = ? AND FORGOT_PASSWORD = ?");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[]{ user.getEmail(), user.getForgotPassword() }, new RowMapper<User>() {
				 
					@Override
					public User mapRow(ResultSet rs, int numRow) throws SQLException {
						User user = new User();
						user.setUserId(rs.getString("USER_ID"));
						user.setFirstName(rs.getString("FIRST_NAME"));
						user.setLastName(rs.getString("LAST_NAME"));
						user.setActiveStatus(rs.getString("ACTIVE_STATUS"));
						user.setEmail(rs.getString("EMAIL"));
						user.setForgotPassword(rs.getString("FORGOT_PASSWORD"));
						return user;
					}
			    });
		}catch (DataAccessException dae) {
	    	result = null;
	    }catch (Exception e) {
        	logger.error(e);
        	throw e;
        }	
		return result;
	}
	
	@Override
	public void updatePasswordForgotPassword(User user) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.USER).append(" SET PASSWORD = SHA2(?, 256), FORGOT_PASSWORD = NULL, LAST_UPDATED_DATE = SYSDATE(), LAST_UPDATED_BY = ? WHERE USER_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
		        @Override
		        public void setValues(PreparedStatement ps) throws SQLException {
		        	ps.setString(1, user.getPassword());
		        	ps.setString(2, user.getLastUpdatedBy());
		        	ps.setString(3, user.getUserId());
		        }
		    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
	}
	
	@Override
	public void editUserCustomer(User user) {
		try{	
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ");
	    	sql.append(DBConstants.USER);
	    	sql.append(" SET ");
	    	sql.append(" ACTIVE_STATUS = ?, ");
	    	sql.append(" LAST_UPDATED_DATE = SYSDATE() , LAST_UPDATED_BY = ? WHERE USER_ID = ? ");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	    		@Override
	    		public void setValues(PreparedStatement ps) throws SQLException {
	    			ps.setString(1, user.getActiveStatus());
	    			ps.setString(2, user.getLastUpdatedBy());  
	    			ps.setString(3, user.getUserId());  			
	    		}
	    	});
	    }catch (Exception e) {
	    	logger.error(e);
    		throw e;
        }
	}
	
}
