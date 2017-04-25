package com.emar.udf;

/**
 * @author franplk
 * 
 * Single Key-Value Object
 * 
 */
public class Aim {
	
	private String name;
	private String value;

	public Aim (String name) {
		this(name, name);
	}
	
	public Aim (String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
