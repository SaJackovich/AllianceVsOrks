package com.alliance.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimpleCustomer implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;

	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String firstName;

	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String lastName;

	public SimpleCustomer() {
	}

	public SimpleCustomer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public BigInteger getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}