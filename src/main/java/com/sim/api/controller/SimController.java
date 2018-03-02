package com.sim.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.api.common.ServiceException;
import com.sim.api.model.ProgramMst;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;
import com.sim.api.service.SimService;
import com.sim.api.utils.AppUtils;
import com.sim.api.utils.Constants;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/sim")
public class SimController {

	private static final Logger logger = Logger.getLogger(SimController.class);
	private static final String CLAIMSTR = "claims";
	
	@Autowired
	private SimService simService;
	
	 @RequestMapping(value = "searchSim",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public ResultDataSim SearchSim(@RequestBody String str) throws ServiceException {
		 ResultDataSim result = null;
		 ObjectMapper mapper = new ObjectMapper();
			try {
				JsonNode node = mapper.readTree(str);
				Sim sim = mapper.convertValue(node.get("sim"), Sim.class);
				int page = mapper.convertValue(node.get("page"), int.class);
				result = simService.SearchSim(page, sim);
		 }catch(Exception ex){
			 logger.error(ex);
		 }
		 return result;
	 }
}
