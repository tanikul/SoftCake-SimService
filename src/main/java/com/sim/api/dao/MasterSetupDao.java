package com.sim.api.dao;

import java.util.List;
import java.util.Map;

public interface MasterSetupDao {

	public List<Map<String, Object>> loadProvince();
	List<Map<String, Object>> loadMasterSetup(String prefix);
	List<Map<String, Object>> loadRole();
	Map<String, Object> getProvinceById(String id);
	Map<String, Object> loadMasterSetupByPrefixAndId(String prefix, String id);
}
