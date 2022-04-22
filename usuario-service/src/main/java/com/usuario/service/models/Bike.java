package com.usuario.service.models;

import lombok.Data;

@Data
public class Bike {
	
	private String marca;
	private String modelo;
	
	private int usrId;

	public Bike() {
		super();
	}
}
