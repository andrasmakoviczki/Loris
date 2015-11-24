package edu.elte.spring.loris.frontend.model;

public class SearchModel {
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchModel [term=");
		builder.append(term);
		builder.append("]");
		return builder.toString();
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	private String term;
}
