package com.bike.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bike.service.entities.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer>{

	
	List<Bike> findBikeByUsrId(int usrId);
	
}
