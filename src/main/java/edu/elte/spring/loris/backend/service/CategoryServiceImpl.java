package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.dao.CategoryDao;
import edu.elte.spring.loris.backend.dao.CategoryDaoImpl;
import edu.elte.spring.loris.backend.entity.Category;

public class CategoryServiceImpl implements CategoryService {

	CategoryDao caDao;
	
	public CategoryServiceImpl() {
		this.caDao = new CategoryDaoImpl();
	}

	@Override
	public void createCategory(Category ca) {	
		if (caDao.findbyCategoryName(ca.getCategoryName()) == null) {
			caDao.insertCategory(ca);
		}	
	}

	@Override
	public void removeCategory(Category ca) {
		caDao.removeCategory(ca);
	}

	@Override
	public Category findCategory(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findCategorybyCategoryname(String caName) {
		return caDao.findbyCategoryName(caName);
	}

	@Override
	public List<Category> listCategory() {
		return caDao.listCategory();
	}

}
