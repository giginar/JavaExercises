package kucukcinar.yigit.hw2.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kucukcinar.yigit.hw2.entity.Customer;
import kucukcinar.yigit.hw2.utils.HashUtil;

@Stateless
public class CustomerService {

	@PersistenceContext
	private EntityManager entityManager;

	public Customer checkUser(String name, String password) {
		password = HashUtil.hash(password);
		List<Customer> customers = entityManager
				.createQuery("select c from Customer c where c.name=?1 and c.password=?2", Customer.class)
				.setParameter(1, name).setParameter(2, password).getResultList();
		if (customers.size() == 1) {
			return customers.get(0);
		}
		return null;

	}

	public boolean addCustomer(Customer customer) {
		entityManager.persist(customer);
		return true;
	}

}
