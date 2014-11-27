package Scraper;

import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class EtyOnline implements Scrape {
	private static String dbName = "Ety";
	public EtyOnline(){
		//do doc and body and url initialization here, do database and table creation here
		Database.createDB(dbName);
		StringBuilder url = new StringBuilder("http://www.etymonline.com/index.php?l=a&p=0&allowed_in_frame=0");
		Document doc = null;
		try {
			doc = Jsoup.connect(url.toString()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Element body = doc.getElementById("dictionary");
		
	}
	@Override
	public HashMap createPageMap() {//create from database
		// TODO Auto-generated method stub
		return null;
	}

	public static void loadDatabase(Linked<String> list,String tableName) {
		while(list.getSize()!=0){
				//System.out.println(list.pop());
				String definition = list.pop();
				String word = list.pop();
				System.out.println("Word: "+word + " Definition: " + definition);
				String sql = "INSERT INTO "+tableName+" (WORD,ORIGIN) "+"VALUES ('"+word+"', '"+definition+"');";
				System.out.println(sql);
				//if(PopulateDB.insert(sql, dbName)==-1){
					PopulateDB.insert(sql, dbName);
				//}
				
		}
		//String sql = "INSERT INTO tableName (WORD,ORIGIN)"+"VALUES(word,origin);";

	}

	public static void main(String[] args) throws IOException {
		//StringBuilder url = new StringBuilder("http://www.etymonline.com/index.php?l=a&p=60&allowed_in_frame=0");
		StringBuilder url = new StringBuilder("http://www.etymonline.com/index.php?l=a&p=0&allowed_in_frame=0");
		Document doc = Jsoup.connect(url.toString()).get();
		//Element body = doc.getElementById("dictionary");
		Database.createDB(dbName);
		
		//for(int i = 97;i<=122;i++){
		//EtyOnline ee = new EtyOnline();
		
		//int i = 97;
		//while(i!=122){
		
			//ee.runner(i);
			//EtyOnline e2 = new EtyOnline();

			//e2.runner(i+1);

		//i++;
		//}
		
		for(int i = 116;i<=122;i++){
					//TableDB.createTable("CREATE TABLE " + String.valueOf((char)i).toUpperCase() +" (WORD text, "+"ORIGIN text);",dbName);

			//TableDB.createTable("CREATE TABLE TEST (WORD TEXT PRIMARY KEY  NOT NULL, "+"ORIGIN TEXT NOT NULL)",dbName);
			//PopulateDB.insert("INSERT INTO TEST (WORD,ORIGIN) "+"VALUES ('test','testhere');", dbName);
			
			runner(i);
			//byLetter((char)i);
		}
		
		/*
		ArrayList<String> range = getWordRange(url.toString(),new ArrayList<String>(),doc,body);
		int counter = 0;
		for(String i:range){
			System.out.println(counter+" "+i);
			counter++;
		}
		*/
		
	}
	private static void runner(int i){
		TableDB.createTable("CREATE TABLE " + String.valueOf((char)i).toUpperCase() +" (WORD text, "+"ORIGIN text);",dbName);
		try {
			byLetter((char)i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	private static void byLetter(char letter) throws IOException{
		
		Document doc;
		Element body;
		int incre = 0;

		String first = "http://www.etymonline.com/index.php?l=" + letter+"&p=";
		String second = "&allowed_in_frame=0";
		String url = first + incre + second;
		
		 doc = Jsoup.connect(url.toString()).get();
		 body = doc.getElementById("dictionary");
		
		System.out.println(url);
		while(check(url.toString(),doc,body)==true){
			url = first + incre + second;
			
			//linked list and global hashmap? so that load database is in main. Check running loops times
			
			Linked<String> listStr = getWordRange(url,new Linked<String>() ,doc,body);//get words and meanings in the particular letter
			loadDatabase(listStr,(letter+"").toUpperCase());
			
			System.out.println(url);
			doc = Jsoup.connect(url.toString()).get();
			body = doc.getElementById("dictionary");
			 
			incre++;
		}
		
	}
	

	private static boolean check(String url,Document doc,Element body){
		if(body.text().equals("No matching terms found.")){
			return false;
		}
		return true;
	}
	
	private static Linked<String> getWordRange(String url,Linked<String> array,Document doc,Element body) throws IOException{
		//StringBuilder url = new StringBuilder("http://www.etymonline.com/index.php?l=a&p=0&allowed_in_frame=0");

		
		//String url = "http://dictionary.reference.com/etymology/list/a";
		System.out.println("Fetching url...");
		//Document doc = Jsoup.connect(url).get();
		//doc = Jsoup.connect(url).get();

		int counter = 0;
		//Element body = doc.getElementById("dictionary");
		Elements highlights = body.getElementsByClass("highlight");
		//Elements content = list.select("a[href]");
		for(Element links:highlights){
			//System.out.println(links.attr("abs:href"));
			array.push(links.text());//links.attr("abs:href"));
			counter++;
		}
		return array;
		//System.out.println(counter);
	}

}
