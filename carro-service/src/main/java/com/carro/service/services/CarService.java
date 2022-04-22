package com.carro.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.service.entities.Car;
import com.carro.service.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepo;
	
	public List<Car> getAll(){
		return carRepo.findAll();
	}
	
	public Car getCarById(int id) {
		return carRepo.findById(id).orElse(null);
	}
	
	public List<Car> getCarByUserId(int id) {
		return carRepo.findCarByUsrId(id);
	}
	
	public Car save(Car car) {
		Car newCar = carRepo.save(car);
		return newCar;
	}
}
	