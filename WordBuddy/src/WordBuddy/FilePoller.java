package WordBuddy;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.Scanner;

public class FilePoller {
		
	public static String entry;	
	public static ArrayList<String> synonyms = new ArrayList<String>();
	
	public FilePoller(String entry)
	{
		try {
			String url = "http://www.thesaurus.com/browse/" + entry;
			String htmlFile = FilePoller.parseFile(url);
			findSynonyms(htmlFile);
			//System.out.println(getSynonyms());	
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}


	// stores parsed file  
	
	public static String parseFile (String url) throws IOException {
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36")
				.referrer("google.com")
				.ignoreHttpErrors(true)
				.timeout(1000*3) 
				.get();            
		String htmlString = doc.toString();
		return htmlString;
	} 
	
	public static ArrayList<String> getSynonyms()
	{
		return synonyms;
	}

	// Searched parsed file for synonyms
	
	public static void findSynonyms (String html) {
		String word = null;
		int firstquote = 0, k = 0;
		int secondquote = 0, beginningindex = 0;
		String url;
		String precedent = ("<a href=\"http://www.thesaurus.com/browse/");
		int i = html.indexOf("<div id=\"synonyms-0\" class=\"synonyms\">");
		while(i >= 0 && k <= 5) {
		     i = html.indexOf(precedent, i+1);
		     firstquote = html.indexOf("\"",i+1);
		     secondquote = html.indexOf("\"", firstquote + 1);
		     url = html.substring(firstquote, secondquote);
		     beginningindex = url.indexOf("browse/") + 7;
		     word = url.substring(beginningindex, url.length());
		     //System.out.println("The word is: " + word);
		     synonyms.add(word);
			 k++;
			}
	}
							
	// main method
	
	public static void main (String [] args) {
		String entry = "taciturn";
		
		FilePoller poller = new FilePoller(entry);
				
	}
}
