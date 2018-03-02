package com.sim.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sim.api.common.ServiceException;
import com.sim.api.service.MasterSetupService;

@RestController
@RequestMapping("/masterSetup")
public class MasterSetupController {

	@Autowired
	@Qualifier("masterSetupService")
	private MasterSetupService masterSetupService;
	
	private static final Logger logger = Logger.getLogger(MasterSetupController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	    df.setLenient(true);
	    CustomDateEditor editor = new CustomDateEditor(df, true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	@RequestMapping(value = "loadProvince", method = RequestMethod.GET)
	public List<Map<String, Object>> loadProvince() throws ServiceException  {
		List<Map<String, Object>> result = null;
		try {
			result = masterSetupService.loadProvince();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
		return result;
	}
	
	@RequestMapping(value = "loadMasterSetup/{group}", method = RequestMethod.GET)
	public List<Map<String, Object>> loadMasterSetup(@PathVariable String group) throws ServiceException  {
		List<Map<String, Object>> result = null;
		try {
			result = masterSetupService.loadMasterSetup(group);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
		return result;
	}
	
	@RequestMapping(value = "loadRole", method = RequestMethod.GET)
	public List<Map<String, Object>> loadRole() throws ServiceException  {
		List<Map<String, Object>> result = null;
		try {
			result = masterSetupService.loadRole();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
		return result;
	}
}
