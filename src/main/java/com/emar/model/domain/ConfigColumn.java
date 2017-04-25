package com.emar.model.domain;

/**
 * 封装域
 * 
 * @author franplk 2016.08.24
 */
public class ConfigColumn extends Column {

	private static final long serialVersionUID = 1L;

	private int dim;
	private int flag;
	private int sorting;
	private String formula;
	private String exValue;
	private String mapName;
	private String mapTitle;

	public ConfigColumn() {
	}

	public ConfigColumn(String field) {
		super(field);
	}

	public ConfigColumn(String field, String title) {
		this.field = field;
		this.title = title;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getSorting() {
		return sorting;
	}

	public void setSorting(int sorting) {
		this.sorting = sorting;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getExValue() {
		return exValue;
	}

	public void setExValue(String exValue) {
		this.exValue = exValue;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapTitle() {
		return mapTitle;
	}

	public void setMapTitle(String mapTitle) {
		this.mapTitle = mapTitle;
	}

	public TableColumn convert2TableColumn() {
		TableColumn tableCol = new TableColumn(field, title);
		tableCol.setSortable(true);
		tableCol.setDataType(dataType);
		if (dim != 1) {
			tableCol.setAlign("right");
		}
		return tableCol;
	}
}
