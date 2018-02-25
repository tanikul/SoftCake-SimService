package com.sim.api.controller;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.api.common.ServiceException;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;
import com.sim.api.model.User;
import com.sim.api.service.UserService;
import com.sim.api.utils.AppUtils;
import com.sim.api.utils.Constants;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/apis/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	private static final String CLAIMSTR = "claims";
	
	@RequestMapping(value = "loadUserById/{userId}", method = RequestMethod.GET)
	public User loadMasterSetup(@PathVariable String userId) throws ServiceException  {
		User result = null;
		try {
			result = userService.loadUserById(userId);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
		return result;
	}
	
	@RequestMapping(value = "deleteUserById/{userId}", method = RequestMethod.GET)
	public String deleteUserById(@PathVariable String userId) throws ServiceException  {
		try {
			userService.deleteUserById(userId);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(e);
		}
		return Constants.SUCCESS;
	}
	
	 
	
}
