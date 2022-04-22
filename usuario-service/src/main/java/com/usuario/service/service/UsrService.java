package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entities.User;
import com.usuario.service.feign.BikeFeignClient;
import com.usuario.service.feign.CarFeignClient;
import com.usuario.service.models.Bike;
import com.usuario.service.models.Car;
import com.usuario.service.repository.UserRepository;

@Service
public class UsrService {

	@Autowired
	private UserRepository usrRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CarFeignClient carFeignClient;
	
	@Autowired
	private BikeFeignClient bikeFeignClient;

	public List<User> getAll() {
		return usrRepo.findAll();
	}

	public User getUserById(int id) {
		return usrRepo.findById(id).orElse(null);
	}

	public User save(User usr) {
		User user = usrRepo.save(usr);
		return user;
	}
	
	//RestTemplate
	public List<Car> getCars(int usrId) {
		List<Car> carros = restTemplate.getForObject("http://localhost:8082/car/user/" + usrId, List.class);
		return carros;
	}

	public List<Bike> getBikes(int usrId) {
		List<Bike> bikes = restTemplate.getForObject("http://localhost:8083/bike/usr/" + usrId, List.class);
		return bikes;
	}
	
	//FeignClient
	public Car saveCar(int usrId, Car car) {
		car.setUsrId(usrId);
		Car newCar = carFeignClient.saveCar(car);
		return newCar;
	}
	
	public Bike saveBike(int usrId, Bike bike) {
		bike.setUsrId(usrId);
		Bike newBike = bikeFeignClient.saveBike(bike);
		return newBike;
	}
	
	public Map<String, Object> getUsrVehicles(int usrId){
		Map<String, Object> result = new HashMap<>();
		User usr = usrRepo.findById(usrId).orElse(null);
		if(usr == null) {
			result.put("Mensaje", "User does not exist");
		}
		result.put("User", usr);
		List<Car> cars = carFeignClient.getCars(usrId);
		if(cars.isEmpty()) {
			result.put("Cars", "User does not have cars");
		}else {
			result.put("Cars", cars);
		}
		List<Bike> bikes = bikeFeignClient.getBikes(usrId);
		if(bikes.isEmpty()) {
			result.put("Bikes", "User does not have bikes");
		}else {
			result.put("Bikes", bikes);
		}
		return result;
	}
	
}
