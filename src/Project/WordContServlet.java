package Project;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@WebServlet(name = "WordContServlet")
public class WordContServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String str = request.getParameter("inputbox");
        // out.println(str);
        String[] strArr = str.split("[\\W0-9]+");
        //out.println(strArr.length);
        File folder = new File("C:\\Users\\lenovo\\Downloads\\Advanced computing concepts\\Text");
        File[] listOfFiles = folder.listFiles();
        //out.println(listOfFiles.length);
        out.println("<html>");
        out.println("<body style=\"background-color:#D4E6F1\">");
        Map<String, Map<String, Integer>> wordToDocumentMap = new TreeMap<>();
        Map<String, Integer> documentToCountMap = new TreeMap<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            // create inverted index of all files
            File file = new File(listOfFiles[i].getAbsolutePath());

//            String doc = Jsoup.parse(file,"UTF-8","http://www.w3c.net/").html();
//            String clean = Jsoup.clean(doc, "http://www.w3c.net/", new Whitelist());
                BufferedReader br = new BufferedReader(new FileReader(file));
                String text = "";
                String[] list = null;

                while ((text = br.readLine()) != null) {
                    list = text.split("[\\W0-9]+");

                }

                String[] wl = list;
                for (String word : wl) {
                    if (wordToDocumentMap == null) {
                        documentToCountMap.put(listOfFiles[i].getName(), 1);
                        wordToDocumentMap.put(word, documentToCountMap);
                    } else if (wordToDocumentMap.containsKey(word)) {
                        documentToCountMap = wordToDocumentMap.get(word);
                        if (documentToCountMap.get(listOfFiles[i].getName()) == null) {
                            documentToCountMap.put(listOfFiles[i].getName(), 1);
                        } else {
                            documentToCountMap.put(listOfFiles[i].getName(), documentToCountMap.get(listOfFiles[i].getName()) + 1);
                        }
                    } else {
                        documentToCountMap.put(listOfFiles[i].getName(), 1);
                        wordToDocumentMap.put(word, documentToCountMap);
                    }
                }
        }

        for (Map.Entry<String, Map<String, Integer>> wordToDocument : wordToDocumentMap.entrySet()) {
                String currentWord = wordToDocument.getKey();
                Map<String, Integer> documentToWordCount = wordToDocument.getValue();
                for (Map.Entry<String, Integer> documentToFrequency : documentToWordCount.entrySet()) {
                    String path=folder.getParentFile()+"\\W3C Web Pages\\"+documentToFrequency.getKey();
                    Path p = Paths.get(path.replaceAll(".txt",".htm"));
                    int wordCount = documentToFrequency.getValue();
                    //out.println("<font color=\"white\">");
                    out.println("<h2><i><b>"+"Word " + currentWord + " found " + wordCount + " times in document " + "<a href=\"/static/W3CWebPages/"+path.replaceAll(".txt",".htm").substring(path.lastIndexOf('\\') + 1)+"\">"+path.replaceAll(".txt",".htm").substring(path.lastIndexOf('\\') + 1)+"</a>"+"</b></i></b><br>");
                   // out.println("</font>");
                }
        }
        out.println("</body>");
        out.println("</html>");
    }
}


