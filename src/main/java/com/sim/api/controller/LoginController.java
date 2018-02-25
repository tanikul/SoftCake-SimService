package com.sim.api.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import javax.xml.soap.SOAPException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.api.common.ServiceException;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;
import com.sim.api.model.User;
import com.sim.api.service.EmailService;
import com.sim.api.service.UserService;
import com.sim.api.utils.AppUtils;
import com.sim.api.utils.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@RestController
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	private static final String CLAIMSTR = "claims";
	
	@Autowired
	UserService userService; 
	
	@Autowired
	private AppUtils app;
	
	@Autowired
	EmailService emailService;
	
	 @RequestMapping(value = "/test",method = RequestMethod.GET)
	 public String test() throws ServiceException {
		 User u = new User();
		 u.setActivateEmail("xxxxx");
		 u.setEmail("tanikul.sa@gmail.com");
		try {
			emailService.sendEmailRegisterUser(u);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "^_^  Welcome to MasterSetup Service !!!!!!!!!!!!! \n";
	 }
	 
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	 public User login(HttpServletRequest request , @RequestBody final User user) throws ServiceException {
		 User u = null;
		 try{
			u = userService.checkLogin(user);
		 }catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
        return u;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logOut(HttpServletRequest request ,@RequestBody final String userId) throws ServletException {
    	try {	
             // this.logOutSystem(request, userId,Constants.ACTION_LOGOUT);
		}catch(Exception ex){
			 logger.error(ex);
		 }
    	return Constants.SUCCESS;
	}
    
    @RequestMapping(value = "/user/signup", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String signup(@RequestBody User user,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		try {
			String sha256hex = DigestUtils.sha256Hex(user.getUserId() + user.getPassword() + new Date());
			user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
		    user.setActivateEmail(sha256hex);
		    user.setActiveStatus("N");
		    user.setRole(Constants.USER_ROLE);
			user.setCreatedBy(user.getUserId());
			user.setLastUpdateBy(user.getUserId());
			userService.insertUser(user);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
    
    @RequestMapping(value = "/user/checkDuplicateUser",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public int SearchSim(@RequestBody String userId) throws ServiceException {
		int result = 0;
		try {
			result = userService.checkDuplicateUser(userId);
		 }catch(Exception ex){
			 logger.error(ex);
		 }
		 return result;
    }
    
    @SuppressWarnings("unused")
    private static class LoginResponse {
        
		private String token;

		public LoginResponse(final String token) {
            this.token = token;
        }
		
        public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
    }
	    
}