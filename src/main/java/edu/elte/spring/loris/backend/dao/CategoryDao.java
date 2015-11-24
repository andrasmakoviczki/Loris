package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManager;
import edu.elte.spring.loris.backend.entity.Category;

public interface CategoryDao{

	public Category findCategory(String id);
	public Category findbyCategoryName(String caName);
	public List<Category> listCategory();
}
