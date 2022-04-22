package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entities.User;
import com.usuario.service.models.Bike;
import com.usuario.service.models.Car;
import com.usuario.service.repository.UserRepository;
import com.usuario.service.service.UsrService;

@RestController
@RequestMapping("/usr")
public class UserController {
	
	@Autowired
	private UsrService usrService;
	
	@GetMapping
	public ResponseEntity<List<User>> listUsers(){
		List<User> users = usrService.getAll();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id){
		User user = usrService.getUserById(id);
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User newUsr = usrService.save(user);
		return ResponseEntity.ok(newUsr);
	}
	
	@GetMapping("/cars/{usrId}")
	public ResponseEntity<List<Car>> getCarsByUser(@PathVariable("usrId") int usrId){
		User user = usrService.getUserById(usrId);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}		
		List<Car> cars = usrService.getCars(usrId);
		return ResponseEntity.ok(cars);
	}
	
	@GetMapping("/bikes/{usrId}")
	public ResponseEntity<List<Bike>> getBikesByUser(@PathVariable("usrId") int usrId){
		User user = usrService.getUserById(usrId);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}		
		List<Bike> bikes = usrService.getBikes(usrId);
		return ResponseEntity.ok(bikes);
	}
	
	//Save With FeginClient
	@PostMapping("/car/{usrId}")
	public ResponseEntity<Car> saveCar(@PathVariable("usrId") int usrId, @RequestBody Car car){
		Car newCar = usrService.saveCar(usrId, car);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/bike/{usrId}")
	public ResponseEntity<Bike> saveBike(@PathVariable("usrId") int usrId, @RequestBody Bike bike){
		Bike newBike = usrService.saveBike(usrId, bike);
		return ResponseEntity.ok(newBike);
	}
	
	@GetMapping("/all/{usrId}")
	public ResponseEntity< Map<String, Object>> getAllUserVehicles(@PathVariable("usrId") int usrId){
		Map<String, Object> result = usrService.getUsrVehicles(usrId);
		return ResponseEntity.ok(result);
	}
}
