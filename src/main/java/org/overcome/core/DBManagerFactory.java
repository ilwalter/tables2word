package org.overcome.core;

public class DBManagerFactory {
	
	public static DBManager getDBManager(){
		DBManager dbm = new SimpleDBManager();
		dbm.connect();
		return dbm;
	}
	

}
