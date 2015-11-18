package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Category;

public interface CategoryDao {
	public void insertCategory(Category ca);
	public void updateCategory(Category ca);
	public Category findCategory(String id);
	public Category findbyCategoryName(String caName);
	public List<Category> listCategory();
	public void removeCategory(Category ca);
}
