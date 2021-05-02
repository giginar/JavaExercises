package kucukcinar.yigit.hw2.mbeans;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import kucukcinar.yigit.hw2.business.CustomerService;
import kucukcinar.yigit.hw2.entity.BasePerson;
import kucukcinar.yigit.hw2.entity.Customer;
import kucukcinar.yigit.hw2.utils.HashUtil;

@Named
@SessionScoped
public class CustomerLoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4301656955159397091L;

	@EJB
	private CustomerService customerService;

	private Customer customer = new Customer();

	private String username;
	private String password;

	private boolean loggedIn;

	private String accessPage;

	public String login() {
		if(username.isEmpty() || password.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("Login Error", new FacesMessage("Information is missing!", "Please enter username and password"));
			return null;
		}
		BasePerson user = customerService.checkUser(username, password);

		if (user != null) {
			this.loggedIn = true;
			return this.accessPage;
		}
		FacesContext.getCurrentInstance().addMessage("Login Error",
				new FacesMessage("Information is missing!", "Please enter username and password"));
		return "/login?faces-redirect=true";
	}

	public String logout() {
		if (this.loggedIn == true ) {
			this.loggedIn = false;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.invalidate();
			return "/product";
		}
		FacesContext.getCurrentInstance().addMessage("Logout Error",
				new FacesMessage("You must be logged in first!", "Please login"));
		return "null";
	}

	public String saveCustomer() {
		customer.setPassword(HashUtil.hash(customer.getPassword()));
		customerService.addCustomer(customer);
		customer = new Customer();
		return "product";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getAccessPage() {
		return accessPage;
	}

	public void setAccessPage(String accessPage) {
		this.accessPage = accessPage;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
