package com.ezTrip.entity;

import lombok.Data;

@Data
public class TripPackage {
	
	private int tripId;
	private String tripCategory;
	private String place;
	private String days;
	private String resorts;
	private String bookingPrice;
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public String getTripCategory() {
		return tripCategory;
	}
	public void setTripCategory(String tripCategory) {
		this.tripCategory = tripCategory;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	public String getResorts() {
		return resorts;
	}
	
	public void setResorts(String resorts) {
		this.resorts = resorts;
	}
	public String getBookingPrice() {
		return bookingPrice;
	}
	public void setBookingPrice(String bookingPrice) {
		this.bookingPrice = bookingPrice;
	}
	
}
