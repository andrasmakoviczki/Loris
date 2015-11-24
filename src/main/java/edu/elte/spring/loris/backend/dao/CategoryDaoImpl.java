package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import edu.elte.spring.loris.backend.dao.model.GeneralEntityManager;
import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;
import org.springframework.stereotype.Repository;
@Repository
public class CategoryDaoImpl extends GeneralEntityManagerImpl<Category> implements CategoryDao {

	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);


	/*public CategoryDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}*/

	@Override
	public Category findCategory(String id) {
		Category ca = findById(Category.class, id);
		return ca;
	}

	@Override
	public List<Category> listCategory() {
		List<?> q = findByQuery("select ca from Category ca");

		List<Category> caList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Category) {
				caList.add((Category) object);
			}
		}

		return caList;
	}

	@Override
	public Category findbyCategoryName(String caname) {

		String query = new String(
				"select ca from " + Category.class.getSimpleName() + " ca where ca.categoryName=:caname");
		List<?> q = findByQuery(query, "caname", caname);

		if (q.isEmpty()) {
			return null;
		}

		List<Category> caList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Category) {
				caList.add((Category) object);
			}
		}

		return caList.get(0);
	}

}
