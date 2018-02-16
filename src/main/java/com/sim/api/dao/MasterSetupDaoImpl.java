package com.sim.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sim.api.utils.DBConstants;

@Repository("masterSetupDao")
public class MasterSetupDaoImpl implements MasterSetupDao {

	
	private JdbcTemplate jdbcTemplate; 
	private static final Logger logger = Logger.getLogger(MasterSetupDaoImpl.class);
	
	@Autowired
	private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = (JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations();
	}
	
	@Override
	public List<Map<String, Object>> loadProvince() {
		List<Map<String, Object>> result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT PROVINCE_ID CODE, PROVINCE_NAME DESCRIPTION FROM ");
			sql.append(DBConstants.PROVINCE);
			sql.append(" ORDER BY PROVINCE_NAME ASC");
			result = jdbcTemplate.queryForList(sql.toString());
		} catch(EmptyResultDataAccessException e){
			logger.debug(e);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public List<Map<String, Object>> loadMasterSetup(String prefix) {
		List<Map<String, Object>> result = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID CODE, DESCRIPTION FROM ");
			sql.append(DBConstants.MASTER_SETUP);
			sql.append(" WHERE GROUP_TYPE = ? ");
			sql.append(" ORDER BY ID ASC");
			result = jdbcTemplate.queryForList(sql.toString(), new Object[] { prefix });
		} catch(EmptyResultDataAccessException e){
			logger.debug(e);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
}
