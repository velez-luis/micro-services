package com.carro.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carro.service.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

	
	List<Car> findCarByUsrId(int usrId);
	
}
