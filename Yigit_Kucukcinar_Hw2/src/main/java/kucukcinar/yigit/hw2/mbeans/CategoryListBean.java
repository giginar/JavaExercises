package kucukcinar.yigit.hw2.mbeans;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import kucukcinar.yigit.hw2.business.CategoryService;
import kucukcinar.yigit.hw2.entity.Category;

@RequestScoped
@Named
public class CategoryListBean {

	@Inject
	CategoryService categoryService;

	private List<Category> categories = new ArrayList<>();

	@PostConstruct
	public void init() {

	}

	public List<Category> getCategories() {
		this.categories = new ArrayList<Category>();
		this.categories = categoryService.getAllCategories();
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String updatePage() {
		return "updatecategory";
	}
}
