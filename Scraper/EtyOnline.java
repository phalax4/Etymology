package Scraper;

import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class EtyOnline implements Scrape {
	
	public EtyOnline(){
		//do doc and body and url initialization here
	}
	@Override
	public HashMap scrapePage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadDatabase(HashMap<String, String> hash) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws IOException {
		StringBuilder url = new StringBuilder("http://www.etymonline.com/index.php?l=a&p=60&allowed_in_frame=0");
		Document doc = Jsoup.connect(url.toString()).get();
		Element body = doc.getElementById("dictionary");
		
		int incre = 1;
		while(check(url.toString(),doc,body)==true){
			url = url.replace(42,43,String.valueOf(incre));
			 doc = Jsoup.connect(url.toString()).get();
			 body = doc.getElementById("dictionary");
			incre++;
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
	private static boolean check(String url,Document doc,Element body){
		if(body.text().equals("No matching terms found.")){
			return false;
		}
		return true;
	}
	
	private static ArrayList<String> getWordRange(String url,ArrayList<String> array,Document doc,Element body) throws IOException{
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
			array.add(links.text());//links.attr("abs:href"));
			counter++;
		}
		return array;
		//System.out.println(counter);
	}

}
