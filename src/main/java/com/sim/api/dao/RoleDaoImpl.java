package com.sim.api.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sim.api.datatable.Order;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.mapper.RoleRowMapper;
import com.sim.api.model.RoleMst;
import com.sim.api.utils.DBConstants;

@Repository("roleDao")
public class RoleDaoImpl  implements RoleDao{

	private JdbcTemplate jdbcTemplate; 
	private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

	@Autowired 
	@Qualifier("transactionManager") 
	private PlatformTransactionManager transactionManager;

	@Autowired
	private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
	}
	


	@Override
	public void insertRoleMst(RoleMst roleMst) {
		StringBuilder sql = new StringBuilder();
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try{
			sql.append("  INSERT INTO ").append(DBConstants.ROLE); 
			sql.append("  (ROLE_NAME, ACTIVE_STATUS, CREATED_DATE, CREATED_BY, LAST_UPDATED_DATE, LAST_UPDATED_BY)  "); 
			sql.append("  VALUES ( ?, ? , SYSDATE() , ? , SYSDATE(), ? )  ");
			jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, roleMst.getRoleName());
					ps.setString(2, "Y");
					ps.setString(3, roleMst.getCreatedBy()!=null?roleMst.getCreatedBy():"SYSTEM");
					ps.setString(4, roleMst.getLastUpdatedBy()!=null?roleMst.getLastUpdatedBy():"SYSTEM");
				}
			});
	    	transactionManager.commit(status);
		}catch (Exception e) {
	    	transactionManager.rollback(status);
	    	LOGGER.error(e);
    		throw e;
        }
	}

	@Override
	public RoleMst getRoleMstByRoleNameByRoleType(String roleName){
		RoleMst result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ");
			sql.append(DBConstants.ROLE);
			sql.append(" WHERE ROLE_NAME = ? ");
			result = jdbcTemplate.queryForObject(sql.toString(),  new Object[] { roleName }, new RoleRowMapper());
		} catch(EmptyResultDataAccessException  ex){
			LOGGER.error(">> getRoleMstByRoleNameByRoleType ERROR :"+ ex);
		}
		return result;	
	}
	@Override
	public int checkDuplicateRoleNameByRoleType(String roleName) {
		int result = 0;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT COUNT(0) FROM  ");
			sql.append(DBConstants.ROLE);
			sql.append(" WHERE ROLE_NAME = ?  ");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { roleName }, Integer.class);
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
		return result;
	}


	@Override
	public List<RoleMst> searchRoleByDataTable(SearchDataTable<RoleMst> roleData) {
		List<RoleMst> results = null;
		StringBuilder sql = new StringBuilder();
		String orderType = "asc";

		try{
			sql.append(" select R.* ,  ");
			sql.append(" ( CASE WHEN U.ROLE is null  THEN FALSE ELSE TRUE END)  DEL_FLG  ");
			sql.append(" from ").append(DBConstants.ROLE).append("  R  ");
			sql.append(" LEFT JOIN (   ");
			sql.append("    select DISTINCT ROLE from ").append(DBConstants.USER).append("  ");
			sql.append(" ) U ON U.ROLE = R.ROLE_ID  ");
			sql.append(" where ACTIVE_STATUS = 'Y' ");


			if(roleData.getDataSearch() != null){
				if( StringUtils.isNotBlank(roleData.getDataSearch().getRoleName())){
					sql.append(" and R.ROLE_NAME like  ?  ");
				}
			}
				
			if(roleData.getOrder() != null && !roleData.getOrder().isEmpty()){
				Order field = roleData.getOrder().get(0);
				if(field!=null && !field.getDir().isEmpty() ){
					orderType = field.getDir();
				}
			}


			sql.append(" ORDER BY ROLE_NAME  ").append(orderType);
			sql.append(" LIMIT ").append(roleData.getLength()).append(" OFFSET ").append(roleData.getStart());
			results = jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					int i = 1;
					if(roleData.getDataSearch() != null ){
						if( StringUtils.isNotBlank(roleData.getDataSearch().getRoleName())){
							preparedStatement.setString(i++,  "%"+roleData.getDataSearch().getRoleName() + "%");
						}
					}
				}
			},new  RoleRowMapper() {

				@Override
				public RoleMst mapRow(ResultSet rs, int arg1) throws SQLException { 
					RoleMst role = new RoleMst(); 
					role.setRoleId(rs.getInt("ROLE_ID"));
					role.setRoleName(rs.getString("ROLE_NAME"));
					role.setActiveStatus(rs.getString("ACTIVE_STATUS"));

					role.setCreatedBy(rs.getString("CREATED_BY"));
					role.setCreatedDate(rs.getDate("CREATED_DATE"));
					role.setLastUpdateBy(rs.getString("LAST_UPDATED_BY"));
					role.setLastUpdateDate(rs.getDate("LAST_UPDATED_DATE"));

					role.setDisableDeleteFlg(rs.getBoolean("DEL_FLG"));

					return role; 
				}

			});
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

		return results;
	}

	@Override
	public int countSearchRoleByDataTableFilter(SearchDataTable<RoleMst> roleData) {
		int  result = 0;
		StringBuilder sql = new StringBuilder();
		String roleName = "";

		try{
			if(roleData.getDataSearch() != null) { 
				if(StringUtils.isNotBlank(roleData.getDataSearch().getRoleName())){
					roleName = roleData.getDataSearch().getRoleName();
				}
			}
			sql.append(" select count(0)  from ").append(DBConstants.ROLE);
			sql.append(" where ");
			sql.append(" ACTIVE_STATUS = 'Y' ");
			sql.append(" and ROLE_NAME like  ?  ");

			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { "%"+roleName+"%" }, Integer.class);
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

		return result;
	}
	@Override
	public int countSearchRoleByDataTableTotal(SearchDataTable<RoleMst> roleData) {
		int  result = 0;
		StringBuilder sql = new StringBuilder();
		try{
			sql.append(" select count(0)  from ").append(DBConstants.ROLE);
			sql.append("  where ACTIVE_STATUS = 'Y' ");

			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { }, Integer.class);
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}

		return result;
	}

	@Override
	public void deletetRoleMstByRoleName(String roleName){
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM ").append(DBConstants.ROLE);
			sql.append(" where ");
			sql.append(" and ROLE_NAME = ? ");
			jdbcTemplate.update(sql.toString(), new Object[] { roleName });
			transactionManager.commit(status);
		}catch (Exception e) {
			transactionManager.rollback(status);
			LOGGER.error(e);
			throw e;
		}
	}

	@Override
	public int checkRoleExistingInUserMaster(String roleName){
		int result = 0;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("select count(0)    ");
			sql.append(" FROM  ").append(DBConstants.ROLE);
			sql.append("  where exists(  ");
			sql.append("	select ROLE from  ").append(DBConstants.USER).append("  where ");
			sql.append("    role = role_id   ");
			sql.append(" )  ");
			sql.append(" 	and ROLE_NAME = ? ");
			result = jdbcTemplate.queryForObject(sql.toString(), new Object[] { roleName }, Integer.class);
		} catch (Exception e) {
			LOGGER.error(e);
			throw e;
		}
		return result;	
	}

}
