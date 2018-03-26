package org.overcome.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleDBManager implements DBManager {

	private Configration cfg;
	public Connection conn = null;
	

	public SimpleDBManager() {
		try {
			cfg = new Configration();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connect() {
		try {
			if(conn == null){
				Class.forName(cfg.getDbDriver());
				conn = DriverManager.getConnection(cfg.getDbUrl(), cfg.getDbUser(), cfg.getDbPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void close() {
		try {
		if(conn != null)
			conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet executeQuery(String sql) {
		try {
			PreparedStatement   pst = conn.prepareStatement(sql);
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Configration getConfigration() {
		return this.cfg;
	}

}
