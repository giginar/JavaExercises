package kucukcinar.yigit.homework.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kucukcinar.yigit.homework.entity.Category;
import kucukcinar.yigit.homework.entity.Product;

@Stateless
public class ProductService {

	@PersistenceContext
	private EntityManager entityManager;

	public void createDummyData() {

		for (int i = 1; i < 6; i++) {

			Random rand = new Random();
			int randomNr = rand.nextInt(50);

			StringBuilder strBuild = new StringBuilder();
			strBuild.append("Category" + i);

			String catDesc = "";

			switch (i) {

			case 1:
				catDesc = "Electronic";
				break;

			case 2:
				catDesc = "Clothes";
				break;

			case 3:
				catDesc = "Home Design";
				break;
			case 4:
				catDesc = "Kids";
				break;
			case 5:
				catDesc = "Gardening";
				break;
			}

			String catName = strBuild.toString();
			Category category = new Category(catName, catDesc);
			entityManager.persist(category);
			Product p = new Product("Product1" + i, randomNr * 5000, category, randomNr * 20);
			Product p2 = new Product("Product2" + i, randomNr * 200, category, randomNr * 15);
			Product p3 = new Product("Product3" + i, randomNr * 2248, category, randomNr * 10);
			entityManager.persist(p);
			entityManager.persist(p2);
			entityManager.persist(p3);
		}

	}

	public List<Product> getAllProducts() {
		return entityManager.createQuery("select p from Product p", Product.class).getResultList();
	}

	public List<Product> getAllProductsByCategory(int catId) {
		System.out.println("------------------******************Servis catid " + catId);
		List<Product> products = entityManager.createQuery("select p from Product p where p.category.id = ?1").setParameter(1, catId).getResultList();
		System.out.println("**********---------" + products.size());
		return products;
	}

	public boolean addProduct(Product product) {
		entityManager.persist(product);
		return true;
	}

	public void deleteProduct(int productID) {
		Product deletedProduct = entityManager.find(Product.class, productID);
		entityManager.remove(deletedProduct);
	}
}