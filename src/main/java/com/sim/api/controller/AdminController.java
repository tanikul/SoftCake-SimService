package com.sim.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.api.common.ServiceException;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.Predict;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;
import com.sim.api.model.User;
import com.sim.api.service.PredictService;
import com.sim.api.service.SimService;
import com.sim.api.service.UserService;
import com.sim.api.utils.Constants;

import io.jsonwebtoken.Claims;


@RestController
@RequestMapping("/apis/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SimService simService;
	
	@Autowired
	private PredictService predictService;
	
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
	
	 @RequestMapping(value = "saveSim",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String saveSim(@RequestBody String str, HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
    	ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);
			JavaType t = mapper.getTypeFactory().constructCollectionType(List.class, Sim.class);
			List<Sim> list = mapper.convertValue(node.get("list"), t);
			for(Sim s : list){
				s.setCreatedBy(claims.getSubject());
				s.setLastUpdateBy(claims.getSubject());
				simService.insertSim(s);
			}
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;
    }
	 
	@RequestMapping(value = "searchSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public DataTable<Sim> searchSim(@RequestBody SearchDataTable<Sim> searchDataTable,
			final HttpServletRequest request) throws ServiceException {
		DataTable<Sim> result = new DataTable<>();
		try{
			result = simService.SearchSimDataTable(searchDataTable);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return result;
	}
	
	@RequestMapping(value = "getPredictById/{predictId}", method = RequestMethod.GET, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Predict getPredictById(@PathVariable int predictId,
			final HttpServletRequest request) throws ServiceException {
		Predict result = null;
		try{
			result = predictService.getPredictById(predictId);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return result;
	}
	
	@RequestMapping(value = "updatePredict", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String updatePredictById(@RequestBody Predict predict,
			final HttpServletRequest request) throws ServiceException {
		try{
			predictService.updatePredict(predict);;
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
}
