package com.ezTrip.dao;

import java.util.List;

import com.ezTrip.entity.TripPackage;

public interface TripPackageDao {
	
	public int createTripPackage(TripPackage tripPackage);
	public List<TripPackage> findAll();
	public List<TripPackage> findByCategory(String tripCategory);
	public List<TripPackage> findByPlace(String place);
	public List<TripPackage> findByCategoryAndPlace(String tripCategory, String place);
	public int updateTripPackage(TripPackage tripPackage);
	public TripPackage findByTripId(int tripId);
	public int deleteById(int tripId);
	
	public List<TripPackage> findBySearchAndPagination(String tripCategory, String place, int page);
	public List<TripPackage> findAllWithPagination(int page);
	public List<TripPackage> findByCategoryWithPagination(String tripCategory, int page);
	public List<TripPackage> findByPlaceWithPagination(String place, int page);

}
