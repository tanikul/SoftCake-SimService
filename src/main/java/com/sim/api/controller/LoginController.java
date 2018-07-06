package com.sim.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.castor.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.common.StringUtils;
import com.sim.api.common.ServiceException;
import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.ListPrivileges;
import com.sim.api.model.PrivilegeJson;
import com.sim.api.model.Sim;
import com.sim.api.model.User;
import com.sim.api.service.BookingService;
import com.sim.api.service.EmailService;
import com.sim.api.service.MasterSetupService;
import com.sim.api.service.UserService;
import com.sim.api.utils.AppUtils;
import com.sim.api.utils.Constants;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@RestController
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	UserService userService; 
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	MasterSetupService masterSetupService;
	
	@Autowired
	private AppUtils app;
	
	 @RequestMapping(value = "/testEmail",method = RequestMethod.GET)
	 public String testEmail() throws ServiceException {
		try {
			Booking b = new Booking();
			emailService.sendEmailSubmitBooking("201805042");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "^_^  Welcome to MasterSetup Service !!!!!!!!!!!!! \n";
	 }
	 
	 @RequestMapping(value = "/testReport/{bookingId}",method = RequestMethod.GET)
	 public String testReport(@PathVariable String bookingId,
			 HttpServletResponse response) throws ServiceException {
		 try {
			/* File initialFile = new File("C:\\Users\\tanikul.s\\report2.jasper");
			 InputStream employeeReportStream = new FileInputStream(initialFile);*/
			 /*BigDecimal pi8 = new BigDecimal(32);
			 String x8 = app.BigDecimalToCurrencyFormat(pi8);
			 String y8 = new DecimalFormat("#,###.00").format(pi8);
			 BigDecimal pi7 = new BigDecimal(32222222);
			 String x7 = app.BigDecimalToCurrencyFormat(pi7);
			 String y7 = new DecimalFormat("#,###.00#").format(pi7);
			 BigDecimal pi = new BigDecimal(32222222.144444);
			 String x = app.BigDecimalToCurrencyFormat(pi);
			 String y = new DecimalFormat("#,###.00").format(pi);
			 BigDecimal pi2 = new BigDecimal(32222222.13);
			 String x2 = app.BigDecimalToCurrencyFormat(pi2);
			 String y2 = new DecimalFormat("#,###.00").format(pi2);
			 BigDecimal pi3 = new BigDecimal(32222222.145);
			 String x3 = app.BigDecimalToCurrencyFormat(pi3);
			 String y3 = new DecimalFormat("#,###.00").format(pi3);
			 BigDecimal pi4 = new BigDecimal(33333333333333332222222.15);
			 String x4 = app.BigDecimalToCurrencyFormat(pi4);
			 String y4 = new DecimalFormat("#,###.00").format(pi4);*/
			 
			 Booking booking = bookingService.getBookingByBookingId(bookingId);
			 User user = userService.loadUserById(booking.getMerchantId());
			 List<BookingDetail> bookingDetails = new ArrayList<>();
			 BookingDetail d = null;
			 for(BookingDetail bookingDetail : booking.getBookingDetails()) {
				 bookingDetail.setSimNumber(app.parseSimFormat(bookingDetail.getSimNumber()));
				 bookingDetail.setPriceStr(app.BigDecimalToCurrencyFormat(bookingDetail.getPrice()));
				 bookingDetails.add(bookingDetail);
				 d = bookingDetail;
			 }
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 bookingDetails.add(d);
			 booking.setBookingDetails(bookingDetails);
			InputStream employeeReportStream = getClass().getResourceAsStream("/templates/booking.jrxml");
			JRBeanCollectionDataSource sims = new JRBeanCollectionDataSource(booking.getBookingDetails());
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("bookingId", booking.getBookingId());
			parameters.put("name", user.getPrefixName() + user.getFirstName() + " " + user.getLastName());
			parameters.put("nickName", (user.getNickName() == null || user.getNickName() == "") ? "-" : user.getNickName());
			String address1 = null;
			String address2 = null;
			if(user.getAddress().length() > 40) {
				address1 = user.getAddress().substring(0, 40);
				address2 = user.getAddress().substring(41, user.getAddress().length());
			}
			address1 = null;
			address2 = "";
			parameters.put("userAddress", user.getAddress() + " " + user.getProvinceStr() + " " + user.getPostcode());
			parameters.put("mobile", ((user.getMobile() == null || user.getMobile() == "") ? "-" : app.parseSimFormat(user.getMobile())));
			parameters.put("email", user.getEmail());
			parameters.put("line", (user.getLine() == null || user.getNickName() == "") ? "-" : user.getLine());
			parameters.put("website", (user.getWebsite() == null || user.getNickName() == "") ? "-" : user.getWebsite());
			Map<String, Object> province = masterSetupService.getProvinceById(booking.getProvince());
			parameters.put("bookingAddress", booking.getAddress() + " " + province.get("DESCRIPTION") + " " + booking.getPostcode());
			parameters.put("bookingPostCode", booking.getPostcode());
			parameters.put("bookingProvince", booking.getProvince());
			parameters.put("bookingSumPrice", app.BigDecimalToCurrencyFormat(booking.getSumPrice()));
			Map<String, Object> bank = masterSetupService.loadMasterSetupByPrefixAndId("BANK", booking.getBank());
			parameters.put("bookingBank", bank.get("DESCRIPTION") + "<br/>" + bank.get("SUB_DESCRIPTION"));
			parameters.put("Sim", sims);
			JasperReport jasperReport
			  = JasperCompileManager.compileReport(employeeReportStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			
			JRPdfExporter exporter = new JRPdfExporter();
			 
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			/*exporter.setExporterOutput(
			  new SimpleOutputStreamExporterOutput("employeeReport.pdf"));*/
			 
			SimplePdfReportConfiguration reportConfig
			  = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			 
			SimplePdfExporterConfiguration exportConfig
			  = new SimplePdfExporterConfiguration();
			exportConfig.setMetadataAuthor("baeldung");
			exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint("PRINTING");
			 
			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
			exporter.exportReport();
			
			response.setContentType("application/pdf");
	        response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
	        response.addHeader("Content-Disposition", "inline; filename=employeeReport.pdf;");
	
	        OutputStream responseOutputStream = response.getOutputStream();
	        responseOutputStream.write(pdfReportStream.toByteArray());
	        responseOutputStream.close();
	        pdfReportStream.close();
		 }catch(JRException ex) {
			 ex.printStackTrace();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return "^_^  Welcome to MasterSetup Service !!!!!!!!!!!!! \n";
	 }
	
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
	 
	 @RequestMapping(value = "/checkLoginWithEmail", method = RequestMethod.POST)
	 public User checkLoginWithEmail(HttpServletRequest request , @RequestBody final User user) throws ServiceException {
		 User u = null;
		 try{
			u = userService.checkLoginWithEmail(user);
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
		    user.setUserType(Constants.USER_TYPE_CUSTOMER);
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
	 public int checkDuplicateUser(@RequestBody String userId) throws ServiceException {
		int result = 0;
		try {
			result = userService.checkDuplicateUser(userId);
		 }catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
		 return result;
    }
    
    @RequestMapping(value = "/user/checkDuplicateEmail",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public int checkDuplicateEmail(@RequestBody String email) throws ServiceException {
		User result = null;
		try {
			result = userService.selectUserByEmail(email);
		 }catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
		 return (result == null) ? 0 : 1;
     }
    
     @RequestMapping(value = "/user/getUserAuthenByEmail",method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public User getUserByEmail(@RequestBody String email) throws ServiceException {
		User result = null;
		try {
			result = userService.selectUserByEmail(email);
			if(result == null) {
				throw new ServiceException(101);
			}
		 }catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
		 return result;
    }
    
	 @RequestMapping(value = "/user/getRightUserDefault",method = RequestMethod.GET, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	 @ResponseBody
	 public ListPrivileges getRightUserDefault() throws ServiceException {
    	ListPrivileges rs = new ListPrivileges();
		try {
			List<PrivilegeJson> result = userService.getRightUserDefault();
			rs.setList(result);
		}catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		 }
		 return rs;
    }
	 
	 @RequestMapping(value = "/activateEmail",method = RequestMethod.GET)
	 @ResponseBody
	 public String activateEmail(@RequestParam("userId") String userId,
			 @RequestParam("activateEmail") String activateEmail) throws ServiceException {
		try {
			User user = new User();
			user.setActivateEmail(activateEmail);
			user.setUserId(userId);
			userService.updateConfirmation(user);
		}catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		}
		return Constants.SUCCESS;
   }
    
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
    public String forgotPassword(@RequestBody String email) throws ServiceException {
		String result = "";
    	try {
    		result = userService.updateForgotPassword(email);
		}catch(Exception ex){
			 logger.error(ex);
			 throw new ServiceException(ex);
		}
		return result;
    }
    

	@RequestMapping(value = "/saveResetPassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public String resetPassword(@RequestBody User user,
			final HttpServletRequest request,
			final HttpServletResponse response) throws ServiceException {
		try {
			user.setLastUpdateBy(user.getUserId());
			userService.updatePasswordForgotPassword(user);
		} catch (Exception e) {
    		logger.error(e);
    		throw new ServiceException(e);
        }
		return Constants.SUCCESS;
	} 
    
    @RequestMapping(value = "/selectUserByEmailAndForgotPassword", method = RequestMethod.POST, produces="application/json;charset=UTF-8",headers = {"Accept=text/xml, application/json"})
    @ResponseBody
    public User forgotPassword(@RequestBody User user) throws ServiceException {
		User result = null;
    	try {
    		result = userService.selectUserByEmailAndForgotPassword(user);
		}catch(Exception ex){
			logger.error(ex);
			throw new ServiceException(ex);
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