package com.sim.api.service;

import java.util.List;
import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.Sim;

public interface BookingService {

	String insertBooking(List<Sim> sim, String userId) throws Exception;
	List<Booking> searchBooking(Booking search);
	List<Booking> updateAutorizeStatus(Booking booking);
	List<Booking> updateStatusUploadFile(Booking booking);
	String getSlipByBookingId(String userId, String bookingId);
	Booking getBookingByBookingId(String bookingId);
	void cancelBooking(String bookingId, String userId);
	List<Booking> updateStatusUploadIdCard(BookingDetail bookingDetail);
	String getIdCardByBookingDetailId(String userId, String bookingDetailId);
	List<Booking> rejectStatusIdCard(BookingDetail bookingDetail);
	List<Booking> approveStatusIdCard(BookingDetail bookingDetail);
}
