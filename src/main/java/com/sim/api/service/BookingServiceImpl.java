package com.sim.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sim.api.dao.BookingDao;
import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.BookingResponse;
import com.sim.api.model.Sim;

@Service
public class BookingServiceImpl implements BookingService {

	private static final Logger logger = Logger.getLogger(BookingServiceImpl.class);
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public String insertBooking(List<Sim> sim, String userId) throws Exception {
		String result = "";
		try {
			for(int i = 0; i < sim.size(); i++){
				sim.get(i).setLastUpdateBy(userId);
				sim.get(i).setCreatedBy(userId);
			}
			result = bookingDao.insertBooking(sim);
			emailService.sendEmailSubmitBooking(result);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public List<Booking> searchBooking(Booking search) {
		return genBookingResult(bookingDao.searchBooking(search));
	}
	
	private List<Booking> genBookingResult(List<BookingResponse> bs) {
		List<Booking> result = new ArrayList<>();
		try {
			String bookingId = "";
			Booking booking = new Booking();
			List<BookingDetail> details = new ArrayList<>();
			for(BookingResponse item : bs){
				if(!bookingId.equals(item.getBookingId())){
					if(StringUtils.isNotBlank(bookingId)){
						booking.setBookingDetails(details);
						result.add(booking);
						booking = new Booking();
						details = new ArrayList<>();
					}
					booking.setBookingId(item.getBookingId());
					booking.setBookingStatus(item.getBookingStatus());
					booking.setSlip(item.getSlip());
					booking.setRejectReason(item.getRejectReson());
					booking.setMerchantId(item.getMerchantId());
					booking.setSumPrice(item.getSumPrice());
					bookingId = item.getBookingId();
				}
				BookingDetail detail = new BookingDetail();
				detail.setActivateFlag(item.getActivateFlag());
				detail.setBookingDetailId(item.getBookingDetailId());
				detail.setCustomerId(item.getCustomerId());
				detail.setEffectiveDate(item.getEffectiveDate());
				detail.setPrice(item.getPrice());
				detail.setSimNumber(item.getSimNumber());
				details.add(detail);
			}
			if(StringUtils.isNotBlank(booking.getBookingId())){
				booking.setBookingDetails(details);
				result.add(booking);
				booking = null;
			}
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	public List<Booking> updateAutorizeStatus(Booking booking) {
		List<Booking> result = null;
		try {
			bookingDao.updateAutorizeStatus(booking);
			result = genBookingResult(bookingDao.searchBooking(new Booking()));
		} catch(Exception ex) {
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public List<Booking> updateStatusUploadFile(Booking booking) {
		List<Booking> result = null;
		try {
			bookingDao.updateStatusUploadFile(booking);
			booking.setBookingStatus("");
			booking.setBookingId("");
			result = genBookingResult(bookingDao.searchBooking(booking));
		} catch(Exception ex) {
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public String getSlipByBookingId(String userId, String bookingId) {
		return bookingDao.getSlipByBookingId(userId, bookingId);
	}
	@Override
	public String getIdCardByBookingDetailId(String userId, String bookingDetailId) {
		return bookingDao.getIdCardByBookingDetailId(userId, bookingDetailId);
	}

	@Override
	public Booking getBookingByBookingId(String bookingId) {
		Booking booking = bookingDao.getBookingByBookingId(bookingId);
		booking.setBookingDetails(bookingDao.getBookingDetailByBookingId(bookingId));
		return booking;
	}

	@Override
	public void cancelBooking(String bookingId, String userId) {
		bookingDao.cancelBooking(bookingId, userId);
	}

	@Override
	public List<Booking> updateStatusUploadIdCard(BookingDetail bookingDetail) {
		List<Booking> result = null;
		try {
			bookingDao.updateStatusUploadIdCard(bookingDetail);
			Booking booking = new Booking();
			booking.setMerchantId(booking.getLastUpdatedBy());
			result = genBookingResult(bookingDao.searchBooking(booking));
		} catch(Exception ex) {
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public List<Booking> rejectStatusIdCard(BookingDetail bookingDetail) {
		List<Booking> result = null;
		try {
			bookingDao.rejectStatusIdCard(bookingDetail);
			Booking booking = new Booking();
			result = genBookingResult(bookingDao.searchBooking(booking));
		} catch(Exception ex) {
			logger.error(ex);
			throw ex;
		}
		return result;
	}

	@Override
	public List<Booking> approveStatusIdCard(BookingDetail bookingDetail) {
		List<Booking> result = null;
		try {
			bookingDao.approveStatusIdCard(bookingDetail);
			Booking booking = new Booking();
			result = genBookingResult(bookingDao.searchBooking(booking));
		} catch(Exception ex) {
			logger.error(ex);
			throw ex;
		}
		return result;
	}
}
