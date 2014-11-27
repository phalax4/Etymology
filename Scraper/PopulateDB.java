package Scraper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulateDB {
	//insert records into the tableName
	
	//String sql = "INSERT INTO tableName (WORD,ORIGIN)"+"VALUES(word,origin);";
	
	
	public static void insert(String sql,String dbName){//, String word, String origin){
	
		Connection c = null;
		
		Statement st = null;
		try {
			Class.forName("org.sqlite.JDBC");
			//c = DriverManager.getConnection("jdbc:sqlite:test.db");//dbName goes here
			//System.out.println(dbName);
			
			try{
				c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
			}catch(NullPointerException e){
				System.out.println("\n"+"error\n");
				//c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
			}

			c.setAutoCommit(false);
			System.out.println("DB opened: PopulateDB.java");
			//try{
			System.gc();
			st = c.createStatement();
			//}catch(NullPointerException e2){
				
			//}
			//System.out.println(sql);
				
			st.executeUpdate(sql);
			System.gc();
			c.commit();
			c.close();
			System.gc();
			st.close();
			System.gc();


			//return 0;
		} catch (SQLException | ClassNotFoundException e) {
			try {
				c.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.gc();
			System.err.println(e.getClass().getName()+":" + e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				System.gc();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		//System.out.println("Insertion Complete");
	}
	
	public static void main(String[] args) {
		
	}
}
