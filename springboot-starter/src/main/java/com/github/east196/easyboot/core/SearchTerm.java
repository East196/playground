package com.github.east196.easyboot.core;

import java.util.HashMap;

public class SearchTerm extends HashMap<String,String> {
	
	private static final long serialVersionUID = 1L;

	public enum Relation {
		EQ,

		LIKE,

		GT,

		LT,

		GTE,

		LTE;
	}

	
	public static void main(String[] args) {
		System.out.println(Relation.valueOf("EQ"));
	}
}
