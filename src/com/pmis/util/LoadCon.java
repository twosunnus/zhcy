package com.pmis.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LoadCon {
 
	public Connection getCon() {
		 InputStream ins = getClass().getResourceAsStream("/com/pmis/jdbc.properties");
		 Properties p = new Properties();
		 try {
			p.load(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName(p.getProperty("jdbc.driverClassName"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					p.getProperty("jdbc.url"),p.getProperty("jdbc.username"), p.getProperty("jdbc.password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
