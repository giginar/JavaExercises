package kucukcinar.yigit.hw2.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kucukcinar.yigit.hw2.entity.Retailer;
import kucukcinar.yigit.hw2.utils.HashUtil;

@Stateless
public class RetailerService {

	@PersistenceContext
	private EntityManager entityManager;

	public Retailer checkUser(String username, String password) {
		password = HashUtil.hash(password);

		List<Retailer> retailers = entityManager
				.createQuery("select r from Retailer r where r.name=?1 and c.password=?2", Retailer.class)
				.setParameter(1, username).setParameter(2, password).getResultList();
		if (retailers.size() == 1) {
			return retailers.get(0);
		}
		return null;

	}

}