package com.sim.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.BookingResponse;
import com.sim.api.model.Sim;
import com.sim.api.utils.DBConstants;

@Repository
public class BookingDaoIml implements BookingDao {

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
	public String insertBooking(List<Sim> sims) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		StringBuilder sql = new StringBuilder();
		String result = "";
		try{
			String bookingId = generateBookingId();
			sql = new StringBuilder();
			sql.append("  INSERT INTO ").append(DBConstants.BOOKING);
			sql.append("  (BOOKING_ID, BOOKING_STATUS, MERCHANT_ID, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY, PAY_TYPE, BANK, ADDRESS, PROVINCE, POSTCODE) VALUES ");
			sql.append(" (?, 'P', ?, SYSDATE(), ?, SYSDATE(), ?, ?, ?, ?, ?, ?)"); 
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, bookingId);
					ps.setString(2, sims.get(0).getCreatedBy());
					ps.setString(3, sims.get(0).getCreatedBy());
					ps.setString(4, sims.get(0).getCreatedBy());
					ps.setString(5, sims.get(0).getPayType());
					ps.setString(6, sims.get(0).getBank());
					ps.setString(7, sims.get(0).getAddress());
					ps.setString(8, sims.get(0).getProvince());
					ps.setString(9, sims.get(0).getPostcode());
				}
			});
			for(Sim item : sims){
				String bookingDetailId = generateBookingDetailId();
				if("S".equals(item.getFlagSim())) {
					sql = new StringBuilder();
					sql.append("  INSERT INTO ").append(DBConstants.BOOKING_DETAIL);
					sql.append("  (BOOKING_DETAIL_ID, BOOKING_ID, SIM_NUMBER, ACTIVATE_FLAG, BOOKING_STATUS, PRICE, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY)");
					sql.append(" SELECT ?, ?, SIM_NUMBER, 'N', 'P', PRICE, SYSDATE(), ?, SYSDATE(), ? FROM ").append(DBConstants.SIM_MST).append(" WHERE SIM_NUMBER = ?"); 
					jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, bookingDetailId);
							ps.setString(2, bookingId);
							ps.setString(3, item.getCreatedBy());
							ps.setString(4, item.getLastUpdatedBy());
							ps.setString(5, item.getSimNumber());
						}
					});
					sql = new StringBuilder();
					sql.append("  UPDATE ").append(DBConstants.SIM_MST);
					sql.append("  SET BOOKING_FLAG = 'Y', LAST_UPDATED_DATE = SYSDATE(), LAST_UPDATED_BY = ? WHERE SIM_NUMBER = ?");
					jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, item.getLastUpdatedBy());
							ps.setString(2, item.getSimNumber());
						}
					});
				}else if("R".equals(item.getFlagSim())){
					sql = new StringBuilder();
					sql.append("  INSERT INTO ").append(DBConstants.SIM_MST);
					sql.append("  (SIM_NUMBER, CREDIT_TERM, PRICE, OPERATION_FLAG, ACTIVE_STATUS, BOOKING_FLAG, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY)");
					sql.append(" SELECT SIM_NUMBER, CREDIT_TERM, PRICE, 'N', 'Y', 'Y', SYSDATE(), ?, SYSDATE(), ? FROM ").append(DBConstants.REQUEST_SIM).append(" WHERE SIM_NUMBER = ?"); 
					jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, item.getCreatedBy());
							ps.setString(2, item.getLastUpdatedBy());
							ps.setString(3, item.getSimNumber());
						}
					});
					sql = new StringBuilder();
					sql.append("  DELETE FROM ").append(DBConstants.REQUEST_SIM).append(" WHERE SIM_NUMBER = ?");
					jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, item.getSimNumber());
						}
					});
					sql = new StringBuilder();
					sql.append("  INSERT INTO ").append(DBConstants.BOOKING_DETAIL);
					sql.append("  (BOOKING_DETAIL_ID, BOOKING_ID, SIM_NUMBER, ACTIVATE_FLAG, BOOKING_STATUS, PRICE, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY)");
					sql.append(" SELECT ?, ?, SIM_NUMBER, 'N', 'P', PRICE, SYSDATE(), ?, SYSDATE(), ? FROM ").append(DBConstants.SIM_MST).append(" WHERE SIM_NUMBER = ?"); 
					jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setString(1, bookingDetailId);
							ps.setString(2, bookingId);
							ps.setString(3, item.getCreatedBy());
							ps.setString(4, item.getLastUpdatedBy());
							ps.setString(5, item.getSimNumber());
						}
					});
				}
			}
			sql = new StringBuilder();
			sql.append("  UPDATE ").append(DBConstants.BOOKING);
			sql.append("  SET SUM_PRICE = ");
			sql.append(" (SELECT SUM(PRICE) FROM ").append(DBConstants.BOOKING_DETAIL).append(" WHERE BOOKING_ID = ? ) WHERE BOOKING_ID = ? "); 
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, bookingId);
					ps.setString(2, bookingId);
				}
			});
			transactionManager.commit(status);
			result = bookingId;
	    }catch (Exception e) {
	    	transactionManager.rollback(status);
	    	logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public void insertBookingDetail(BookingDetail bookingDetail) {
		StringBuilder sql = new StringBuilder();
		try{
			sql.append("  INSERT INTO ").append(DBConstants.BOOKING_DETAIL);
			sql.append("  (BOOKING_DETAIL_ID, BOOKING_ID, SIM_NUMBER, ACTIVATE_FLAG, BOOKING_STATUS, PRICE, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY)");
			sql.append(" SELECT ?, ?, SIM_NUMBER, 'N', 'P', ?, SYSDATE(), ?, SYSDATE(), ? FROM SIM WHERE SIM_NUMBER = ?"); 
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, bookingDetail.getBookingDetailId());
					ps.setString(2, bookingDetail.getBookingId());
					ps.setBigDecimal(3, bookingDetail.getPrice());
					ps.setString(4, bookingDetail.getCreatedBy());
					ps.setString(5, bookingDetail.getLastUpdatedBy());
					ps.setString(6, bookingDetail.getSimNumber());
				}
			});
	    }catch (Exception e) {
	    	logger.error(e);
    		throw e;
        }
	}
	
	@Override
	public String generateBookingId() {
		StringBuilder sql = new StringBuilder();
		String result = "";
		try {
			sql.append("SELECT CONCAT(DATE_FORMAT(SYSDATE(),'%Y%m%d'), CAST(a.NUMBER AS INT) + 1) ID FROM (");
			sql.append(" SELECT CASE WHEN POSITION(DATE_FORMAT(SYSDATE(),'%Y%m%d') IN (SELECT BOOKING_ID FROM ").append(DBConstants.BOOKING).append(" ORDER BY BOOKING_ID DESC LIMIT 1)) > 0 "); 
			sql.append(" THEN REPLACE((SELECT BOOKING_ID FROM ").append(DBConstants.BOOKING).append(" ORDER BY BOOKING_ID DESC LIMIT 1), DATE_FORMAT(SYSDATE(),'%Y%m%d'), '') ");
			sql.append(" ELSE 0 END NUMBER) a ");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { }, String.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public String generateBookingDetailId() {
		StringBuilder sql = new StringBuilder();
		String result = "";
		try {
			sql.append("SELECT CONCAT(DATE_FORMAT(SYSDATE(),'%Y%m%d'), CAST(a.NUMBER AS INT) + 1) ID FROM (");
			sql.append(" SELECT CASE WHEN POSITION(DATE_FORMAT(SYSDATE(),'%Y%m%d') IN (SELECT BOOKING_DETAIL_ID FROM ").append(DBConstants.BOOKING_DETAIL).append(" ORDER BY BOOKING_DETAIL_ID DESC LIMIT 1)) > 0 "); 
			sql.append(" THEN REPLACE((SELECT BOOKING_DETAIL_ID FROM ").append(DBConstants.BOOKING_DETAIL).append(" ORDER BY BOOKING_DETAIL_ID DESC LIMIT 1), DATE_FORMAT(SYSDATE(),'%Y%m%d'), '') ");
			sql.append(" ELSE 0 END NUMBER) a ");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { }, String.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}	
	
	@Override
	public List<BookingResponse> searchBooking(Booking search) {
		List<BookingResponse> result = null;
		try {
			List<String> where = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.BOOKING_ID, a.BOOKING_STATUS,");
			sql.append(" a.SUM_PRICE, b.BOOKING_DETAIL_ID, b.SIM_NUMBER, b.ACTIVATE_FLAG, a.MERCHANT_ID, ");
			sql.append(" b.EFFECTIVE_DATE, b.PRICE, b.CUSTOMER_ID, a.REJECT_REASON, a.SLIP FROM ").append(DBConstants.BOOKING).append(" a LEFT JOIN ").append(DBConstants.BOOKING_DETAIL);
			sql.append(" b ON a.BOOKING_ID = b.BOOKING_ID ");
			if(StringUtils.isNotBlank(search.getMerchantId())){
				where.add(" a.MERCHANT_ID = ?");
			}
			if(StringUtils.isNotBlank(search.getBookingId())){
				where.add(" a.BOOKING_ID LIKE ?");
			}
			if(search.getBookingDetails() != null){
				if(StringUtils.isNotBlank(search.getBookingDetails().get(0).getSimNumber())) {
					where.add(" b.SIM_NUMBER LIKE ?");
				}
			}
			if(!where.isEmpty()){
				sql.append(" WHERE ");
				for(String strWhere : where){
					sql.append(strWhere).append(" AND ");
				}
				sql = new StringBuilder(sql.substring(0, sql.length() - 4));
			}
			sql.append(" ORDER BY a.BOOKING_ID DESC");
			result = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	int i = 1;
	                if (StringUtils.isNotBlank(search.getMerchantId())) {
	                    preparedStatement.setString(i++, search.getMerchantId());
	                }
	                if (StringUtils.isNotBlank(search.getBookingId())) {
	                	 preparedStatement.setString(i++, "%" + search.getBookingId() + "%");
	                }
	                if(search.getBookingDetails() != null){
	    				if(StringUtils.isNotBlank(search.getBookingDetails().get(0).getSimNumber())) {
	    					 preparedStatement.setString(i++, "%" + search.getBookingDetails().get(0).getSimNumber() + "%");
	    				}
	    			}
	            }
	        }, new RowMapper<BookingResponse>() {
					@Override
					public BookingResponse mapRow(ResultSet rs, int numRow) throws SQLException {
						BookingResponse b = new BookingResponse();
						b.setActivateFlag(rs.getString("ACTIVATE_FLAG"));
						b.setBookingDetailId(rs.getString("BOOKING_DETAIL_ID"));
						b.setBookingId(rs.getString("BOOKING_ID"));
						b.setBookingStatus(rs.getString("BOOKING_STATUS"));
						b.setCustomerId(rs.getString("CUSTOMER_ID"));
						b.setEffectiveDate(rs.getDate("EFFECTIVE_DATE"));
						b.setPrice(rs.getBigDecimal("PRICE"));
						b.setSimNumber(rs.getString("SIM_NUMBER"));
						b.setSumPrice(rs.getBigDecimal("SUM_PRICE"));
						b.setSlip(rs.getString("SLIP"));
						b.setRejectReson(rs.getString("REJECT_REASON"));
						b.setMerchantId(rs.getString("MERCHANT_ID"));
						return b;
					}
			    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public void updateAutorizeStatus(Booking booking) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try {
			sql.append("UPDATE ").append(DBConstants.BOOKING).append(" SET BOOKING_STATUS = ?, AUTHORIZED_DATE = SYSDATE(), AUTHORIZED_BY = ?, REJECT_REASON = (CASE WHEN ? = 'N' THEN ? ELSE NULL END)");
			sql.append(" WHERE BOOKING_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, booking.getBookingStatus());
	            	ps.setString(2, booking.getAuthorizedBy());
	            	ps.setString(3, booking.getBookingStatus());
	            	ps.setString(4, booking.getRejectReason());
	            	ps.setString(5, booking.getBookingId());
	            }
	        });
			sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.BOOKING_DETAIL).append(" SET BOOKING_STATUS = ?");
			sql.append(" WHERE BOOKING_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, booking.getBookingStatus());
	            	ps.setString(2, booking.getBookingId());
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
	public void updateStatusUploadFile(Booking booking) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try {
			sql.append("UPDATE ").append(DBConstants.BOOKING).append(" SET BOOKING_STATUS = ?, LAST_UPDATED_DATE = SYSDATE(), LAST_UPDATED_BY = ?, SLIP = ?");
			sql.append(" WHERE BOOKING_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, booking.getBookingStatus());
	            	ps.setString(2, booking.getLastUpdatedBy());
	            	ps.setString(3, booking.getSlip());
	            	ps.setString(4, booking.getBookingId());
	            }
	        });
			sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.BOOKING_DETAIL).append(" SET BOOKING_STATUS = ?");
			sql.append(" WHERE BOOKING_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, booking.getBookingStatus());
	            	ps.setString(2, booking.getBookingId());
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
	public void updateStatusUploadIdCard(BookingDetail bookingDetail) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try {
			sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.BOOKING_DETAIL).append(" SET ACTIVATE_FLAG = 'W', CUSTOMER_ID = ?, LAST_UPDATED_DATE = SYSDATE(), LAST_UPDATED_BY = ?");
			sql.append(" WHERE BOOKING_DETAIL_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, bookingDetail.getCustomerId());
	            	ps.setString(2, bookingDetail.getLastUpdatedBy());
	            	ps.setString(3, bookingDetail.getBookingDetailId());	
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
	public void approveStatusIdCard(BookingDetail bookingDetail) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try {
			sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.BOOKING_DETAIL).append(" SET ACTIVATE_FLAG = 'Y', REJECT_REASON = NULL, AUTHORIZED_DATE = SYSDATE(), AUTHORIZED_BY = ?");
			sql.append(" WHERE BOOKING_DETAIL_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, bookingDetail.getAuthorizedBy());
	            	ps.setString(2, bookingDetail.getBookingDetailId());	
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
	public void rejectStatusIdCard(BookingDetail bookingDetail) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try {
			sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.BOOKING_DETAIL).append(" SET ACTIVATE_FLAG = 'N', REJECT_REASON = ?, AUTHORIZED_DATE = SYSDATE(), AUTHORIZED_BY = ?");
			sql.append(" WHERE BOOKING_DETAIL_ID = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, bookingDetail.getRejectReason());
	            	ps.setString(2, bookingDetail.getAuthorizedBy());
	            	ps.setString(3, bookingDetail.getBookingDetailId());	
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
	public String getSlipByBookingId(String userId, String bookingId) {
		String result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT SLIP");
			sql.append(" FROM ").append(DBConstants.BOOKING);
			sql.append(" WHERE MERCHANT_ID = ? AND BOOKING_ID = ?");
			result = (String) jdbcTemplate.queryForObject(sql.toString(), new Object[] { userId, bookingId }, String.class);
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public String getIdCardByBookingDetailId(String userId, String bookingDetailId) {
		String result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CUSTOMER_ID");
			sql.append(" FROM ").append(DBConstants.BOOKING_DETAIL);
			sql.append(" WHERE LAST_UPDATED_BY = ? AND BOOKING_DETAIL_ID = ?");
			result = (String) jdbcTemplate.queryForObject(sql.toString(), new Object[] { userId, bookingDetailId }, String.class);
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public Booking getBookingByBookingId(String bookingId) {
		Booking result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ");
			sql.append(DBConstants.BOOKING);
			sql.append(" WHERE BOOKING_ID = ? ");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { bookingId }, new RowMapper<Booking>() {
					@Override
					public Booking mapRow(ResultSet rs, int numRow) throws SQLException {
						Booking b = new Booking();
						b.setBookingId(rs.getString("BOOKING_ID"));
						b.setMerchantId(rs.getString("MERCHANT_ID"));
						b.setBookingStatus(rs.getString("BOOKING_STATUS"));
						b.setBookingStatus(rs.getString("BOOKING_STATUS"));
						b.setSumPrice(rs.getBigDecimal("SUM_PRICE"));
						b.setSlip(rs.getString("SLIP"));
						b.setPayType(rs.getString("PAY_TYPE"));
						b.setBank(rs.getString("BANK"));
						b.setAddress(rs.getString("ADDRESS"));
						b.setProvince(rs.getString("PROVINCE"));
						b.setPostcode(rs.getString("POSTCODE"));
						b.setRejectReason(rs.getString("REJECT_REASON"));
						return b;
					}
			    });
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public List<BookingDetail> getBookingDetailByBookingId(String bookingId) {
		List<BookingDetail> result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.*, b.CREDIT_TERM FROM ");
			sql.append(DBConstants.BOOKING_DETAIL).append(" a INNER JOIN ").append(DBConstants.SIM_MST).append(" b ON a.SIM_NUMBER = b.SIM_NUMBER");
			sql.append(" WHERE BOOKING_ID = ? ");
			result = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement preparedStatement) throws
	                    SQLException {
	            	preparedStatement.setString(1, bookingId);
	            }
	        }, new RowMapper<BookingDetail>() {
					@Override
					public BookingDetail mapRow(ResultSet rs, int numRow) throws SQLException {
						BookingDetail b = new BookingDetail();
						b.setBookingId(rs.getString("BOOKING_ID"));
						b.setBookingDetailId(rs.getString("BOOKING_DETAIL_ID"));
						b.setSimNumber(rs.getString("SIM_NUMBER"));
						b.setActivateFlag(rs.getString("ACTIVATE_FLAG"));
						b.setEffectiveDate(rs.getDate("EFFECTIVE_DATE"));
						b.setPrice(rs.getBigDecimal("PRICE"));
						b.setCustomerId(rs.getString("CUSTOMER_ID"));
						b.setCreditTerm(rs.getInt("CREDIT_TERM"));
						return b;
					}
			    });
			
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	

	@Override
	public void cancelBooking(String bookingId, String userId) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try {
			sql = new StringBuilder();
			sql.append("UPDATE ").append(DBConstants.SIM_MST).append(" SET BOOKING_FLAG = 'N'");
			sql.append(" WHERE SIM_NUMBER = (SELECT SIM_NUMBER FROM ").append(DBConstants.BOOKING_DETAIL).append(" WHERE BOOKING_ID = ? AND LAST_UPDATED_BY = ?)");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, bookingId);
	            	ps.setString(2, userId);
	            }
	        });
			sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.BOOKING_DETAIL).append(" WHERE BOOKING_ID = ? AND LAST_UPDATED_BY = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, bookingId);
	            	ps.setString(2, userId);
	            }
	        });
			sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.BOOKING).append(" WHERE BOOKING_ID = ? AND LAST_UPDATED_BY = ?");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	            	ps.setString(1, bookingId);
	            	ps.setString(2, userId);
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
