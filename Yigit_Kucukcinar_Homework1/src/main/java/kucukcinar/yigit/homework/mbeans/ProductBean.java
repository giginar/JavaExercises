package kucukcinar.yigit.homework.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import kucukcinar.yigit.homework.business.ProductService;
import kucukcinar.yigit.homework.entity.Product;

@RequestScoped
@Named
public class ProductBean {
	@Inject
	private ProductService productService;
	
	private Product product = new Product();
	
	public void dummyData() {
		productService.createDummyData();
	}
	
	public String saveProduct()
	{
		productService.addProduct(product);
		return "productlist";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
