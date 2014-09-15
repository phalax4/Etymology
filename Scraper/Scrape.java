package Scraper;

import java.util.HashMap;


public interface Scrape {
	public HashMap scrapePage();
	public void loadDatabase(HashMap<String,String> hash);//call another class to do the printing to txt file
	

}
