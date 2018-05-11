package Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import Project.WordEntity;

public class WordCont {

    public void displayWordCount(String fileName){



            Hashtable<String,Integer> tm = new Hashtable<>();

            String line = fileName.toLowerCase();
            String str[] = line.split("[\\W0-9]+");//\s[*]*[>> =]*[→]*[•]*[,]*[.]*[;]*[:]*[(]*[)]*[/]*[-]*[©]*[®]*
            for(int i = 0; i<str.length; i++){
                String word = str[i].trim();
                if(tm.containsKey(word)){
                        tm.put(word, tm.get(word)+1);
                }else{
                        tm.put(word, 1);
                }
            }

            System.out.println("The output is by dict order：");
            Iterator iterator=tm.entrySet().iterator();
            while(iterator.hasNext())
            {
                System.out.println(iterator.next());
            }

    }

    public void displayFrequencyWord(String fileName){


            StringBuffer sb = new StringBuffer();

            sb.append(fileName);


            Hashtable<String,Integer> ht = new Hashtable<>();
            StringTokenizer st = new StringTokenizer(sb.toString(),"- \n");
            while (st.hasMoreTokens()) { //Tests if there are more tokens available from this tokenizer's string.
                String letter = st.nextToken().trim();//Returns the next token from this string tokenizer.
                int count;
                if (!ht.containsKey(letter)) {
                    count = 1;
                } else {
                    count = ht.get(letter).intValue() + 1;
                }
                ht.put(letter,count);
            }

            Set<WordEntity> set = new TreeSet<WordEntity>();
            for (String key : ht.keySet()) {
                set.add(new WordEntity(key,ht.get(key)));
            }

            System.out.print("The highest occurrence word is： ");
            Iterator<WordEntity> it1 = set.iterator();
            int count=it1.next().getCount();
            for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
                WordEntity w = it.next();
                if (w.getCount()==count) {
                    //break;
                    System.out.println(w.getKey());
                    System.out.println(" The occurrence time is： " + w.getCount());
                }
            }

    }
    public static void main(String[] args) throws IOException {
        String doc = Jsoup.connect("https://www.w3.org/blog/data/").get().html();
        String clean = Jsoup.clean(doc, "https://www.w3.org/blog/data/", new Whitelist());

        WordCont wc = new WordCont();
        wc.displayWordCount(clean);
        wc.displayFrequencyWord(clean);
    }
}