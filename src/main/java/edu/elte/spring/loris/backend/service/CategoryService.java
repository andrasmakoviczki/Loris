package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.Category;

public interface CategoryService {
	//Category létrehozása
	public void createCategory(Category ca);
	//Category törlése
	public void removeCategory(Category ca);
	//Category frissítése
	public void updateCategory(Category ca);
	//Category listázása
	public List<Category> listCategory();
	//Category id alapján
	public Category getCategory(String id);
	//Category kategórianév alapján
	public Category getCategorybyCategoryname(String caname);
}
