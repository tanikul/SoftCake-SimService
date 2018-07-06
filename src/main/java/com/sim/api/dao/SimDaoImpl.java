package com.sim.api.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.sim.api.datatable.Order;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.FilterSearch;
import com.sim.api.model.RequestMst;
import com.sim.api.model.RequestSim;
import com.sim.api.model.Sim;
import com.sim.api.utils.Constants;
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
	public List<Sim> SearchSim(int page, FilterSearch filter) {
		List<Sim> result = null;
		List<String> where = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT * FROM ").append(DBConstants.SIM_MST).append(" a ");
			where.add(" a.BOOKING_FLAG NOT IN('Y')");
			if(StringUtils.isNotBlank(filter.getFilter_1())){
				where.add(" a.SIM_NUMBER LIKE ?");
			}
			if(StringUtils.isNotBlank(filter.getFilter_2())){
				where.add(" a.SIM_NUMBER LIKE ?");
			}
			if(StringUtils.isNotBlank(filter.getFilter_3())){
				where.add(" a.SIM_NUMBER LIKE ?");
			}
			if(StringUtils.isNotBlank(filter.getFilter_4())){
				where.add(" a.SIM_NUMBER NOT LIKE ?");
			}
			if(StringUtils.isNotBlank(filter.getFilter_5())){
				where.add(" a.SUM_NUMBER = ?");
			}
			if(!where.isEmpty()){
				sql.append(" WHERE ");
				for(String strWhere : where){
					sql.append(strWhere).append(" AND ");
				}
				sql = new StringBuilder(sql.substring(0, sql.length() - 4));
			}
			sql.append(" LIMIT ").append(36).append(" OFFSET ").append((page - 1) * 36);
			result = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;
	            	if(StringUtils.isNotBlank(filter.getFilter_1())){
	            		preparedStatement.setString(i++, filter.getFilter_1() + "%");
	            	}
	            	if(StringUtils.isNotBlank(filter.getFilter_2())){
	            		preparedStatement.setString(i++, "%" + filter.getFilter_2());
	            	}
	            	if(StringUtils.isNotBlank(filter.getFilter_3())){
	            		preparedStatement.setString(i++, "%" + filter.getFilter_3() + "%");
	            	}
	            	if(StringUtils.isNotBlank(filter.getFilter_4())){
	            		preparedStatement.setString(i++, "%" + filter.getFilter_4() + "%");
	            	}
	            	if(StringUtils.isNotBlank(filter.getFilter_5())){
	            		preparedStatement.setString(i++, filter.getFilter_5());
	            	}
	            }
	        }, new RowMapper<Sim>() {
					@Override
					public Sim mapRow(ResultSet rs, int numRow) throws SQLException {
						Sim sim = new Sim();
						sim.setCreditTerm(rs.getString("CREDIT_TERM"));
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
			sql.append(DBConstants.SIM_MST);
			sql.append(" WHERE CASE WHEN (? = '' || ? IS NULL) THEN 1 = 1 ELSE SIM_NUMBER = ? END");
			sql.append(" AND BOOKING_FLAG NOT IN('Y')");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { sim.getSimNumber(),
					sim.getSimNumber(),
					sim.getSimNumber() }, Integer.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public void insertSim(Sim sim) {
		StringBuilder sql = new StringBuilder();
		try{
			sql.append("  INSERT INTO ").append(DBConstants.SIM_TMP); 
			sql.append("  (SIM_NUMBER, CREDIT_TERM, PRICE, ACTIVE_STATUS, OPERATION_FLAG, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY, SUM_NUMBER)  "); 
			sql.append("  VALUES ( ?, ?, ?, ?, ? , SYSDATE() , ? , SYSDATE(), ?, ? )  ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, sim.getSimNumber());
					ps.setString(2, sim.getCreditTerm());
					ps.setBigDecimal(3, sim.getPrice());
					ps.setString(4, "W");
					ps.setString(5, "N");
					ps.setString(6, sim.getCreatedBy());
					ps.setString(7, sim.getLastUpdatedBy());
					//ps.setDate(8, new java.sql.Date(sim.getRecievedDate().getTime()));
					ps.setInt(8, sim.getSumNumber());
				}
			});
	    }catch (Exception e) {
	    	logger.error(e);
    		throw e;
        }
	}
	
	@Override
	public List<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable, String type) {
		List<Sim> result = null;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		StringBuilder order = new StringBuilder();
		String[] orderColumns = null;
		try {
			orderColumns = new String[]{"a.SIM_NUMBER", "a.PRICE", "a.CREDIT_TERM", "a.ACTIVE_STATUS", "a.OPERATION_FLAG", "b.BOOKING_STATUS"};
			if(type.equals(Constants.MST)){
				sql.append(" SELECT a.SIM_NUMBER, a.PRICE, a.RECIEVED_DATE, a.OPERATION_FLAG, a.ACTIVE_STATUS, b.BOOKING_STATUS, c.MERCHANT_ID, a.CREDIT_TERM, b.ACTIVATE_FLAG, b.CUSTOMER_ID, a.BOOKING_FLAG, a.REJECT_REASON FROM ");
				sql.append(DBConstants.SIM_MST).append(" a ");
				sql.append(" LEFT JOIN ").append(DBConstants.BOOKING_DETAIL).append(" b ON a.SIM_NUMBER = b.SIM_NUMBER");
				sql.append(" LEFT JOIN ").append(DBConstants.BOOKING).append(" c ON b.BOOKING_ID = c.BOOKING_ID");
			}else{
				sql.append(" SELECT a.SIM_NUMBER, a.PRICE, a.RECIEVED_DATE, a.OPERATION_FLAG, a.ACTIVE_STATUS, a.CREDIT_TERM, NULL BOOKING_STATUS, NULL MERCHANT_ID, NULL ACTIVATE_FLAG, NULL CUSTOMER_ID, a.BOOKING_FLAG, a.REJECT_REASON  FROM ");
				sql.append(DBConstants.SIM_TMP).append(" a ");
			}
			
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getSimNumber())){
				where.add(" a.SIM_NUMBER = ?");
			}
			if(searchDataTable.getDataSearch().getPrice() != null){
				where.add(" a.PRICE = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getCreditTerm())){
				where.add(" a.CREDIT_TERM = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
				where.add(" a.ACTIVE_STATUS = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getOperationFlag())){
				where.add(" a.OPERATION_FLAG = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getBookingStatus())){
				where.add(" b.BOOKING_STATUS = ?");
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
			if(type.equals(Constants.MST)) {
				sql.append(" LIMIT ").append(searchDataTable.getLength()).append(" OFFSET ").append(searchDataTable.getStart());
			}
			result = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getSimNumber())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getSimNumber());
	            	}
	            	if(searchDataTable.getDataSearch().getPrice() != null){
	            		preparedStatement.setBigDecimal(i++, searchDataTable.getDataSearch().getPrice());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getCreditTerm())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getCreditTerm());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getActiveStatus());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getOperationFlag())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getOperationFlag());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getBookingStatus())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getBookingStatus());
	            	}
	            }
	        }, new RowMapper<Sim>() {
					@Override
					public Sim mapRow(ResultSet rs, int numRow) throws SQLException {
						Sim sim = new Sim();
						sim.setCreditTerm(rs.getString("CREDIT_TERM"));
						sim.setActiveStatus(rs.getString("ACTIVE_STATUS"));
						sim.setPrice(rs.getBigDecimal("PRICE"));
						sim.setSimNumber(rs.getString("SIM_NUMBER"));
						sim.setRecievedDate(rs.getDate("RECIEVED_DATE"));
						sim.setBookingStatus(rs.getString("BOOKING_STATUS"));
						sim.setMerchantId(rs.getString("MERCHANT_ID"));
						sim.setActivateFlag(rs.getString("ACTIVATE_FLAG"));
						sim.setCustomerIdCard(rs.getString("CUSTOMER_ID"));
						sim.setOperationFlag(rs.getString("OPERATION_FLAG"));
						sim.setBookingFlag(rs.getString("BOOKING_FLAG"));
						sim.setRejectReason(rs.getString("REJECT_REASON"));
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
	public int CountSimDataTableFilter(SearchDataTable<Sim> searchDataTable, String type) {
		List<Sim> results = null;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		try {
			sql.append(" SELECT a.SIM_NUMBER  FROM ");
			if(type.equals(Constants.MST)){
				sql.append(DBConstants.SIM_MST).append(" a ");
				sql.append(" LEFT JOIN ").append(DBConstants.BOOKING_DETAIL).append(" b ON a.SIM_NUMBER = b.SIM_NUMBER");
				sql.append(" LEFT JOIN ").append(DBConstants.BOOKING).append(" c ON b.BOOKING_ID = c.BOOKING_ID");
			}else{
				sql.append(DBConstants.SIM_TMP).append(" a ");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getSimNumber())){
				where.add(" a.SIM_NUMBER = ?");
			}
			if(searchDataTable.getDataSearch().getPrice() != null){
				where.add(" a.PRICE = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getCreditTerm())){
				where.add(" a.CREDIT_TERM = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
				where.add(" a.ACTIVE_STATUS = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getOperationFlag())){
				where.add(" a.OPERATION_FLAG = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getBookingStatus())){
				where.add(" b.BOOKING_STATUS = ?");
			}
			if(!where.isEmpty()){
				sql.append(" WHERE ");
				for(String strWhere : where){
					sql.append(strWhere).append(" AND ");
				}
				sql = new StringBuilder(sql.substring(0, sql.length() - 4));
			}
			
			results = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getSimNumber())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getSimNumber());
	            	}
	            	if(searchDataTable.getDataSearch().getPrice() != null){
	            		preparedStatement.setBigDecimal(i++, searchDataTable.getDataSearch().getPrice());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getCreditTerm())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getCreditTerm());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getActiveStatus())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getActiveStatus());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getOperationFlag())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getOperationFlag());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getBookingStatus())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getBookingStatus());
	            	}
	            }
	        }, new RowMapper<Sim>() {
				 
					@Override
					public Sim mapRow(ResultSet rs, int numRow) throws SQLException {
						Sim corp = new Sim();
						corp.setSimNumber(rs.getString("SIM_NUMBER"));
						return corp;
					}
			 
			    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return (results == null) ? 0 : results.size();
	}
	
	@Override
	public int countSimTotalDataTable(SearchDataTable<Sim> searchDataTable, String type) {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		try {
			sql.append("SELECT COUNT(0) FROM ");
			if(type.equals(Constants.MST)){
				sql.append(DBConstants.SIM_MST);
			}else{
				sql.append(DBConstants.SIM_TMP);
			}
			//sql.append(" WHERE CASE WHEN (? = '' || ? IS NULL) THEN 1 = 1 ELSE SIM_NUMBER = ? END");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { /*searchDataTable.getDataSearch().getSimNumber(),
					searchDataTable.getDataSearch().getSimNumber(),
					searchDataTable.getDataSearch().getSimNumber() */}, Integer.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public Sim selectSimByNumber(String simNumber, String userId) {
		List<Sim> result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT SIM_NUMBER, CREDIT_TERM, PRICE, 'S' FLAG_SIM FROM ").append(DBConstants.SIM_MST).append(" WHERE SIM_NUMBER = ? UNION ");
			sql.append(" SELECT SIM_NUMBER, CREDIT_TERM, PRICE, 'R' FLAG_SIM  FROM ").append(DBConstants.REQUEST_SIM).append(" WHERE SIM_NUMBER = ?");
			result = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;
	            	preparedStatement.setString(i++, simNumber);
	            	preparedStatement.setString(i++, simNumber);
	            }
	        }, new RowMapper<Sim>() {
					@Override
					public Sim mapRow(ResultSet rs, int numRow) throws SQLException {
						Sim sim = new Sim();
						sim.setSimNumber(rs.getString("SIM_NUMBER"));
						sim.setCreditTerm(rs.getString("CREDIT_TERM"));
						sim.setPrice(rs.getBigDecimal("PRICE"));
						sim.setFlagSim(rs.getString("FLAG_SIM"));
						return sim;
					}
			 
			    });
		} catch(EmptyResultDataAccessException e) {
			return null;
		}catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result.get(0);
	}
	
	@Override
	public void updateAutorizeStatus(Sim sim) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("UPDATE ").append(DBConstants.SIM_TMP).append(" SET ACTIVE_STATUS = ?, AUTHORIZED_DATE = SYSDATE(), AUTHORIZED_BY = ?, REJECT_REASON = ?");
			sql.append(" WHERE SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getActiveStatus());
	            	ps.setString(2, sim.getAuthorizedBy());
	            	ps.setString(3, sim.getRejectReason());
	            	ps.setString(4, sim.getSimNumber());
	            }
	        });
		}catch (Exception e) {
        	logger.error(e);
        	throw e;        
		}
	}
	
	@Override
	public void deleteSimTmp(Sim sim) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("DELETE FROM ").append(DBConstants.SIM_TMP).append(" WHERE SIM_NUMBER = ? ");
			jdbcTemplate.update(sql.toString(), new Object[] { sim.getSimNumber() });
		} catch(Exception e){
			logger.error(e);
        	throw e;
		}
	}
	
	@Override
	public void deleteSimTmpAndMst(Sim sim) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("DELETE a, b FROM ").append(DBConstants.SIM_MST).append(" a INNER JOIN ");
			sql.append(DBConstants.SIM_TMP).append(" b ON a.SIM_NUMBER = b.SIM_NUMBER WHERE b.SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new Object[] { sim.getSimNumber() });
		} catch(Exception e){
			logger.error(e);
        	throw e;
		}
	}
	
	@Override
	@Transactional
	public void updateTmpToSimMst(Sim sim) {
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
        try{
	        StringBuilder sql = new StringBuilder();
	    	sql.append("UPDATE ");
	    	sql.append(DBConstants.SIM_MST);
	    	sql.append(" a INNER JOIN ").append(DBConstants.SIM_TMP).append(" b ON a.SIM_NUMBER = b.SIM_NUMBER");
	    	sql.append(" SET a.ACTIVE_STATUS = ?, a.OPERATION_FLAG = b.OPERATION_FLAG, a.AUTHORIZED_BY = ?, a.AUTHORIZED_DATE = SYSDATE(), a.LAST_UPDATED_DATE = b.LAST_UPDATED_DATE , a.LAST_UPDATED_BY = b.LAST_UPDATED_BY");
	    	sql.append(" WHERE a.SIM_NUMBER = ? ");
	    	jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getActiveStatus());
	            	ps.setString(2, sim.getAuthorizedBy());
	            	ps.setString(3, sim.getSimNumber());
	            }
	        });
	    	sql = new StringBuilder();
	    	sql.append("DELETE FROM ").append(DBConstants.SIM_TMP).append(" WHERE SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new Object[] { sim.getSimNumber() });
	    	transactionManager.commit(status);
	    }catch (Exception e) {
        	transactionManager.rollback(status);
			logger.error(e);
        	throw e;
        }
    }
	
	@Override
	public void copyToSimTmp(Sim sim) {
		try{
    		StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ").append(DBConstants.SIM_TMP).append(" (SIM_NUMBER, CREDIT_TERM, PRICE, SUM_NUMBER, OPERATION_FLAG, ACTIVE_STATUS, BOOKING_FLAG, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY) ");
			sql.append(" SELECT SIM_NUMBER, ?, ?, SUM_NUMBER, ?, ?, BOOKING_FLAG, CREATED_DATE, CREATED_BY, SYSDATE(), ? FROM ");
			sql.append(DBConstants.SIM_MST);
			sql.append(" WHERE SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getCreditTerm());
	            	ps.setBigDecimal(2, sim.getPrice());
	            	ps.setString(3, sim.getOperationFlag());
	            	ps.setString(4, sim.getActiveStatus());
	            	ps.setString(5, sim.getLastUpdatedBy());
	            	ps.setString(6, sim.getSimNumber());
	        	 }
	        });
		}catch (Exception e) {
        	logger.error(e);
        	throw e;
	    }
	}

	@Override
	public void copyToSimTmpDelete(Sim sim) {
		try{
    		StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ").append(DBConstants.SIM_TMP).append(" (SIM_NUMBER, CREDIT_TERM, PRICE, SUM_NUMBER, OPERATION_FLAG, ACTIVE_STATUS, BOOKING_FLAG, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY, RECIEVED_DATE) ");
			sql.append(" SELECT SIM_NUMBER, CREDIT_TERM, PRICE, SUM_NUMBER, ?, ?, BOOKING_FLAG, CREATED_DATE, CREATED_BY, SYSDATE(), ?, RECIEVED_DATE FROM ");
			sql.append(DBConstants.SIM_MST);
			sql.append(" WHERE SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getOperationFlag());
	            	ps.setString(2, sim.getActiveStatus());
	            	ps.setString(3, sim.getLastUpdatedBy());
	            	ps.setString(4, sim.getSimNumber());
	        	 }
	        });
		}catch (Exception e) {
        	logger.error(e);
        	throw e;
	    }
	}
	
	@Override
	public void updateSimTmp(Sim sim) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("UPDATE ").append(DBConstants.SIM_TMP);
			sql.append(" SET CREDIT_TERM = ?, PRICE = ?, ACTIVE_STATUS = ?, OPERATION_FLAG = ?, LAST_UPDATED_DATE = SYSDATE(), LAST_UPDATED_BY = ?");
	    	sql.append(" WHERE SIM_NUMBER = ? ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1,  sim.getCreditTerm());
	            	ps.setBigDecimal(2, sim.getPrice());
	            	ps.setString(3, sim.getActiveStatus());
	            	ps.setString(4, sim.getOperationFlag());
	            	ps.setString(5, sim.getLastUpdatedBy());
	            	ps.setString(6, sim.getSimNumber());
	            }
	        });
	    }catch (Exception e) {
        	logger.error(e);
        	throw e;
		}
	}
	
	@Override
	@Transactional
	public void copyToSimMst(Sim sim) {
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
        try{
    		StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ").append(DBConstants.SIM_MST).append(" (SIM_NUMBER, CREDIT_TERM, PRICE, RECIEVED_DATE, SUM_NUMBER, OPERATION_FLAG, ACTIVE_STATUS, BOOKING_FLAG, REJECT_REASON, CREATED_DATE, CREATED_BY, AUTHORIZED_DATE, AUTHORIZED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY) ");
			sql.append(" SELECT SIM_NUMBER, CREDIT_TERM, PRICE, RECIEVED_DATE, SUM_NUMBER, ?, ?, BOOKING_FLAG, NULL, CREATED_DATE, CREATED_BY, SYSDATE(), ?, LAST_UPDATED_DATE, LAST_UPDATED_BY FROM ");
			sql.append(DBConstants.SIM_TMP);
			sql.append(" WHERE SIM_NUMBER = ? ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getOperationFlag());
	            	ps.setString(2, sim.getActiveStatus());
	            	ps.setString(3, sim.getLastUpdatedBy());
	            	ps.setString(4, sim.getSimNumber());
	            }
	        });
			sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.SIM_TMP).append(" WHERE SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new Object[] { sim.getSimNumber() });
	    	transactionManager.commit(status);
		}catch (Exception e) {
			logger.error(e);
        	throw e;
	    }
	}
	
	@Override
	public List<RequestMst> SearchSimRequestDataTable(SearchDataTable<RequestMst> searchDataTable) {
		List<RequestMst> result = null;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		StringBuilder order = new StringBuilder();
		String[] orderColumns = null;
		try {
			orderColumns = new String[]{"a.REQUEST_ID", "a.REQUEST_DATE", "a.REQUEST_STATUS", "a.MERCHANT_ID"};
			sql.append(" SELECT a.REQUEST_ID, a.REQUEST_TYPE, a.REQUEST_VALUE, a.REQUEST_DATE, a.REQUEST_STATUS,  a.MERCHANT_ID, a.REJECT_REASON, b.SIM_NUMBER, b.PRICE, b.RECIEVED_DATE, b.CREDIT_TERM, b.REQUEST_STATUS SIM_STATUS, b.SUM_NUMBER, c.DESCRIPTION FROM ");
			sql.append(DBConstants.REQUEST_MST).append(" a LEFT JOIN ").append(DBConstants.REQUEST_SIM).append(" b ON a.REQUEST_ID = b. REQUEST_MST_ID");
			sql.append(" LEFT JOIN ").append(DBConstants.MASTER_SETUP).append(" c ON c.GROUP_TYPE = 'REQUEST_TYPE' AND a.REQUEST_TYPE = c.ID ");
			
			if(searchDataTable.getDataSearch().getRequestSim() != null) {
				if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestSim().get(0).getSimNumber())){
					where.add(" b.SIM_NUMBER = ?");
				}
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getMerchantId())){
				where.add(" a.MERCHANT_ID = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestStatus())){
				where.add(" a.REQUEST_STATUS = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestType())){
				where.add(" a.REQUEST_TYPE = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestValue())){
				where.add(" a.REQUEST_VALUE = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getMerchantId())){
				where.add(" a.MERCHANT_ID = ?");
			}
			/*if(searchDataTable.getDataSearch().getExceptSimNumber() != null){
				String str = "";
				for(int i = 0; i < searchDataTable.getDataSearch().getExceptSimNumber().size(); i++) {
					str += "?,";
				}
				where.add(" a.SIM_NUMBER NOT IN (" + str.substring(0, str.length() - 1) + ")");
			}*/
			if(searchDataTable.getDataSearch().getRequestSim() != null){
				String str = "";
				for(int i = 0; i < searchDataTable.getDataSearch().getRequestSim().get(0).getExceptStatus().size(); i++) {
					str += "?,";
				}
				where.add(" a.REQUEST_STATUS NOT IN (" + str.substring(0, str.length() - 1) + ")");
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
			result = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;
	            	if(searchDataTable.getDataSearch().getRequestSim() != null) {
	    				if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestSim().get(0).getSimNumber())){
	    					preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestSim().get(0).getSimNumber());
	    				}
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getMerchantId())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getMerchantId());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestStatus())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestStatus());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestType())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestType());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestValue())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestValue());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getMerchantId())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getMerchantId());
	            	}
	            	if(searchDataTable.getDataSearch().getRequestSim() != null){
	            		for(String sim : searchDataTable.getDataSearch().getRequestSim().get(0).getExceptStatus()) {
	            			preparedStatement.setString(i++, sim);
	            		}
	            	}
	            }
	        }, new RowMapper<RequestMst>() {
					@Override
					public RequestMst mapRow(ResultSet rs, int numRow) throws SQLException {
						RequestMst requestMst = new RequestMst();
						requestMst.setRequestId(rs.getString("REQUEST_ID"));
						requestMst.setRequestValue(rs.getString("REQUEST_VALUE"));
						requestMst.setRequestType(rs.getString("REQUEST_TYPE"));
						requestMst.setRequestStatus(rs.getString("REQUEST_STATUS"));
						requestMst.setRequestDate(rs.getDate("REQUEST_DATE"));
						requestMst.setRequestTypeStr(rs.getString("DESCRIPTION"));
						requestMst.setMerchantId(rs.getString("MERCHANT_ID"));
						requestMst.setRejectReason(rs.getString("REJECT_REASON"));
						List<RequestSim> list = new ArrayList<>();
						if(StringUtils.isNotBlank(rs.getString("SIM_NUMBER"))) {
							RequestSim sim = new RequestSim();
							sim.setCreditTerm(rs.getString("CREDIT_TERM"));
							sim.setPrice(rs.getBigDecimal("PRICE"));
							sim.setSimNumber(rs.getString("SIM_NUMBER"));
							sim.setRecievedDate(rs.getDate("RECIEVED_DATE"));
							sim.setRequestStatus(rs.getString("SIM_STATUS"));
							sim.setSumNumber(rs.getInt("SUM_NUMBER"));
							list.add(sim);
						}
						requestMst.setRequestSim(list);
						return requestMst;
					}
			    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public int CountRequestSimDataTableFilter(SearchDataTable<RequestMst> searchDataTable) {
		List<RequestMst> results = null;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		try {
			sql.append(" SELECT a.REQUEST_ID, a.REQUEST_TYPE, a.REQUEST_VALUE, a.REQUEST_DATE, a.REQUEST_STATUS, b.SIM_NUMBER, b.PRICE, b.RECIEVED_DATE, b.CREDIT_TERM, b.REQUEST_STATUS SIM_STATUS, b.SUM_NUMBER FROM ");
			sql.append(DBConstants.REQUEST_MST).append(" a LEFT JOIN ").append(DBConstants.REQUEST_SIM).append(" b ON a.REQUEST_ID = b. REQUEST_MST_ID");
			
			if(searchDataTable.getDataSearch().getRequestSim() != null) {
				if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestSim().get(0).getSimNumber())){
					where.add(" b.SIM_NUMBER = ?");
				}
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getMerchantId())){
				where.add(" a.MERCHANT_ID = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestStatus())){
				where.add(" a.REQUEST_STATUS = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestType())){
				where.add(" a.REQUEST_TYPE = ?");
			}
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestValue())){
				where.add(" a.REQUEST_VALUE = ?");
			}
			if(searchDataTable.getDataSearch().getRequestSim() != null){
				String str = "";
				for(int i = 0; i < searchDataTable.getDataSearch().getRequestSim().get(0).getExceptStatus().size(); i++) {
					str += "?,";
				}
				where.add(" a.REQUEST_STATUS NOT IN (" + str.substring(0, str.length() - 1) + ")");
			}
			if(!where.isEmpty()){
				sql.append(" WHERE ");
				for(String strWhere : where){
					sql.append(strWhere).append(" AND ");
				}
				sql = new StringBuilder(sql.substring(0, sql.length() - 4));
			}
			results = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;
	            	if(searchDataTable.getDataSearch().getRequestSim() != null) {
	    				if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestSim().get(0).getSimNumber())){
	    					preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestSim().get(0).getSimNumber());
	    				}
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getMerchantId())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getMerchantId());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestStatus())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestStatus());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestType())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestType());
	            	}
	            	if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getRequestValue())){
	            		preparedStatement.setString(i++, searchDataTable.getDataSearch().getRequestValue());
	            	}
	            	if(searchDataTable.getDataSearch().getRequestSim() != null){
	            		for(String sim : searchDataTable.getDataSearch().getRequestSim().get(0).getExceptStatus()) {
	            			preparedStatement.setString(i++, sim);
	            		}
	            	}
	            }
	        }, new RowMapper<RequestMst>() {
				 
					@Override
					public RequestMst mapRow(ResultSet rs, int numRow) throws SQLException {
						RequestMst corp = new RequestMst();
						corp.setRequestId(rs.getString("REQUEST_ID"));
						return corp;
					}
			 
			    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return (results == null) ? 0 : results.size();
	}
	
	@Override
	public int countRequestSimTotalDataTable(SearchDataTable<RequestMst> searchDataTable) {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		try {
			sql.append("SELECT COUNT(0) FROM ");
			sql.append(DBConstants.REQUEST_MST);
			sql.append(" WHERE MERCHANT_ID = ? ");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { 
					searchDataTable.getDataSearch().getMerchantId() }, Integer.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public void saveRequestSim(RequestMst sim) {
		try{
			String requestId = generateRequestId();
    		StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ").append(DBConstants.REQUEST_MST).append(" (REQUEST_ID, REQUEST_TYPE, REQUEST_VALUE, REQUEST_DATE, REQUEST_STATUS, MERCHANT_ID, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY) VALUES ");
			sql.append(" (?, ?, ?, SYSDATE(), 'W', ?, SYSDATE(), ?, SYSDATE(), ?)");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, requestId);
	            	ps.setString(2, sim.getRequestType());
	            	ps.setString(3, sim.getRequestValue());
	            	ps.setString(4, sim.getMerchantId());
	            	ps.setString(5, sim.getCreatedBy());
	            	ps.setString(6, sim.getLastUpdatedBy());
	            }
	        });
	    }catch (Exception e) {
			logger.error(e);
        	throw e;
        }
	}
	
	@Override
	public int checkSimNumberBeforeRequest(String sim) {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		try {
			sql.append("SELECT COUNT(0) FROM (");
			sql.append(" SELECT SIM_NUMBER FROM ").append(DBConstants.REQUEST_SIM).append(" WHERE SIM_NUMBER = ? "); 
			sql.append(" UNION SELECT SIM_NUMBER FROM ").append(DBConstants.SIM_MST).append(" WHERE SIM_NUMBER = ? "); 
			sql.append(" UNION SELECT SIM_NUMBER FROM ").append(DBConstants.SIM_TMP).append(" WHERE SIM_NUMBER = ?) a ");  
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { 
					sim, sim , sim 
					}, Integer.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public int checkDuplicateSimNumber(String sim) {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		try {
			sql.append("SELECT COUNT(0) FROM (");
			sql.append(" UNION SELECT SIM_NUMBER FROM ").append(DBConstants.SIM_MST).append(" WHERE SIM_NUMBER = ? "); 
			sql.append(" UNION SELECT SIM_NUMBER FROM ").append(DBConstants.SIM_TMP).append(" WHERE SIM_NUMBER = ?) a ");  
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { 
					sim, sim , sim 
					}, Integer.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public void cancelRequestBySim(RequestSim sim) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("UPDATE ").append(DBConstants.REQUEST_SIM);
			sql.append(" SET REQUEST_STATUS = 'C', LAST_UPDATED_DATE = SYSDATE(), LAST_UPDATED_BY = ?");
	    	sql.append(" WHERE SIM_NUMBER = ? ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getLastUpdatedBy());
	            	ps.setString(2, sim.getSimNumber());
	            }
	        });
	    }catch (Exception e) {
        	logger.error(e);
        	throw e;
		}
	}
	
	@Override
	public void cancelRequestSim(RequestMst sim) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("UPDATE ").append(DBConstants.REQUEST_MST);
			sql.append(" SET REQUEST_STATUS = 'C', LAST_UPDATED_DATE = SYSDATE(), LAST_UPDATED_BY = ?");
	    	sql.append(" WHERE REQUEST_ID = ? ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getLastUpdatedBy());
	            	ps.setString(2, sim.getRequestId());
	            }
	        });
	    }catch (Exception e) {
        	logger.error(e);
        	throw e;
		}
	}
	
	@Override
	public void deleteRequestSim(RequestSim sim) {
		try{
    		StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.REQUEST_SIM).append(" WHERE SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getSimNumber());
	            }
	        });
		}catch (Exception e) {
			logger.error(e);
        	throw e;
	    }
	}
	
	@Override
	public void updateRequestSim(RequestSim sim) {
		try{
    		StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.REQUEST_SIM).append(" SET PRICE = ?, CREDIT_TERM = ? , REQUEST_STATUS = ?, LAST_UPDATED_BY = ?, LAST_UPDATED_DATE = SYSDATE() WHERE SIM_NUMBER = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setBigDecimal(1, sim.getPrice());
	            	ps.setString(2, sim.getCreditTerm());
	            	ps.setString(3, sim.getRequestStatus());
	            	ps.setString(4, sim.getLastUpdatedBy());
	            	ps.setString(5, sim.getSimNumber());
	            }
	        });
		}catch (Exception e) {
			logger.error(e);
        	throw e;
	    }
	}
	
	@Override
	public Sim checkDuplicateSimBeforeAddSimNumber(String simNumber) {
		Sim result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("  SELECT * FROM (SELECT SIM_NUMBER, 'S' FLAG_SIM FROM ").append(DBConstants.SIM_MST).append(" WHERE SIM_NUMBER = ? UNION ");
			sql.append(" SELECT SIM_NUMBER, 'S' FLAG_SIM FROM ").append(DBConstants.SIM_TMP).append(" WHERE SIM_NUMBER = ? UNION ");
			sql.append(" SELECT SIM_NUMBER, 'R' FLAG_SIM  FROM ").append(DBConstants.REQUEST_SIM).append(" WHERE SIM_NUMBER = ?  ) a LIMIT 1");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[]{ simNumber, simNumber, simNumber }, new RowMapper<Sim>() {
					@Override
					public Sim mapRow(ResultSet rs, int numRow) throws SQLException {
						Sim sim = new Sim();
						sim.setSimNumber(rs.getString("SIM_NUMBER"));
						sim.setFlagSim(rs.getString("FLAG_SIM"));
						return sim;
					}
			 
			    });
		} catch(EmptyResultDataAccessException e) {
			return null;
		}catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public String generateRequestId() {
		StringBuilder sql = new StringBuilder();
		String result = "";
		try {
			sql.append("SELECT CONCAT('RE', DATE_FORMAT(SYSDATE(),'%Y%m%d'), CAST(a.NUMBER AS INT) + 1) ID FROM (");
			sql.append(" SELECT CASE WHEN POSITION(CONCAT('RE', DATE_FORMAT(SYSDATE(),'%Y%m%d')) IN (SELECT REQUEST_ID FROM ").append(DBConstants.REQUEST_MST).append(" ORDER BY REQUEST_ID DESC LIMIT 1)) > 0 "); 
			sql.append(" THEN REPLACE((SELECT REQUEST_ID FROM ").append(DBConstants.REQUEST_MST).append(" ORDER BY REQUEST_ID DESC LIMIT 1), CONCAT('RE', DATE_FORMAT(SYSDATE(),'%Y%m%d')), '') ");
			sql.append(" ELSE 0 END NUMBER) a ");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { }, String.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}


	@Override
	public void insertRequestSimData(RequestSim sim) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try{
			sql.append("  INSERT INTO ").append(DBConstants.REQUEST_SIM); 
			sql.append("  (SIM_NUMBER, REQUEST_MST_ID, REQUEST_STATUS, AUTHORIZED_DATE, AUTHORIZED_BY, CREDIT_TERM, PRICE, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY, SUM_NUMBER)  "); 
			sql.append("  VALUES ( ?, ?, ?, SYSDATE(), ?, ?, ? , SYSDATE() , ? , SYSDATE(), ?, ? )  ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, sim.getSimNumber());
					ps.setString(2, sim.getRequestMstId());
					ps.setString(3, "A");
					ps.setString(4, sim.getAuthorizedBy());
					ps.setString(5, sim.getCreditTerm());
					ps.setBigDecimal(6, sim.getPrice());
					ps.setString(7, sim.getCreatedBy());
					ps.setString(8, sim.getLastUpdatedBy());
					ps.setInt(9, sim.getSumNumber());
				}
			});
			sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.REQUEST_MST).append(" SET REQUEST_STATUS = 'A', AUTHORIZED_BY = ?, AUTHORIZED_DATE = SYSDATE() WHERE REQUEST_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, sim.getAuthorizedBy());
	            	ps.setString(2, sim.getRequestMstId());
	            }
	        });
			transactionManager.commit(status);
	    }catch (Exception e) {
        	transactionManager.rollback(status);
			logger.error(e);
        	throw e;
        }
	}
}
