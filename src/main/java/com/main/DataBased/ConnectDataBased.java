package com.main.DataBased;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class ConnectDataBased {
	public static String url = "jdbc:mysql://v93966.hosted-by-vdsina.ru/admin_bd?useSSL=false&characterEncoding=cp1251";
	public static String user = "admin_root";
	public static String password = "i5C28zB9q4heHnwd";
	
	public  PreparedStatement connectDB(String query) {
		PreparedStatement statement = null;
		try {
			Connection connect = DriverManager.getConnection(url, user, password);
			statement = (PreparedStatement) connect.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	                    
		return statement;
	}
}
