package Scraper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulateDB {
	//insert records into the tableName
	
	//String sql = "INSERT INTO tableName (WORD,ORIGIN)"+"VALUES(word,origin);";
	
	
	public static void insert(String sql,String dbName, String word, String origin){
		Connection c = null;
		Statement st = null;
		try {
			Class.forName("org.sqlite.JDBC");
			//c = DriverManager.getConnection("jdbcsqlite:test.db");//dbName goes here
			c = DriverManager.getConnection("jdbcsqlite:"+dbName+".db");
			c.setAutoCommit(false);
			System.out.println("DB opened");
			
			st = c.createStatement();
			
			st.executeUpdate(sql);
			st.close();
			c.commit();
			c.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getClass().getName()+":" + e.getMessage());
			e.printStackTrace();
		}
		//System.out.println("Insertion Complete");
	}
	
	public static void main(String[] args) {
		
	}
}
