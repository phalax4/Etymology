package Scraper;

import java.util.HashMap;


public interface Scrape {
	public HashMap createPageMap();
	public void loadDatabase(Linked<String> list);//call another class to do the printing to txt file
	

}
