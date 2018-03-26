package org.overcome.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class Configration {

	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "";
	private String dbUser = "";
	private String dbPassword = "";
	private String dbSource = "";
	private String targetPath = "";
	private String targetFile = "";

	public Configration() throws IOException {
		Properties pro = new Properties();
		try {
			File directory = new File("");// 设定为当前文件夹
			/*//正式打包的时候使用*/
//			InputStreamReader in = new InputStreamReader(new FileInputStream(new File(directory.getCanonicalPath()+"\\config.properties")),Charset.forName("UTF-8"));
			//在项目中测试的时候使用
			InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/config.properties"),Charset.forName("UTF-8"));
			pro.load(in);
			in.close();
		} catch (Exception e) {
		}

		dbUrl = (String) pro.get("db.url");
		dbUser = (String) pro.get("db.user");
		dbPassword = (String) pro.get("db.password");
		dbSource = (String) pro.get("db.source");
		targetPath = (String) pro.get("target.path");
		targetFile = (String) pro.get("target.file");
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getDbSource() {
		return dbSource;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public String getTargetFile() {
		return targetFile;
	}

}
