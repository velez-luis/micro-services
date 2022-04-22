package com.usuario.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usuario.service.models.Bike;

@FeignClient(name="bike-service", url ="http://localhost:8083")
@RequestMapping("/bike")
public interface BikeFeignClient {
	
	@PostMapping
	public Bike saveBike(@RequestBody Bike bike);
	
	@GetMapping("usr/{usrId}")
	public List<Bike> getBikes(@PathVariable("usrId") int usrId);

}
