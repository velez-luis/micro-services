package com.bike.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bike.service.entities.Bike;
import com.bike.service.repository.BikeRepository;

@Service
public class BikeService {

	@Autowired
	private BikeRepository bikeRepository;
	
	public List<Bike> getAll(){
		return bikeRepository.findAll();
	}
	
	public Bike getBikeByID(int id) {
		return bikeRepository.findById(id).orElse(null);
	}
	
	public Bike save(Bike bike) {
		return bikeRepository.save(bike);
	}
	
	public List<Bike> getBikesByUsrId(int usrId){
		return bikeRepository.findBikeByUsrId(usrId);
	}
}
