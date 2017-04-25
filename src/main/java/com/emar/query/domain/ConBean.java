/**
 * 
 */
package com.emar.query.domain;

import java.io.Serializable;

import com.emar.udf.Aim;

/**
 * @author franplk
 *
 */
public class ConBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aim field;
	private Aim type;
	private Aim label;

	public ConBean() {
	}

	public Aim getField() {
		return field;
	}

	public void setField(Aim field) {
		this.field = field;
	}

	public Aim getType() {
		return type;
	}

	public void setType(Aim type) {
		this.type = type;
	}

	public Aim getLabel() {
		return label;
	}

	public void setLabel(Aim label) {
		this.label = label;
	}
}
