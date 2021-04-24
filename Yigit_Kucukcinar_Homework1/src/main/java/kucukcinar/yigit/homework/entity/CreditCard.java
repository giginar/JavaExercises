package kucukcinar.yigit.homework.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String cardNumber;
	private String cardOwnerName;
	private String cvc;
	private Date expireDate;
	@ManyToOne
	private Customer customer;
	
	public CreditCard() {
		// TODO Auto-generated constructor stub
	}
	
	public CreditCard(String cardNumber, String cardOwnerName, String cvc, Date expireDate, Customer customer) {
		super();
		this.cardNumber = cardNumber;
		this.cardOwnerName = cardOwnerName;
		this.cvc = cvc;
		this.expireDate = expireDate;
		this.customer = customer;
	}
	public int getId() {
		return id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardOwnerName() {
		return cardOwnerName;
	}
	public void setCardOwnerName(String cardOwnerName) {
		this.cardOwnerName = cardOwnerName;
	}
	public String getcvc() {
		return cvc;
	}
	public void setCVC(String cvc) {
		this.cvc = cvc;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
