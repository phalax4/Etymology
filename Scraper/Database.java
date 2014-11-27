package Scraper;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
//creates the database
//need to add checker to see if database is created already
public static void main(String args[]){
	//createDB("test");
	//createDB("Helloworld");
}
	public static void createDB(String dbName){
		Connection c = null;
		try{
			Class.forName("org.sqlite.JDBC");
			StringBuilder st = new StringBuilder("jdbc:sqlite:"+dbName+".db");
			System.out.println("Created:" +st);
			c = DriverManager.getConnection(st.toString());
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": "+e.getMessage());
		}
		System.out.println("Opened Database: Database.java");
	}
	
}
