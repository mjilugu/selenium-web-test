package org.mjilugu.selenium.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBConnection {
	public static void main(String[] args){
		String sql = "select PAGING_ATTEMPTS_ENB, PAGING_ATTEMPTS_TA, PAGING_ATTEMPTS_TA_LIST, datetime_id from event_e_lte_err_raw where day_id = 5 and month_id = 8 and event_id = 13 and paging_attempts_ta != null";
		DBConnectionHelper connection = DBConnectionHelper.getInstance();
		
		try{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
//				System.out.println("Result line: " + resultSet.getInt("PAGING_ATTEMPTS_ENB") + ", " +
//						resultSet.getInt("PAGING_ATTEMPTS_TA") + ", " + resultSet.getInt("PAGING_ATTEMPTS_TA_LIST") + ", " +
//						resultSet.getDate("datetime_id"));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			connection.closeConnection();
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connectionMysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/test");
			Statement statement2 = connectionMysql.createStatement();
			ResultSet resultSet2 = statement2.executeQuery("select * from student");
			
			while(resultSet2.next()){
				String name = resultSet2.getString("name");
				int age = resultSet2.getInt("age");
				
				String line = String.format("Name %s, Age %d", name,age);
				System.out.println(line);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
