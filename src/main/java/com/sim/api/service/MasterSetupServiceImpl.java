package com.sim.api.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sim.api.dao.MasterSetupDao;

@Service("masterSetupService")
public class MasterSetupServiceImpl implements MasterSetupService {

	@Autowired
	@Qualifier("masterSetupDao")
	MasterSetupDao masterSetupDao;
	
	private static final Logger logger = Logger.getLogger(MasterSetupServiceImpl.class);
	
	@Override
	public List<Map<String, Object>> loadProvince() {
		List<Map<String, Object>> result = null;
		try {
			result = masterSetupDao.loadProvince();
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
			result = masterSetupDao.loadMasterSetup(prefix);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> loadRole() {
		List<Map<String, Object>> result = null;
		try {
			result = masterSetupDao.loadRole();
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public Map<String, Object> getProvinceById(String id) {
		return masterSetupDao.getProvinceById(id);
	}

	@Override
	public Map<String, Object> loadMasterSetupByPrefixAndId(String prefix, String id) {
		Map<String, Object> result = null;
		try {
			result = masterSetupDao.loadMasterSetupByPrefixAndId(prefix, id);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
}
