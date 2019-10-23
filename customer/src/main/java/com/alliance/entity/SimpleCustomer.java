package com.alliance.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alliance.converter.AdditionalInfoConverter;

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

	@Convert(converter = AdditionalInfoConverter.class)
	private AdditionalInfo additionalInfo;

	@Column
	private LocalDateTime sysCreatedDatetime;

	@Column
	private LocalDateTime sysUpdatedDatetime;

	public LocalDateTime getSysCreatedDatetime() {
		return sysCreatedDatetime;
	}

	public void setSysCreatedDatetime(LocalDateTime sysCreatedDatetime) {
		this.sysCreatedDatetime = sysCreatedDatetime;
	}

	public LocalDateTime getSysUpdatedDatetime() {
		return sysUpdatedDatetime;
	}

	public void setSysUpdatedDatetime(LocalDateTime sysUpdatedDatetime) {
		this.sysUpdatedDatetime = sysUpdatedDatetime;
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

	public AdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(AdditionalInfo additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}