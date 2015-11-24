package edu.elte.spring.loris.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.elte.spring.loris.backend.dao.CategoryDao;
import edu.elte.spring.loris.backend.dao.CategoryDaoImpl;
import edu.elte.spring.loris.backend.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDaoImpl caDao;
	
	public CategoryServiceImpl() {
	}

	@Override
	public void createCategory(Category ca) {	
		if (caDao.findbyCategoryName(ca.getCategoryName()) == null) {
			caDao.insert(ca);
		}	
	}

	@Override
	public void removeCategory(Category ca) {
		caDao.remove(ca);
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
