package org.overcome.struct;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private String description;
	private List<Column> columns = new ArrayList<Column>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	
	
}
