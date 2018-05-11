package Project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.*;
import java.util.*;


class PriorityQueue1
{
    private final String filename;
    private final int score;


    PriorityQueue1(String filename, int score)
    {
        this.filename = filename;
        this.score = score;
    }
    public int getScore(){
        return score;
    }

    @Override
    public String toString()
    {
        return "<File Name> : "+ filename + "      <Score> : "+ score;
    }
}

class SortQueueViaPriority1 implements Comparator<PriorityQueue1> {
    @Override
    public int compare(PriorityQueue1 p1, PriorityQueue1 p2) {

        return Integer.compare(p1.getScore(), p2.getScore());
    }
}
public class EditDistance {
    public static int editDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        return dp[len1][len2];
    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (int i=0;i<10;i++) {
            Map.Entry entry = (Map.Entry) list.get(i);
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }



    public void calculateEditDistance(File [] listOfFiles, String[] strArr) {
        try {

            PriorityQueue<PriorityQueue1> pq = new PriorityQueue<PriorityQueue1>(101, new SortQueueViaPriority1());
            for (File file : listOfFiles) {
                HashMap<String, Integer> map = new HashMap<>();
                Document doc = Jsoup.parse(file,"UTF-8","http://www.w3c.net/");
                String text = doc.body().text();
                String clean = Jsoup.clean(text, "http://www.w3.org", new Whitelist());
                int d = 0;
                int score = 0;

                    String line = clean.toLowerCase();
                    String str[] = line.split("[\\W0-9]+");

                    for (int i = 0; i < strArr.length; i++) {
                        int value = 0;
                        for (int j = 0; j < str.length; j++) {
                            d = editDistance(strArr[i], str[j]);
                            map.put(str[j], d);
                        }
                        Map<Integer, String> map1 = sortByValues(map);
                        Set set2 = map1.entrySet();
                        Iterator iterator2 = set2.iterator();
                        while (iterator2.hasNext()) {
                            Map.Entry me2 = (Map.Entry) iterator2.next();
                            value += (int) me2.getValue();
                            //System.out.print(me2.getKey() + ": ");
                        }
                        //System.out.println("value is " + value);
                        score += value;
                    }

                //System.out.println(file.getName() + "  Score is  " + score);

                pq.add(new PriorityQueue1(file.getName(), score));

            }
            while (!pq.isEmpty()) {
                System.out.println("----Please press Enter to continue:----");
                Scanner scanner = new Scanner(System.in);
                String readString = scanner.nextLine();
                System.out.println(readString);
                if (readString.equals("")) {
                    for (int count = 0; count < 10; count++) {
                        if(!pq.peek().equals(" ")){
                            System.out.println(pq.remove());
                        }
                        else{
                            System.out.println("End");
                        }

                    }
                }

            }

        }catch (FileNotFoundException e) {
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
        EditDistance ed=new EditDistance();
        ed.calculateEditDistance(listOfFiles,strArr);

    }


}
