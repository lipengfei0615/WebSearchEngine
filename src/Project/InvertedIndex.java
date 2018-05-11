package Project;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.swing.*;

public class InvertedIndex {
	private static Map<String, Map<String, Integer>> wordToDocumentMap = new TreeMap<>();
	private static File fi=new File("C:\\Users\\lenovo\\Downloads\\Advanced computing concepts\\W3C Web Pages");
	
	public static String[] jsoup(String filename) throws IOException {
		File file = new File("C:\\Users\\lenovo\\Downloads\\Advanced computing concepts\\W3C Web Pages\\" + filename);
		
		String doc = Jsoup.parse(file,"UTF-8","http://www.w3c.net/").html();
		String clean = Jsoup.clean(doc, "http://www.w3c.net/", new Whitelist());
		 
		String[] list = clean.split("[\\W0-9]+");
		
		return list;
	}
	
	public static void input(String file) throws IOException {
		Map<String, Integer> documentToCountMap = new TreeMap<>();
		// create inverted index of all files
		 String[] wl = jsoup(file);
		 for (String word : wl) {
			if(wordToDocumentMap == null) {
        			documentToCountMap.put(file, 1);
 	 				wordToDocumentMap.put(word, documentToCountMap);
			}
			else if(wordToDocumentMap.containsKey(word)) {
				documentToCountMap = wordToDocumentMap.get(word);
				if(documentToCountMap.get(file) == null) {
        				documentToCountMap.put(file, 1);
				}
				else {
					documentToCountMap.put(file, documentToCountMap.get(file) + 1);
				}
			}
			else {
        			documentToCountMap.put(file, 1);
 	 			wordToDocumentMap.put(word, documentToCountMap);
			}
		}
	}

	
	public static void output() throws IOException {
        
		for(Map.Entry<String, Map<String,Integer>> wordToDocument : wordToDocumentMap.entrySet()) {
			String currentWord = wordToDocument.getKey();
			Map<String, Integer> documentToWordCount = wordToDocument.getValue();
			for(Map.Entry<String, Integer> documentToFrequency : documentToWordCount.entrySet()) {
				String document = documentToFrequency.getKey() ;
				int wordCount = documentToFrequency.getValue();

				System.out.println("Word " + currentWord + " found " + wordCount + " times in document "+document);

			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] files = fi.list();
		
		for(int i = 1; i <  files.length; i++) {
			input(files[i]);
		}
		output();
	}

}
