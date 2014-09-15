package Scraper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableDB {
//create a table in the database via sql statement
	public static void main(String[] args) {
		//sql stmt to input //   createTable("CREATE TABLE dbName"+"(WORD TEXT PRIMARY KEY NOT NULL,"+"ORIGIN TEXT NOT NULL)");
		//if no origin and valid word make it have a key of word and value unknown not null.
	}
	public static void createTable(String sql,String dbName){
		Connection c = null;
		Statement st = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
			System.out.println("Opened db");
			
			st = c.createStatement();
			st.executeUpdate(sql);
			st.close();
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		System.out.println("Table created");
	}
	
}
