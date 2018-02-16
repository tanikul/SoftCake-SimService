package com.sim.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sim.api.common.ServiceException;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.User;
import com.sim.api.service.UserService;

import io.jsonwebtoken.Claims;


@RestController
@RequestMapping("/apis/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	private static final String CLAIMSTR = "claims";
	
	@RequestMapping(value = "searchUser", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public DataTable<User> searchUser(@RequestBody SearchDataTable<User> searchDataTable,
			final HttpServletRequest request) throws ServiceException {
		DataTable<User> result = new DataTable<>();
		try{
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			result = userService.searchUser(searchDataTable);
		}catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
}
