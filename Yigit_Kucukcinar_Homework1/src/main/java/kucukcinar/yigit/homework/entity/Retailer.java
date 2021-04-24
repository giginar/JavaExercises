package kucukcinar.yigit.homework.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Retailer extends BasePerson {

	public Retailer(String name, String lastname, String email, String phone) {
		super(name, lastname, email, phone);
		this.wallet = new Wallet();
	}

	@OneToOne
	private Wallet wallet;

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}