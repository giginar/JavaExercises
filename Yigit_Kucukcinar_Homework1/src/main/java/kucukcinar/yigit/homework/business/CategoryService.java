package kucukcinar.yigit.homework.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kucukcinar.yigit.homework.entity.Category;
import kucukcinar.yigit.homework.entity.Product;
@Stateless
public class CategoryService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private List<Category> categories = new ArrayList<>();
	
	public List<Category> getAllCategories(){	
		this.categories = entityManager.createQuery("select c from Category c", Category.class).getResultList();
		System.out.println(categories.size());
		return categories;
	}
}
