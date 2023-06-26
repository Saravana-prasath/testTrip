package com.ezTrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezTrip.entity.TripPackage;

@Repository
public class TripPackageDaoImpl implements TripPackageDao{
	
	private static final String CREATE_TRIP_PACKAGE_QUERY = "INSERT INTO TripPackages(tripCategory, place, days, resorts, bookingPrice) values (?,?,?,?,?)";
	private static final String SELECT_ALL_TRIP_PACKAGES_QUERY = "select * from TripPackages";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int createTripPackage(TripPackage tripPackage) {
		return jdbcTemplate.update(CREATE_TRIP_PACKAGE_QUERY, tripPackage.getTripCategory(),
													   tripPackage.getPlace(),
													   tripPackage.getDays(),
													   tripPackage.getResorts(),
													   tripPackage.getBookingPrice()
								   );
	}

	@Override
	public List<TripPackage> findAll() {
		return jdbcTemplate.query(SELECT_ALL_TRIP_PACKAGES_QUERY, BeanPropertyRowMapper.newInstance(TripPackage.class));
	}

	@Override
	public List<TripPackage> findByCategory(String tripCategory) {
		String SELECT_PACKAGES_BY_CATEGORY = "select * from TripPackages WHERE tripCategory LIKE '%" + tripCategory + "%'";
		return jdbcTemplate.query(SELECT_PACKAGES_BY_CATEGORY, BeanPropertyRowMapper.newInstance(TripPackage.class));
	}

	@Override
	public List<TripPackage> findByPlace(String place) {
		String SELECT_PACKAGES_BY_PLACE = "select * from TripPackages where place LIKE '%" + place + "%'";
		return jdbcTemplate.query(SELECT_PACKAGES_BY_PLACE, BeanPropertyRowMapper.newInstance(TripPackage.class));
	}

	@Override
	public List<TripPackage> findByCategoryAndPlace(String tripCategory, String place) {
		String SELECT_PACKAGES_BY_BOTH = "select * from TripPackages where tripCategory LIKE '%" + tripCategory + "%' and place LIKE '%" + place + "%'";
		return jdbcTemplate.query(SELECT_PACKAGES_BY_BOTH, BeanPropertyRowMapper.newInstance(TripPackage.class));
	}

	@Override
	public int updateTripPackage(TripPackage tripPackage) {
		String UPDATE_TRIP_PACKAGE_BY_TRIP_ID = "update TripPackages set tripCategory=?, place=?, days=?, resorts=?, bookingPrice=? where tripId=?";
		return jdbcTemplate.update(UPDATE_TRIP_PACKAGE_BY_TRIP_ID, tripPackage.getTripCategory(),
																   tripPackage.getPlace(),
																   tripPackage.getDays(),
																   tripPackage.getResorts(),
																   tripPackage.getBookingPrice(),
																   tripPackage.getTripId()
													   );
	}

	@Override
	public TripPackage findByTripId(int tripId) {
		try {
			String SELECT_PACKAGE_BY_ID = "select * from TripPackages where tripId=?";
			return jdbcTemplate.queryForObject(SELECT_PACKAGE_BY_ID, BeanPropertyRowMapper.newInstance(TripPackage.class), tripId);
		} catch(IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(int tripId) {
			String DELETE_PACKAGE_BY_ID = "delete from TripPackages where tripId=?";
			return jdbcTemplate.update(DELETE_PACKAGE_BY_ID, tripId);
		
	}
	
	// ***********************************************************************************************************************************************************
	// For Pagination with Filter
	@Override
	public List<TripPackage> findBySearchAndPagination(String tripCategory, String place, int page) {
		int offset = (page-1)*5;
		String SEARCH_AND_PAGINATION = "select * from TripPackages order by tripCategory, place, days, bookingPrice where tripCategory LIKE '%" + tripCategory + "%' and place LIKE '%" + place + "%' limit " + 5 + " offset " + offset;
		return jdbcTemplate.query(SEARCH_AND_PAGINATION, BeanPropertyRowMapper.newInstance(TripPackage.class));	
	}
	
	@Override
	public List<TripPackage> findAllWithPagination(int page) {
		int offset = (page-1)*5;
		String FIND_ALL_WITH_PAGINATION = "select * from TripPackages order by tripCategory, place, days, bookingPrice limit " + 5 + " offset " + offset;
		return jdbcTemplate.query(FIND_ALL_WITH_PAGINATION, BeanPropertyRowMapper.newInstance(TripPackage.class));
	}

	@Override
	public List<TripPackage> findByCategoryWithPagination(String tripCategory, int page) {
		int offset = (page-1)*5;
		String FIND_BY_CATEGORY_WITH_PAGINATION = "select * from TripPackages order by tripCategory, place, days, bookingPrice where tripCategory LIKE '%" + tripCategory +"%' limit " + 5 + " offset " + offset;
		return jdbcTemplate.query(FIND_BY_CATEGORY_WITH_PAGINATION, BeanPropertyRowMapper.newInstance(TripPackage.class));
	}

	@Override
	public List<TripPackage> findByPlaceWithPagination(String place, int page) {
		int offset = (page-1)*5;
		String FIND_BY_PLACE_WITH_PAGINATION = "select * from TripPackages order by tripCategory, place, days, bookingPrice where place LIKE '%" + place +"%' limit " + 5 + " offset " + offset;
		return jdbcTemplate.query(FIND_BY_PLACE_WITH_PAGINATION, BeanPropertyRowMapper.newInstance(TripPackage.class));
	}

	// *****************************************************************************************************************************************************
	
}
