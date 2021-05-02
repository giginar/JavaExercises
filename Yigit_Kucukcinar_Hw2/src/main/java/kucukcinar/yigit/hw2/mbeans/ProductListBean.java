package kucukcinar.yigit.hw2.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import kucukcinar.yigit.hw2.business.CategoryService;
import kucukcinar.yigit.hw2.business.ProductService;
import kucukcinar.yigit.hw2.entity.Category;
import kucukcinar.yigit.hw2.entity.Product;

@RequestScoped
@Named
public class ProductListBean {
	@Inject
	private ProductService productService;
	@Inject
	private CategoryService categoryService;
	private Category category;
	private int categoryId;
	
	private List<Product> products = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		this.category = new Category();
	}
	
	public List<Product> getAllProducts() {
		this.products = new ArrayList<Product>();
		this.products = productService.getAllProducts();
		return products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Category> getAllCategories() {
		this.categories = categoryService.getAllCategories();
		return categories;
	}
	
	public List<Product> getAllProductsByCategory() {
		this.products = new ArrayList<Product>();
		this.products = productService.getAllProductsByCategory(categoryId);
		return products;
	}

	public void delete(int productID)
	{
		productService.deleteProduct(productID);
		this.products = productService.getAllProducts();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String updatePage() {
		return "updateproduct";
	}
	
}
