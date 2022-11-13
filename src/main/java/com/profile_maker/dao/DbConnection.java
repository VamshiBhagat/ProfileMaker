package com.profile_maker.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.profile_maker.utils.ConfigLoader;

public class DbConnection {

	public Connection getDbConnection() throws ClassNotFoundException, SQLException, IOException {
		ConfigLoader loader = new ConfigLoader();
		Properties properties = loader.getDbConfigs();
		Class.forName(properties.getProperty("db.driver"));
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profile_maker",properties.getProperty("db.username"), properties.getProperty("db.password"));
		return con;
	}
}
