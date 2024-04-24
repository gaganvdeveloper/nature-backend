package com.gbss.nature.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String doorNo;
	private String customerName;
	private String email;
	private long phone;
	private long alternatePhone;
	private String landmark;
	private int pincode;
	private String address;
	private String city;
	private String state;
}
