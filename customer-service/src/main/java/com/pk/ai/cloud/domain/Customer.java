package com.pk.ai.cloud.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID")
	@ApiModelProperty(notes = "Customer unique-id allocated by system")
	private String uuid;

	@ApiModelProperty(notes = "Customer first name")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@ApiModelProperty(notes = "Customer last name")
	@Column(name = "LAST_NAME")
	private String lastName;

	@ApiModelProperty(notes = "Customer middle name")
	private String middleName;

	@ApiModelProperty(notes = "Customer date of birth")
	private Date dateOfBirth;

	@ApiModelProperty(notes = "Customer mobile number with country code")
	private String mobileNb;

	@ApiModelProperty(notes = "Customer number For ex: <CountryCode>10042001")
	private String customerNb;

	@ApiModelProperty(notes = "Customer country of birth")
	private String countryOfBirth;

	@ApiModelProperty(notes = "Customer country of residence")
	private String countryOfResidence;

	@ApiModelProperty(notes = "Customer segment for ex: Retail/Private Bank")
	private String customerSegment;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNb() {
		return mobileNb;
	}

	public void setMobileNb(String mobileNb) {
		this.mobileNb = mobileNb;
	}

	public String getCustomerNb() {
		return customerNb;
	}

	public void setCustomerNb(String customerNb) {
		this.customerNb = customerNb;
	}

	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	public String getCustomerSegment() {
		return customerSegment;
	}

	public void setCustomerSegment(String customerSegment) {
		this.customerSegment = customerSegment;
	}

}
