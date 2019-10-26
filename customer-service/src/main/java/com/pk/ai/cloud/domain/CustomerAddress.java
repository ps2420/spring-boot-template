package com.pk.ai.cloud.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID")
	@ApiModelProperty(notes = "Customer address unique-id allocated by system")
	private String uuid;

	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes = "Customer Id of postal address")
	private String customerId;

	@ApiModelProperty(notes = "Customer address type for ex: Office/Personal")
	@Column(name = "TYPE")
	private String type;

	@ApiModelProperty(notes = "Customer address line 1")
	@Column(name = "ADDRESS_LINE_1")
	private String addressLine1;

	@ApiModelProperty(notes = "Customer address line 2")
	@Column(name = "ADDRESS_LINE_2")
	private String addressLine2;

	@ApiModelProperty(notes = "Customer address line 3")
	@Column(name = "ADDRESS_LINE_3")
	private String addressLine3;

	@ApiModelProperty(notes = "Customer address line 4")
	@Column(name = "ADDRESS_LINE_4")
	private String addressLine4;

	@ApiModelProperty(notes = "Customer country code")
	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@ApiModelProperty(notes = "Customer postal address zip code")
	@Column(name = "ZIP_CODE")
	private String zipCode;

	@ApiModelProperty(notes = "Customer address state")
	@Column(name = "STATE")
	private String state;

	@ApiModelProperty(notes = "Customer address city")
	@Column(name = "CITY")
	private String city;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
