package kucukcinar.yigit.hw2.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kucukcinar.yigit.hw2.entity.Category;

@Stateless
public class CategoryService {

	@PersistenceContext
	private EntityManager entityManager;

	private List<Category> categories = new ArrayList<>();

	public List<Category> getAllCategories() {
		this.categories = entityManager.createQuery("select c from Category c", Category.class).getResultList();
		return categories;
	}

	public Category getCategory(int categoryId) {

		try {
			return entityManager.find(Category.class, categoryId);

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}
	}

	public boolean addCategory(Category category) {
		entityManager.persist(category);
		return true;
	}

	public boolean updateCategory(int id, String name, String description) {
		Category category = entityManager.find(Category.class, id);
		category.setName(name);
		category.setDescription(description);
		return true;
	}


	public void deleteCategory(int categoryID) {
		Category deletedCategory = entityManager.find(Category.class, categoryID);
		entityManager.remove(deletedCategory);
	}

}
