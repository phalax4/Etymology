package Scraper;

import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapeDictionaryCom implements Scrape {

	public static void main(String[] args) throws IOException {
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> origin = new ArrayList<String>();

		//System.out.println((char)122);
		//String address = "http://dictionary.reference.com/etymology/list/";
		
		StringBuilder url = new StringBuilder("http://dictionary.reference.com/etymology/list/");
		for(int i = 97; i<=122;i++){
			url.append((char)i);
			//array = getWordRange(url.toString(),array);//get list of word list ranges
			
			url.deleteCharAt(47);
			array.clear();
		}
		array = getWordRange("http://dictionary.reference.com/etymology/list/a",array);//get list of word list ranges
		
		words =  getWordRange(array.get(0),words);//get word links within word range;
		
		//System.out.println("");
		//System.out.println(array.get(0));
		//System.out.println(words.get(0));
		for(String i : words){
				origin.add(i);//http://dictionary.reference.com/etymology/a
		}
		
		for(String i : origin){
			System.out.println(i);
		}


	}
	
	private static ArrayList<String> getWordRange(String url,ArrayList<String> array) throws IOException{
		//String url = "http://dictionary.reference.com/etymology/list/a";
		System.out.println("Fetching url...");
		Document doc = Jsoup.connect(url).get();
		int counter = 0;
		Elements list = doc.getElementsByClass("lsw");
		Elements content = list.select("a[href]");
		for(Element links:content){
			//System.out.println(links.attr("abs:href"));
			array.add(links.attr("abs:href"));
			counter++;
		}
		return array;
		//System.out.println(counter);
	}
	private static void getOrigin(String url) throws IOException{
		System.out.println("Fetching Origin");
		Document doc = Jsoup.connect(url).get();
		
		
	}

	@Override
	public HashMap<String,String> scrapePage() {
		return new HashMap<String, String>();
		//basically what is in the main right now
	}

	@Override
	public void loadDatabase(HashMap<String,String> hash) {//print out to text file calling another class to do so
		
	}

}
