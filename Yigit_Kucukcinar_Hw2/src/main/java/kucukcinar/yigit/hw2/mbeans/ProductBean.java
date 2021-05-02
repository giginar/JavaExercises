package kucukcinar.yigit.hw2.mbeans;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import kucukcinar.yigit.hw2.business.CategoryService;
import kucukcinar.yigit.hw2.business.ProductService;
import kucukcinar.yigit.hw2.entity.Category;
import kucukcinar.yigit.hw2.entity.Product;

@SessionScoped
@Named
public class ProductBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467187109917799853L;
	/**
	 * 
	 */

	private int idToUpdate;
	private String nameToUpdate;
	private double priceToUpdate;
	private int stockToUpdate;

	@Inject
	private ProductService productService;

	@Inject
	private CategoryService categoryService;

	private Product product = new Product();
	private int categoryId;
	private int productid;

	public void dummyData() {
		productService.createDummyData();
	}

	@PostConstruct
	public void init() {
		//System.out.println("******-------->>>>>>>>" + productid);
	}

	public String saveProduct() {
		Category category = categoryService.getCategory(categoryId);
		product.setCategory(category);
		productService.addProduct(product);
		product = new Product();
		categoryId = 0;
		return "/productlist?faces-redirect=true";
	}

	public String deleteProduct(int ProductID) {
		productService.deleteProduct(ProductID);
		this.product = new Product();
		product = new Product();
		this.idToUpdate = 0;
		this.nameToUpdate = "";
		this.priceToUpdate = 0;
		this.stockToUpdate = 0;
		return "/productlist?faces-redirect=true";
	}

	public String updateProductId(int id) {
		this.idToUpdate = id;
		return "/secure/updateproduct?faces-redirect=true";
	}

	public String updateProduct() {
		Category category = categoryService.getCategory(categoryId);
		productService.updateProduct(idToUpdate, nameToUpdate, priceToUpdate, stockToUpdate, category);
		this.idToUpdate = 0;
		this.nameToUpdate = "";
		this.priceToUpdate = 0;
		this.stockToUpdate = 0;
		return "/productlist?faces-redirect=true";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getIdToUpdate() {
		return idToUpdate;
	}

	public void setIdToUpdate(int idToUpdate) {
		this.idToUpdate = idToUpdate;
	}

	public String getNameToUpdate() {
		return nameToUpdate;
	}

	public void setNameToUpdate(String nameToUpdate) {
		this.nameToUpdate = nameToUpdate;
	}

	public double getPriceToUpdate() {
		return priceToUpdate;
	}

	public void setPriceToUpdate(double priceToUpdate) {
		this.priceToUpdate = priceToUpdate;
	}

	public int getStockToUpdate() {
		return stockToUpdate;
	}

	public void setStockToUpdate(int stockToUpdate) {
		this.stockToUpdate = stockToUpdate;
	}

}
