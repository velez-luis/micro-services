package com.bike.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Bike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String marca;
	private String modelo;
	private int usrId;
	
	public Bike() {
		super();
	}
	
}
