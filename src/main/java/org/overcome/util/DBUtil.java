package org.overcome.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.overcome.core.DBManager;
import org.overcome.core.DBManagerFactory;
import org.overcome.struct.Column;
import org.overcome.struct.Table;

public class DBUtil {

	private DBManager dbm = DBManagerFactory.getDBManager();
	
	public DBManager getDbm() {
		return dbm;
	}

	public List<Map<String,String>> getTables() {
		List<Map<String,String>> tbs = new ArrayList<Map<String,String>>();
		ResultSet rs = dbm.executeQuery("SELECT TABLE_NAME,TABLE_COMMENT FROM TABLES WHERE TABLE_SCHEMA='"+dbm.getConfigration().getDbSource().trim()+"';");
		try {
			while (rs.next()) {
				
				Map<String, String> tb = new HashMap<String,String>();
				tb.put("name", rs.getString(1));
				tb.put("comment", rs.getString(2));
				
				tbs.add(tb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tbs;
	}

	public List<Table> initTables(List<Map<String,String>> tableStrs) {
		List<Table> tables = new ArrayList<Table>();

		for (Map<String,String> tbsmap : tableStrs) {
			/*表基本信息*/
			String tableName = tbsmap.get("name");
			String tableComment = tbsmap.get("comment");
			
			Table table = new Table();
			table.setName(tableName);
			table.setDescription(tableComment);
			
			List<Column> columns = getTableColumns(dbm.getConfigration().getDbSource().trim(), tableName);
			table.setColumns(columns);
			tables.add(table);
		}

		return tables;
	}
	
	public List<Column> getTableColumns(String db,String table){
		 List<Column> columns = new ArrayList<Column>();

			StringBuffer sql = new StringBuffer();
			sql.append("	SELECT COLUMN_NAME,COLUMN_DEFAULT,IS_NULLABLE,COLUMN_TYPE,COLUMN_KEY,EXTRA,COLUMN_COMMENT  ")
				.append("	FROM COLUMNS ")
				.append("	WHERE TABLE_SCHEMA ='").append(db).append("' ")
				.append("   AND TABLE_NAME = '").append(table).append("' ");
			
			ResultSet rs = dbm.executeQuery(sql.toString());
			try {
				while (rs.next()) {
					String colName = rs.getString(1);
					String colDefault = rs.getString(2);
					String isNullable = rs.getString(3);
					String colType = rs.getString(4);
					String colKey = rs.getString(5);
					String extra = rs.getString(6);
					String colComment = rs.getString(7);
					
					Column column = new Column(); 
					column.setName(colName);
					column.setDefaultValue(colDefault);
					column.setIsNull("YES".equals(isNullable));
					column.setType(colType);
					column.setKey(colKey);
					column.setExtra(extra);
					column.setDescription(colComment);
					
					columns.add(column);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 
		 return columns;
	}

}
