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
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.Predict;
import com.sim.api.model.RequestSim;
import com.sim.api.model.Sim;
import com.sim.api.model.User;
import com.sim.api.service.BookingService;
import com.sim.api.service.PredictService;
import com.sim.api.service.SimService;
import com.sim.api.service.UserService;
import com.sim.api.utils.AppUtils;
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
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private AppUtils app;
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	private static final String CLAIMSTR = "claims";
	
	@RequestMapping(value = "searchUser", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public DataTable<User> searchUser(@RequestBody SearchDataTable<User> searchDataTable,
			final HttpServletRequest request) throws ServiceException {
		DataTable<User> result = new DataTable<>();
		try{
			result = userService.searchUser(searchDataTable);
		}catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	
	 @RequestMapping(value = "saveSim",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String saveSim(@RequestBody List<Sim> list, HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
    	try {
    		List<String> listSimDup = new ArrayList<>();
    		List<String> listReqDup = new ArrayList<>();
    		for(Sim s : list){
    			Sim sim = simService.checkDuplicateSimBeforeAddSimNumber(s.getSimNumber());
    			if(sim != null) {
    				if("S".equals(sim.getFlagSim())) {
    					listSimDup.add(s.getSimNumber());
    				}else if("R".equals(sim.getFlagSim())) {
    					listReqDup.add(s.getSimNumber());
    				}
    			}
    		}
    		String str = "";
    		if(listSimDup.size() > 0) {
    			str = "เบอร์โทรศัพท์ดังต่อไปนี้มีในระบบแล้ว <br/><ul>";
    			for(String item : listSimDup) {
    				str += "<li>" + app.parseSimFormat(item) + "</li>";
    			}
    			str += "</ul>";
    		}
    		if(listReqDup.size() > 0) {
    			str = "เบอร์โทรศัพท์ดังต่อไปนี้มีคำขอมาจากลูกค้า <ul>";
    			for(String item : listReqDup) {
    				str += "<li>" + app.parseSimFormat(item) + "</li>";
    			}
    			str += "</ul>";
    		}
    		if(listReqDup.size() > 0 || listSimDup.size() > 0) {
    			throw new ServiceException(str);
    		}
			for(Sim s : list){
				s.setCreatedBy(claims.getSubject());
				s.setLastUpdateBy(claims.getSubject());
				s.setSumNumber(app.calculateSim(s.getSimNumber()));
				simService.insertSim(s);
			}
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex.getMessage()); 
		}
		return Constants.SUCCESS;
    }
	 
	@RequestMapping(value = "searchSimActive", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public DataTable<Sim> searchSimActive(@RequestBody SearchDataTable<Sim> searchDataTable,
			final HttpServletRequest request) throws ServiceException {
		DataTable<Sim> result = new DataTable<>();
		try{
			result = simService.SearchSimDataTable(searchDataTable, Constants.MST);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return result;
	}
	
	@RequestMapping(value = "searchSimPending", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public DataTable<Sim> SearchSimPending(@RequestBody SearchDataTable<Sim> searchDataTable,
			final HttpServletRequest request) throws ServiceException {
		DataTable<Sim> result = new DataTable<>();
		try{
			result = simService.SearchSimDataTable(searchDataTable, Constants.TMP);
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
			predictService.updatePredict(predict);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "approveSim/{simNumber:.*}/{operationFlag}", method = RequestMethod.GET)
	public String approveSim(@PathVariable String simNumber,
			@PathVariable String operationFlag,
			final HttpServletRequest request) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			Sim sim = new Sim();
			sim.setSimNumber(simNumber);
			sim.setOperationFlag(operationFlag);
			sim.setLastUpdateBy(claims.getSubject());
			sim.setOperationFlag(operationFlag);
			sim.setAuthorizedBy(claims.getSubject());
			sim.setActiveStatus("Y");
			simService.acceptSim(sim);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "cancelSim/{simNumber}", method = RequestMethod.GET)
	public String cancelSim(final HttpServletRequest request, @PathVariable String simNumber) throws ServiceException {
		try {
			Sim sim = new Sim();
			sim.setSimNumber(simNumber);
			simService.cancelSim(sim);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}

	@RequestMapping(value = "rejectSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String rejectCorporationByCorpCode(@RequestBody String str,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);
			String rejectReason = mapper.convertValue(node.get("rejectReason"), String.class);
			String simNumber = mapper.convertValue(node.get("simNumber"), String.class);
			String operationFlag = mapper.convertValue(node.get("operationFlag"), String.class);
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			Sim sim = new Sim();
			sim.setSimNumber(simNumber);
			sim.setLastUpdateBy(claims.getSubject());
			sim.setOperationFlag(operationFlag);
			sim.setRejectReason(rejectReason);
			sim.setActiveStatus("N");
			sim.setAuthorizedBy(claims.getSubject());
			simService.rejectSim(sim);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "approveSimAll", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public String approveSim(@RequestBody List<Sim> sims,
			final HttpServletRequest request) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			for(Sim sim : sims) {
				sim.setLastUpdateBy(claims.getSubject());
				sim.setAuthorizedBy(claims.getSubject());
				sim.setActiveStatus("Y");
				simService.acceptSim(sim);
			}
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "rejectSimAll", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public String rejectSimAll(@RequestBody List<Sim> sims,
			final HttpServletRequest request) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			for(Sim sim : sims) {
				sim.setLastUpdateBy(claims.getSubject());
				sim.setAuthorizedBy(claims.getSubject());
				sim.setActiveStatus("N");
				simService.rejectSim(sim);
			}
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "updateSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String updateSim(@RequestBody Sim sim,
			final HttpServletRequest request) throws ServiceException {
		try{
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			sim.setLastUpdateBy(claims.getSubject());
			sim.setActiveStatus("W");
			simService.updateSimTmp(sim);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "updateSimMst", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String updateSimMst(@RequestBody Sim sim,
			final HttpServletRequest request) throws ServiceException {
		try{
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			sim.setLastUpdateBy(claims.getSubject());
			sim.setOperationFlag("E");
			sim.setActiveStatus("W");
			simService.copyToSimTmp(sim);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "deleteSimMst/{simNumber}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteSimMst(@PathVariable String simNumber,
			final HttpServletRequest request) throws ServiceException {
		try{
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			Sim sim = new Sim();
			sim.setLastUpdateBy(claims.getSubject());
			sim.setSimNumber(simNumber);
			sim.setOperationFlag("D");
			sim.setActiveStatus("W");
			simService.copyToSimTmpDelete(sim);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "searchBooking", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public List<Booking> searchBooking(@RequestBody Booking search,
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		List<Booking> result = null;
		try {
			result = bookingService.searchBooking(search);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
   }
	
	@RequestMapping(value = "approveBooking/{bookingId}", method = RequestMethod.GET, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public List<Booking> approveBooking(@PathVariable String bookingId,
			final HttpServletRequest request) throws ServiceException {
		List<Booking> result = null;
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			Booking booking = new Booking();
			booking.setAuthorizedBy(claims.getSubject());
			booking.setBookingId(bookingId);
			booking.setBookingStatus("Y");
			result = bookingService.updateAutorizeStatus(booking);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	
	@RequestMapping(value = "rejectBooking", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public List<Booking> rejectBooking(@RequestBody Booking booking,
			final HttpServletRequest request) throws ServiceException {
		List<Booking> result = null;
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			booking.setAuthorizedBy(claims.getSubject());
			booking.setBookingStatus("N");
			result = bookingService.updateAutorizeStatus(booking);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	
	@RequestMapping(value = "openSlipByAdmin", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String openSlip(@RequestBody String str,
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);
			String bookingId = mapper.convertValue(node.get("bookingId"), String.class);
			String merchantId = mapper.convertValue(node.get("merchantId"), String.class);
			result = bookingService.getSlipByBookingId(merchantId, bookingId);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
   }
	
	@RequestMapping(value = "searchRequestSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public DataTable<RequestSim> searchRequestSim(@RequestBody SearchDataTable<RequestSim> searchDataTable,
			final HttpServletRequest request) throws ServiceException {
		DataTable<RequestSim> result = new DataTable<>();
		try{
			result = simService.SearchRequestSimDataTable(searchDataTable);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return result;
	}
	
	@RequestMapping(value = "approveRequestSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String approveRequestSim(@RequestBody RequestSim requestSim,
			final HttpServletRequest request) throws ServiceException {
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			requestSim.setLastUpdateBy(claims.getSubject());
			simService.approveRequestSim(requestSim);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);  
		}
		return Constants.SUCCESS;
	}
	
	@RequestMapping(value = "rejectStatusIdCard", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public List<Booking> rejectStatusIdCard(@RequestBody BookingDetail bookingDetail,
			final HttpServletRequest request) throws ServiceException {
		List<Booking> result = null;
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			bookingDetail.setAuthorizedBy(claims.getSubject());
			result = bookingService.rejectStatusIdCard(bookingDetail);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
	
	@RequestMapping(value = "approveStatusIdCard", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public List<Booking> approveStatusIdCard(@RequestBody BookingDetail bookingDetail,
			final HttpServletRequest request) throws ServiceException {
		List<Booking> result = null;
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			bookingDetail.setAuthorizedBy(claims.getSubject());
			result = bookingService.approveStatusIdCard(bookingDetail);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);  
		}
		return result;
	}
}
