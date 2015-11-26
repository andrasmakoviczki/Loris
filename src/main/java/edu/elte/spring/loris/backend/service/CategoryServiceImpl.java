package edu.elte.spring.loris.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if (caDao.findCategorybyCategoryName(ca.getCategoryName()).isEmpty()) {
			caDao.insert(ca);
		}
	}

	@Override
	public void removeCategory(Category ca) {
		caDao.remove(ca);
	}

	@Override
	public void updateCategory(Category ca) {
		caDao.merge(ca);
	}

	@Override
	public List<Category> listCategory() {
		return caDao.listCategory();
	}

	@Override
	public Category getCategory(String id) {
		return caDao.getCategory(id);
	}

	@Override
	public Category getCategorybyCategoryname(String caName) {
		List<Category> caList = caDao.findCategorybyCategoryName(caName);

		if (caList.isEmpty()) {
			return null;
		}

		return caList.get(0);
	}
}
