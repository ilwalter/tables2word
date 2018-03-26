package org.overcome.core;

import java.sql.ResultSet;

public interface DBManager {
	
	public Configration getConfigration();
	public void connect();
	public void close();
	public ResultSet executeQuery(String sql);
	

}
