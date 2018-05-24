package com.sim.api.dao;

import java.util.List;

import com.sim.api.model.Booking;
import com.sim.api.model.BookingDetail;
import com.sim.api.model.BookingResponse;
import com.sim.api.model.Sim;

public interface BookingDao {

	String insertBooking(List<Sim> sims);
	void insertBookingDetail(BookingDetail bookingDetail);
	String generateBookingId();
	String generateBookingDetailId();
	List<BookingResponse> searchBooking(Booking search);
	void updateAutorizeStatus(Booking booking);
	void updateStatusUploadFile(Booking booking);
	String getSlipByBookingId(String userId, String bookingId);
	Booking getBookingByBookingId(String bookingId);
	List<BookingDetail> getBookingDetailByBookingId(String bookingId);
	void cancelBooking(String bookingId, String userId);
	void updateStatusUploadIdCard(BookingDetail bookingDetail);
	String getIdCardByBookingDetailId(String userId, String bookingDetailId);
	void rejectStatusIdCard(BookingDetail bookingDetail);
	void approveStatusIdCard(BookingDetail bookingDetail);

}
