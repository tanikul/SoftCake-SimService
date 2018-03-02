package com.sim.api.service;

import java.util.List;
import java.util.Map;

public interface MasterSetupService {

	public List<Map<String, Object>> loadProvince();
	public List<Map<String, Object>> loadMasterSetup(String prefix);
	List<Map<String, Object>> loadRole();
}
