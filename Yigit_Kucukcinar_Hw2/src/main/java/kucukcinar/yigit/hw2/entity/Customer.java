package kucukcinar.yigit.hw2.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer extends BasePerson {

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private List<CreditCard> creditcards = new ArrayList<>();

	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private Basket basket;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String lastname, String email, String phone, String password) {
		super(name, lastname, email, phone, password);
		this.addresses = new ArrayList<>();
		this.creditcards = new ArrayList<>();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<CreditCard> getCreditcards() {
		return creditcards;
	}

	public void setCreditcards(List<CreditCard> creditcards) {
		this.creditcards = creditcards;
	}

}
