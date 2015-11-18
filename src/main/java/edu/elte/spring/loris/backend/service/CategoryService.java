package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Category;

public interface CategoryService {
	public void createCategory(Category ca);
	public void removeCategory(Category ca);
	public Category findCategory(String id);
	public Category findCategorybyCategoryname(String caname);
	public List<Category> listCategory();
}
