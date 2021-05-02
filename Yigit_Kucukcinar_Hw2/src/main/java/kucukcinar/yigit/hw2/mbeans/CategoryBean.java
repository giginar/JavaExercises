package kucukcinar.yigit.hw2.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import kucukcinar.yigit.hw2.business.CategoryService;
import kucukcinar.yigit.hw2.entity.Category;

@SessionScoped
@Named
public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7581499570891888816L;
	/**
	 * 
	 */

	private int idToUpdate;
	private String nameToUpdate;
	private String descriptionToUpdate;
	@Inject
	CategoryService categoryService;

	private Category newCategory = new Category();
	private List<Category> categories = new ArrayList<>();

	public String updateCategoryId(int id) {
		this.idToUpdate = id;
		return "/secure/updatecategory?faces-redirect=true";
		// TODO Auto-generated method stub

	}

	public String saveCategory() {
		categoryService.addCategory(newCategory);
		newCategory = new Category();
		this.idToUpdate = 0;
		this.nameToUpdate = "";
		this.descriptionToUpdate = "";
		return "/categorylist?faces-redirect=true";
	}

	public String deleteCategory(int categoryID) {
		categoryService.deleteCategory(categoryID);
		this.categories = categoryService.getAllCategories();
		this.idToUpdate = 0;
		this.nameToUpdate = "";
		this.descriptionToUpdate = "";
		return "/categorylist?faces-redirect=true";
	}

	public String updateCategory() {
		categoryService.updateCategory(idToUpdate, nameToUpdate, descriptionToUpdate);
		this.idToUpdate = 0;
		this.nameToUpdate = "";
		this.descriptionToUpdate = "";
		return "/categorylist?faces-redirect=true";
	}

	public Category getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(Category newCategory) {
		this.newCategory = newCategory;
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

	public String getDescriptionToUpdate() {
		return descriptionToUpdate;
	}

	public void setDescriptionToUpdate(String descriptionToUpdate) {
		this.descriptionToUpdate = descriptionToUpdate;
	}

}
