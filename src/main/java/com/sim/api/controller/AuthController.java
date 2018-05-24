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
import com.sim.api.common.ServiceException;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.RequestSim;
import com.sim.api.model.Sim;
import com.sim.api.service.BookingService;
import com.sim.api.service.SimService;
import com.sim.api.utils.AppUtils;
import com.sim.api.utils.Constants;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/apis")
public class AuthController {
	
	private static final Logger logger = Logger.getLogger(AuthController.class);
	private static final String CLAIMSTR = "claims";
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private SimService simService;
	
	@Autowired
	private AppUtils app;

	 @RequestMapping(value = "saveBooking", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String saveSim(@RequestBody List<Sim> list, HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		String result = "";
		try {
			result = bookingService.insertBooking(list, claims.getSubject());
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
    }
	 
	 @RequestMapping(value = "searchBooking", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public List<Booking> searchBooking(@RequestBody Booking search,
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		List<Booking> result = null;
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		try {
			search.setMerchantId(claims.getSubject());
			result = bookingService.searchBooking(search);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
   }
	 
	 @RequestMapping(value = "uploadSlip", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public List<Booking> uploadSlip(@RequestBody Booking booking, HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		List<Booking> result = null;
		try {
			booking.setLastUpdateBy(claims.getSubject());
			booking.setBookingStatus("W");
			result = bookingService.updateStatusUploadFile(booking);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
    }
	 
	 @RequestMapping(value = "openSlip", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String openSlip(@RequestBody String bookingId, 
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		String result = null;
		try {
			result = bookingService.getSlipByBookingId(claims.getSubject(), bookingId);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
    }
	 @RequestMapping(value = "openIDCard", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String getIdCardByBookingDetailId(@RequestBody String bookingDetailId, 
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		String result = null;
		try {
			result = bookingService.getIdCardByBookingDetailId(claims.getSubject(), bookingDetailId);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
    }
	 
	 @RequestMapping(value = "uploadIdcard", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public List<Booking> uploadSlip(@RequestBody BookingDetail bookingDetail, 
			 HttpServletRequest request, 
			 HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		List<Booking> result = null;
		try {
			bookingDetail.setLastUpdateBy(claims.getSubject());
			result = bookingService.updateStatusUploadIdCard(bookingDetail);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
    }
	 
	 @RequestMapping(value = "getBookingByBookingId/{bookingId}", method = RequestMethod.GET)
	 @ResponseBody
	 public Booking getBookingByBookingId(@PathVariable String bookingId, HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Booking result = null;
		try {
			result = bookingService.getBookingByBookingId(bookingId);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
    }
	 
	 @RequestMapping(value = "cancelBooking/{bookingId}", method = RequestMethod.GET)
	 @ResponseBody
	 public String cancelBooking(@PathVariable String bookingId,
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		try {
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			bookingService.cancelBooking(bookingId, claims.getSubject());
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;
    }
	 
	@RequestMapping(value = "searchRequestSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	public DataTable<RequestSim> searchRequestSim(@RequestBody SearchDataTable<RequestSim> searchDataTable,
			final HttpServletRequest request) throws ServiceException {
		DataTable<RequestSim> result = new DataTable<>();
		try{
			Claims claims = (Claims) request.getAttribute(CLAIMSTR);
			searchDataTable.getDataSearch().setMerchantId(claims.getSubject());
			result = simService.SearchRequestSimDataTable(searchDataTable);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return result;
	}
	
	@RequestMapping(value = "saveRequestSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String saveRequestSim(@RequestBody RequestSim sim,
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		try {
			sim.setCreatedBy(claims.getSubject());
			sim.setLastUpdateBy(claims.getSubject());
			sim.setMerchantId(claims.getSubject());
			sim.setSumNumber(app.calculateSim(sim.getSimNumber()));
			simService.saveRequestSim(sim);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;
   }
	
	
	@RequestMapping(value = "checkSimNumberBeforeRequest/{simNumber}", method = RequestMethod.GET)
	 @ResponseBody
	 public int checkSimNumberBeforeRequest(@PathVariable String simNumber,
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		int result = 1;
		try {
			result = simService.checkSimNumberBeforeRequest(simNumber);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return result;
   }
	
	@RequestMapping(value = "cancelRequestSim", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public String cancelRequestSim(@RequestBody RequestSim sim,
			 HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		try {
			sim.setLastUpdateBy(claims.getSubject());
			simService.cncelRequestSim(sim);
		} catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex); 
		}
		return Constants.SUCCESS;
    }
	
	@RequestMapping(value = "selectSimByNumber",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public List<Sim> selectSimByNumber(@RequestBody List<String> list,
			 HttpServletRequest request) throws ServiceException {
		 List<Sim> result = new ArrayList<>();
		 Claims claims = (Claims) request.getAttribute(CLAIMSTR);
		try {
			for(String item : list){
				result.add(simService.selectSimByNumber(item, claims.getSubject()));
			}
		 }catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
		 return result;
	 }
}
