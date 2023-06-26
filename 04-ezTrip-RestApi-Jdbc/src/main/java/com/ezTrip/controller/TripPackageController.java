package com.ezTrip.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezTrip.dao.TripPackageDao;
import com.ezTrip.entity.TripPackage;

@RestController
@RequestMapping("/ezTrip")
public class TripPackageController {
	
	@Autowired
	private TripPackageDao packageDao;
	
	// API for create Trip Package *********************************************************************************
	@PostMapping("/package/create")
	public ResponseEntity<String> createPackage(@RequestBody TripPackage tripPackage){
		packageDao.createTripPackage(tripPackage);
		return new ResponseEntity<>("New Package created", HttpStatus.CREATED);
	}

	// API for update packages by category***************************************************************************
		@PostMapping("/package/{Id}/update")
		public ResponseEntity<String> updatePackage(@PathVariable("Id") int tripId ,
													@RequestBody TripPackage tripPackage){
			TripPackage packageFind = packageDao.findByTripId(tripId);
			if(packageFind == null) {
				return new ResponseEntity<>("This Category not available.", HttpStatus.NOT_FOUND);
			} else {
				packageDao.updateTripPackage(tripPackage);
				return new  ResponseEntity<>("Package updated", HttpStatus.OK);
			}
		}
		
		// API for delete Trip Package by Id************************************************************************
		@PostMapping("/package/{Id}/delete")
		public ResponseEntity<String> deleteTripPackage(@PathVariable("Id") int tripId){
			TripPackage packageFind = packageDao.findByTripId(tripId);
			if(packageFind == null) {
				return new ResponseEntity<>("This Category not available.", HttpStatus.NOT_FOUND);
			} else {
				packageDao.deleteById(tripId);
				return new  ResponseEntity<>("Package deleted", HttpStatus.OK);
			}
			
		}
	
	
	// API for Get all Trip Packages *******************************************************************************
	@GetMapping("/allPackages")
	public ResponseEntity<List<TripPackage>> getAllPackages(){
		List<TripPackage> packages = new ArrayList<TripPackage>();
		packageDao.findAll().forEach(packages :: add);
		
		if (packages.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(packages,HttpStatus.OK);
	}
	
	// API for Get Packages by Trip Category ********************************************************************************
	@GetMapping("/packages/tripCategory/{tripCategory}")
	public ResponseEntity<List<TripPackage>> getPackagesByCategory(@PathVariable("tripCategory") String tripCategory){
		List<TripPackage> packages = new ArrayList<TripPackage>();
		packageDao.findByCategory(tripCategory).forEach(packages :: add);
		
		if (packages.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(packages,HttpStatus.OK);
		
	}
	
	// API for Get Packages by Places *****************************************************************************************
	@GetMapping("/packages/place/{place}")
	public ResponseEntity<List<TripPackage>> getPackagesByPlace(@PathVariable("place") String place){
		List<TripPackage> packages = new ArrayList<TripPackage>();
		packageDao.findByPlace(place).forEach(packages :: add);
		
		if (packages.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(packages,HttpStatus.OK);
	}
	
	// API for search packages by category or place or search by both parameters ***********************************************************************
	@GetMapping("/packages")
	public ResponseEntity<List<TripPackage>> searchPackagesByQuery(@RequestParam(value = "tripCategory", required = false) String tripCategory,
																   @RequestParam(value = "place", required = false) String place){
		List<TripPackage> packages = new ArrayList<TripPackage>();
		
		if(tripCategory == null && place == null) {
			packageDao.findAll().forEach(packages :: add);
		} else if(tripCategory != null && place != null) {
			packageDao.findByCategoryAndPlace(tripCategory, place).forEach(packages :: add);
		} else if(tripCategory != null && place == null) {
			packageDao.findByCategory(tripCategory).forEach(packages :: add);	
		} else if (tripCategory == null && place != null){
			packageDao.findByPlace(place).forEach(packages :: add);
		}
		
		if (packages.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(packages,HttpStatus.OK);
		
	}
	
	// API for Trip Package Subscription ********************************************************************************************************************
	
	//@PostMapping("/subscription")
	
	
	// API for Get Packages by using filtering with pagination ******************************************************************************************************
	@GetMapping("/packages/search")
	public ResponseEntity<List<TripPackage>> searchPackagesWithPagination(@RequestParam(value = "tripCategory", required = false) String tripCategory,
																		  @RequestParam(value = "place", required = false) String place,
																		  @RequestParam(defaultValue = "1") int page
																		  ){
		List<TripPackage> packages = new ArrayList<TripPackage>();
		
		if(tripCategory == null && place == null) {
			packageDao.findAllWithPagination(page).forEach(packages :: add);
		} else if(tripCategory != null && place != null) {
			packageDao.findBySearchAndPagination(tripCategory, place, page).forEach(packages :: add);
		} else if(tripCategory != null && place == null) {
			packageDao.findByCategoryWithPagination(tripCategory, page).forEach(packages :: add);	
		} else if (tripCategory == null && place != null){
			packageDao.findByPlaceWithPagination(place, page).forEach(packages :: add);
		}
		
		if (packages.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(packages,HttpStatus.OK); 
																			  
	}
	
	//*******************************************************************************************************************************************************

}
