package com.sim.api.controller;

import java.util.ArrayList;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.api.common.ServiceException;
import com.sim.api.model.FilterSearch;
import com.sim.api.model.Predict;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;
import com.sim.api.service.BookingService;
import com.sim.api.service.PredictService;
import com.sim.api.service.SimService;
import com.sim.api.utils.Constants;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/sim")
public class SimController {

	private static final Logger logger = Logger.getLogger(SimController.class);
	private static final String CLAIMSTR = "claims";
	
	@Autowired
	private SimService simService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PredictService predictService;
	
	 @RequestMapping(value = "searchSim",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public ResultDataSim SearchSim(@RequestBody String str) throws ServiceException {
		 ResultDataSim result = null;
		 ObjectMapper mapper = new ObjectMapper();
			try {
				JsonNode node = mapper.readTree(str);
				FilterSearch filter = mapper.convertValue(node.get("filter"), FilterSearch.class);
				int page = mapper.convertValue(node.get("page"), int.class);
				result = simService.SearchSim(page, filter);
		 }catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
		 return result;
	 }
	 
	 @RequestMapping(value = "selectSimByNumber",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public List<Sim> selectSimByNumber(@RequestBody List<String> list,
			 HttpServletRequest request) throws ServiceException {
		 List<Sim> result = new ArrayList<>();
		try {
			for(String item : list){
				result.add(simService.selectSimByNumber(item, ""));
			}
		 }catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
		 return result;
	 }
	 
	 @RequestMapping(value = "saveBooking",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String saveSim(@RequestBody List<Sim> list, HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
    	try {
    		bookingService.insertBooking(list, claims.getSubject());
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;
    }
	 
	@RequestMapping(value = "getDataPredict/{sumNumber}", method = RequestMethod.GET, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Predict getDataPredict(@PathVariable int sumNumber,
			final HttpServletRequest request) throws ServiceException {
		Predict result = null;
		try{
			result = predictService.getPredictById(sumNumber);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return result;
	}
}
