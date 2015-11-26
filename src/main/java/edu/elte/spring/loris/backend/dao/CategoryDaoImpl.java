package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;


@Repository
public class CategoryDaoImpl extends GeneralEntityManagerImpl<Category> implements CategoryDao {

	@Override
	public Category getCategory(String id) {
		Category ca = findById(Category.class, id);
		return ca;
	}

	@Override
	public List<Category> findCategorybyCategoryName(String caname) {

		String query = new String(
				"SELECT ca FROM " + Category.class.getSimpleName() + " ca WHERE ca.categoryName=:caname");
		List<?> q = findByQuery(query, "caname", caname);

		List<Category> caList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Category) {
				caList.add((Category) object);
			}
		}

		return caList;
	}

	@Override
	public List<Category> listCategory() {
		List<?> q = findByQuery("SELECT ca FROM Category ca");

		List<Category> caList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Category) {
				caList.add((Category) object);
			}
		}

		return caList;
	}

}
