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

import com.sim.api.datatable.Order;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.RoleMst;
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
			sql.append(" LIMIT ").append(36).append(" OFFSET ").append((page - 1) * 36);
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
	
	@Override
	public void insertSim(Sim sim) {
		StringBuilder sql = new StringBuilder();
		try{
			sql.append("  INSERT INTO ").append(DBConstants.SIM); 
			sql.append("  (SIM_NUMBER, PERIOD_TYPE, PRICE, ACTIVE_STATUS, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY)  "); 
			sql.append("  VALUES ( ?, ?, ?, ? , SYSDATE() , ? , SYSDATE(), ? )  ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, sim.getSimNumber());
					ps.setString(2, sim.getPeriodType());
					ps.setBigDecimal(3, sim.getPrice());
					ps.setString(4, sim.getActiveStatus());
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
	public List<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable) {
		List<Sim> result = null;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		StringBuilder order = new StringBuilder();
		String[] orderColumns = null;
		try {
			orderColumns = new String[]{"a.SIM_NUMBER", "a.PRICE", "a.EFFECTIVE_DATE", "b.BOOKING_STATUS", "b.MERCHANT_ID", "a.ACTIVE_STATUS"};
			sql.append(" SELECT a.SIM_NUMBER, a.PRICE, a.EFFECTIVE_DATE, b.BOOKING_STATUS, b.MERCHANT_ID, a.ACTIVE_STATUS, a.PERIOD_TYPE  FROM ").append(DBConstants.SIM).append(" a ");
			sql.append(" LEFT JOIN ").append(DBConstants.BOOKING).append(" b ON a.SIM_NUMBER = b.SIM_NUMBER");
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getSimNumber())){
				where.add(" a.SIM_NUMBER = ?");
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
						sim.setEffectiveDate(rs.getDate("EFFECTIVE_DATE"));
						sim.setBookingStatus(rs.getString("BOOKING_STATUS"));
						sim.setMerchantId(rs.getString("MERCHANT_ID"));
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
	public int CountSimDataTableFilter(SearchDataTable<Sim> searchDataTable) {
		List<Sim> results = null;
		StringBuilder sql = new StringBuilder();
		List<String> where = new ArrayList<>();
		try {
			sql.append(" SELECT a.SIM_NUMBER  FROM ").append(DBConstants.SIM).append(" a ");
			sql.append(" LEFT JOIN ").append(DBConstants.BOOKING).append(" b ON a.SIM_NUMBER = b.SIM_NUMBER");
			if(StringUtils.isNotBlank(searchDataTable.getDataSearch().getSimNumber())){
				where.add(" a.SIM_NUMBER = ?");
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
	public int countSimTotalDataTable(SearchDataTable<Sim> searchDataTable) {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		try {
			sql.append("SELECT COUNT(0) FROM ");
			sql.append(DBConstants.SIM);
			sql.append(" WHERE CASE WHEN (? = '' || ? IS NULL) THEN 1 = 1 ELSE SIM_NUMBER = ? END");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { searchDataTable.getDataSearch().getSimNumber(),
					searchDataTable.getDataSearch().getSimNumber(),
					searchDataTable.getDataSearch().getSimNumber() }, Integer.class);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
}
