package com.example.databasefactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseFactory {
	public final Connection getConnection() {
		Connection connection = null;
		
		try {
//			Driver driver = new Driver();
			String url = "jdbc:postgresql://localhost:5432/test";
//			Properties props = new Properties();
//			props.setProperty("user","postgres");
//			props.setProperty("password","password");
//			props.setProperty("ssl","false");
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, "postgres", "password");
			
			
//			MysqlDataSource dataSource = new MysqlDataSource();
//			
//			
//			dataSource.setUser("postgres");
//			dataSource.setPassword("password");
//			dataSource.setUrl("jdbc:postgresql://localhost:5432/test");
//			
//			connection = dataSource.getConnection();
			
			System.out.println("Connected Successfully");
		}
		catch (SQLException sqlException) {
			System.err.println("Error connecting to the databasesql: "+sqlException.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error connecting to the database: "+e.getMessage());
//			e.printStackTrace();
		}
		
		return connection;
	}
}
