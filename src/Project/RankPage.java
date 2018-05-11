package Project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.*;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
public class RankPage {

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Entry) (o2)).getValue())
                        .compareTo(((Entry) (o1)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (int i=0;i<10;i++) {
            Entry entry = (Entry) list.get(i);
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    private void displayWordCont(File []listOfFiles,String[]strArr) {
        try {
            HashMap<String,Integer> map = new HashMap<>();
            for (File file : listOfFiles) {
                Document doc = Jsoup.parse(file,"UTF-8","http://www.w3c.net/");
                String text = doc.body().text();
                String clean = Jsoup.clean(text, "http://www.w3.org", new Whitelist());

                    int score=0;

                        String line = clean.toLowerCase();
                        String str[] = line.split("[\\W0-9]+");//\s[*]*[>> =]*[→]*[•]*[,]*[.]*[;]*[:]*[(]*[)]*[/]*[-]*[©]*[®]*
                        for (int j = 0; j < strArr.length; j++) {
                            for (int i = 0; i < str.length; i++) {
                                String word = str[i].trim();
                                if (strArr[j].equals(word)) {
                                    score+=1;
                                }
                            }
                        }

                    map.put(file.getName(),score);

            }
            Map<Integer, String> map1 = sortByValues(map);
            System.out.println("<----- The top 10 pages with the best matches with this set of keywords is as follows: ----->");
            Set set2 = map1.entrySet();
            Iterator iterator2 = set2.iterator();
            while(iterator2.hasNext()) {
                Entry me2 = (Entry)iterator2.next();
                System.out.print(me2.getKey() + ": ");
                System.out.println(me2.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args){
        System.out.println("Please input a set of keywords:");
        Scanner in=new Scanner(System.in);
        String str=in.nextLine();
        String[]strArr=str.split("\\s+");
        File folder = new File("C:\\Users\\lenovo\\Downloads\\Advanced computing concepts\\W3C Web Pages");
        File[] listOfFiles = folder.listFiles();
        RankPage rp=new RankPage();
        rp.displayWordCont(listOfFiles,strArr);

    }


}
