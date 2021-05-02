package kucukcinar.yigit.hw2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String city;
	private String district;
	private String street;
	private String apartmentNo;
	private String destription;
	@ManyToOne()
	private Customer customer;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String city, String district, String street, String apartmentNo, String destription,
			Customer customer) {
		super();
		this.city = city;
		this.district = district;
		this.street = street;
		this.apartmentNo = apartmentNo;
		this.destription = destription;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", district=" + district + ", street=" + street
				+ ", apartmentNo=" + apartmentNo + ", destription=" + destription + ", customer=" + customer + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getApartmentNo() {
		return apartmentNo;
	}

	public void setApartmentNo(String apartmentNo) {
		this.apartmentNo = apartmentNo;
	}

	public String getDestription() {
		return destription;
	}

	public void setDestription(String destription) {
		this.destription = destription;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
