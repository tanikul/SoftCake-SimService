package com.sim.api.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.sim.api.common.AppProperties;
import com.sim.api.dao.EmailDao;
import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.Email;
import com.sim.api.model.User;
import com.sim.api.utils.AppUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
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

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	@Qualifier("emailSender")
    public JavaMailSender emailSender;
	
	@Autowired
	@Qualifier("emailDao")
    public EmailDao emailDao;
	
	@Autowired
	public AppProperties app;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	MasterSetupService masterSetupService;
	
	@Autowired
	private AppUtils appUtils;
	
	@Autowired
	UserService userService; 
	
	@Autowired
    private SpringTemplateEngine templateEngine;
	
	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
	      
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	     
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);
	         
	    FileSystemResource file 
	      = new FileSystemResource(new File(pathToAttachment));
	    helper.addAttachment("Invoice", file);
	 
	    emailSender.send(message);
		
	}

	@Override
	public void sendEmailRegisterUser(User user) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");	 
		helper.setFrom(new InternetAddress(app.getSender()));
	    helper.setTo(user.getEmail());
	    helper.setSubject("อีเมล์ยืนยันตัวตน");
	    String str = "กรุณาคลิกที่ลิ้งค์ เพื่อยืนยันตัวตนทางอีเมล์ของท่าน<br/><br/>";
	    helper.setText(str + "<a href=\"" + app.getWebUrl() + "/activateEmail?userId=" + user.getUserId() + "&activateEmail=" + user.getActivateEmail() + "\">" + app.getWebUrl() + "/activateEmail?userId=" + user.getUserId() + "&activateEmail=" + user.getActivateEmail() + "</a>", true);
	    emailSender.send(message);
	}

	@Override
	public void sendEmailForgotPassword(User user) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");	 
		helper.setFrom(new InternetAddress(app.getSender()));
	    helper.setTo(user.getEmail());
	    helper.setSubject("ลืมพาสเวิร์ด");
	    String str = "กรุณาคลิกที่ลิ้งค์ เพื่อทำการรีเซตพาสเวิร์ดของท่าน<br/><br/>";
	    helper.setText(str + "<a href=\"" + app.getWebUrl() + "/ForgotPassword?id=" + user.getForgotPassword() + "&email=" + user.getEmail() + "\">" + app.getWebUrl() + "/forgotPassword?id=" + user.getForgotPassword() + "&email=" + user.getEmail() + "</a>", true);
	    emailSender.send(message);
	}

	@Override
	public void sendEmailSubmitBooking(String bookingId) throws Exception {
		try {
			Booking booking = bookingService.getBookingByBookingId(bookingId);
			 User user = userService.loadUserById(booking.getMerchantId());
			 List<BookingDetail> bookingDetails = new ArrayList<>();
			 for(BookingDetail bookingDetail : booking.getBookingDetails()) {
				 bookingDetail.setSimNumber(appUtils.parseSimFormat(bookingDetail.getSimNumber()));
				 bookingDetail.setPriceStr(appUtils.BigDecimalToCurrencyFormat(bookingDetail.getPrice()));
				 bookingDetails.add(bookingDetail);
			}
			InputStream employeeReportStream = getClass().getResourceAsStream("/templates/booking.jrxml");
			JRBeanCollectionDataSource sims = new JRBeanCollectionDataSource(booking.getBookingDetails());
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("bookingId", booking.getBookingId());
			parameters.put("name", user.getPrefixName() + user.getFirstName() + " " + user.getLastName());
			parameters.put("nickName", (user.getNickName() == null || user.getNickName() == "") ? "-" : user.getNickName());
			parameters.put("userAddress", user.getAddress() + " " + user.getProvinceStr() + " " + user.getPostcode());
			parameters.put("mobile", ((user.getMobile() == null || user.getMobile() == "") ? "-" : appUtils.parseSimFormat(user.getMobile())));
			parameters.put("email", user.getEmail());
			parameters.put("line", (user.getLine() == null || user.getNickName() == "") ? "-" : user.getLine());
			parameters.put("website", (user.getWebsite() == null || user.getNickName() == "") ? "-" : user.getWebsite());
			Map<String, Object> province = masterSetupService.getProvinceById(booking.getProvince());
			parameters.put("bookingAddress", booking.getAddress() + " " + province.get("DESCRIPTION") + " " + booking.getPostcode());
			parameters.put("bookingPostCode", booking.getPostcode());
			parameters.put("bookingProvince", booking.getProvince());
			parameters.put("bookingSumPrice", appUtils.BigDecimalToCurrencyFormat(booking.getSumPrice()));
			Map<String, Object> bank = masterSetupService.loadMasterSetupByPrefixAndId("BANK", booking.getBank());
			parameters.put("bookingBank", bank.get("DESCRIPTION") + "<br/>" + bank.get("SUB_DESCRIPTION"));
			parameters.put("Sim", sims);
			JasperReport jasperReport
			  = JasperCompileManager.compileReport(employeeReportStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			
			JRPdfExporter exporter = new JRPdfExporter();
			 
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(
			  new SimpleOutputStreamExporterOutput("BookingNo_" + booking.getBookingId() + ".pdf"));
			 
			SimplePdfReportConfiguration reportConfig
			  = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			 
			SimplePdfExporterConfiguration exportConfig
			  = new SimplePdfExporterConfiguration();
			exportConfig.setMetadataAuthor("vipsim.co");
			exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint("PRINTING");
			 
			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
			exporter.exportReport();
		    byte[] bytes = pdfReportStream.toByteArray();
	        pdfReportStream.close();
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");	 
	        /*Context context = new Context();
	        String html = templateEngine.process("submitBooking", context);*/
			helper.setFrom(new InternetAddress(app.getSender()));
		    helper.setTo(user.getEmail());
		    helper.addAttachment("BookingNo_" + booking.getBookingId() + ".pdf", new ByteArrayResource(bytes));
		    helper.setSubject("ยืนยันการจองเบอร์โทรศัพท์ Booking No. " + booking.getBookingId());
		    helper.setText("ยืนยันการจองเบอร์โทรศัพท์ Booking No. " + booking.getBookingId(), true);
		    emailSender.send(message);
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	@Override
	public Email getEmailById(int id) {
		Email pre = null;
		try {
			pre = emailDao.getEmailById(id);
		} catch(Exception ex){
			throw ex;
		}
		return pre;
	}

	@Override
	public void updateEmail(Email email) {
		try {
			emailDao.updateEmail(email);
		}catch(Exception ex){
			throw ex; 
		}
	}

}
