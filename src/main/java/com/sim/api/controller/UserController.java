package com.sim.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
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
import com.sim.api.model.User;
import com.sim.api.service.UserService;
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
	
	@RequestMapping(value = "register", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String register(@RequestBody User user,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
		    user.setUserType(Constants.USER_TYPE_ADMIN);
		    user.setCreatedBy(claims.getSubject());
			user.setLastUpdateBy(claims.getSubject());
			userService.registerUser(user);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	} 
	
	@RequestMapping(value = "updateUser", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String updateUser(@RequestBody User user,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			user.setLastUpdateBy(claims.getSubject());
			userService.updateUser(user);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	} 
	
	@RequestMapping(value = "updateUserAdmin", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String updateUserAdmin(@RequestBody User user,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			user.setLastUpdateBy(claims.getSubject());
			userService.updateUserAdmin(user);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	} 
	
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public int updatePassword(@RequestBody String str,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		ObjectMapper mapper = new ObjectMapper();
		int result = 0;
		try {
			JsonNode node = mapper.readTree(str);
			String oldPassword = mapper.convertValue(node.get("oldPassword"), String.class);
			String newPassword = mapper.convertValue(node.get("newPassword"), String.class);
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			String userId = claims.getSubject();
			result = userService.updatePassword(userId, oldPassword, newPassword);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return result;
	} 
	
	@RequestMapping(value = "editUserCustomer", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String editUserCustomer(@RequestBody User user,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			user.setLastUpdateBy(claims.getSubject());
			userService.editUserCustomer(user);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	} 
	
}
