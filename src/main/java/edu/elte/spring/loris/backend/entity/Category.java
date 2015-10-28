package edu.elte.spring.loris.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String id;

		@Column(name = "CATEGORY_NAME")
		private String categoryName;

		public Category() {
		}

		public Category(String id, String categoryName) {
			this.id = id;
			this.categoryName = categoryName;
		}

		public String getId() {
			return id;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Category [id=");
			builder.append(id);
			builder.append(", categoryName=");
			builder.append(categoryName);
			builder.append("]");
			return builder.toString();
		}
}
