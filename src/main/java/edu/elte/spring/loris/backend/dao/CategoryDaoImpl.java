package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;

public class CategoryDaoImpl implements CategoryDao {

	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

	private GeneralEntityManagerImpl em;

	public CategoryDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}

	@Override
	public void insertCategory(Category ca) {
		em.insert(ca);
		logger.info("Category {} information successfully inserted.", ca);
	}

	@Override
	public void removeCategory(Category ca) {
		em.remove(ca);
		logger.info("Category {} information successfully removed.", ca);
	}

	@Override
	public Category findCategory(String id) {
		Category ca = em.findById(Category.class, id);
		return ca;
	}

	@Override
	public List<Category> listCategory() {
		List<?> q = em.findByQuery("select ca from Category ca");

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
		List<?> q = em.findByQuery(query, "caname", caname);

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

	@Override
	public void updateCategory(Category ca) {
		em.merge(ca);
		logger.info("Category {} information successfully updated.", ca);
	}

}
