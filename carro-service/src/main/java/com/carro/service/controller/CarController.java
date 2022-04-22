package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carro.service.entities.Car;
import com.carro.service.services.CarService;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping
	public ResponseEntity<List<Car>> getCars() {
		List<Car> cars = carService.getAll();
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable("id") int id) {
		Car car = carService.getCarById(id);
		if (car == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}
	
	@GetMapping("/user/{usrId}")
	public ResponseEntity<List<Car>> getCarByUsrId(@PathVariable("usrId") int id){
		List<Car> cars = carService.getCarByUserId(id);
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}
	
	@PostMapping
	public ResponseEntity<Car> saveCar(@RequestBody Car car){
		Car newCar = carService.save(car);
		return ResponseEntity.ok(newCar);
	}

}
