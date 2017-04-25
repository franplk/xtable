package com.emar.map.demo;

import com.emar.map.UDF;

public class CategoryId2NameUDF extends UDF {

	public CategoryId2NameUDF() {
		//load id to name map data
		//id2nameMap
	}
	
	public String evaluate(Integer id) {
		//name = id2nameMap.get(id);
		return "name" + id;
	}
	
	public String evaluate(String id) {
		return "name" + id;
	}
}
