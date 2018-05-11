package Project;

import java.io.*;
import org.jsoup.*;
import org.jsoup.safety.Whitelist;



import java.io.*;
import org.jsoup.*;
import org.jsoup.safety.Whitelist;
//import final_project.KMP;

public class SpellChecker {
	private static String[] list = null;
	public static AVLTree<String> t = new AVLTree<>( );
	private static File fi = new File("C:\\Users\\lenovo\\Downloads\\Advanced computing concepts\\W3C Web Pages");


	public static void jsoup(String filename) throws IOException {

		File file = new File("C:\\Users\\lenovo\\Downloads\\Advanced computing concepts\\W3C Web Pages\\" + filename);

		String doc = Jsoup.parse(file,"UTF-8","http://www.w3c.net/").html();
		String clean = Jsoup.clean(doc, "http://www.w3c.net/", new Whitelist());

		list = clean.split("[\\W0-9]+");
	}

	public static void directoryOP(String s) throws IOException {

		if(t.contains(s)) {
			System.out.println(s+" found.");
		}
		else {
			t.insert(s);
			t.checkBalance();
			System.out.println("Didn't find "+s +"," + s+" has been inserted in directory.");
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] files = fi.list();

		for(String input : files) {
			jsoup(input);
			for(String obj : list) {
				directoryOP(obj);
			}
		}
		t.printTree();
	}

}
