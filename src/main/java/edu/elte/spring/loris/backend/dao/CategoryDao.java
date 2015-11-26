package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Category;

public interface CategoryDao {
	// Category id alapján
	public Category getCategory(String id);

	// Category listázása
	public List<Category> listCategory();

	// Category listázása kategórianév alapján
	public List<Category> findCategorybyCategoryName(String caName);
}
