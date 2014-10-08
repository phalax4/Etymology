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
		
	}
	@Override
	public HashMap createPageMap() {//create from database
		// TODO Auto-generated method stub
		return null;
	}

	public static void loadDatabase(Linked<String> list,String tableName) {
		while(list.getSize()!=0){
			
		}
		//String sql = "INSERT INTO tableName (WORD,ORIGIN)"+"VALUES(word,origin);";

	}

	public static void main(String[] args) throws IOException {
		//StringBuilder url = new StringBuilder("http://www.etymonline.com/index.php?l=a&p=60&allowed_in_frame=0");
		StringBuilder url = new StringBuilder("http://www.etymonline.com/index.php?l=a&p=0&allowed_in_frame=0");
		Document doc = Jsoup.connect(url.toString()).get();
		Element body = doc.getElementById("dictionary");
		for(int i = 97;i<=122;i++){
			TableDB.createTable("CREATE TABLE " + String.valueOf((char)i).toUpperCase() +"(WORD TEXT PRIMARY KEY NOT NULL,"+"ORIGIN TEXT NOT NULL)",dbName);
			byLetter((char)i);
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
