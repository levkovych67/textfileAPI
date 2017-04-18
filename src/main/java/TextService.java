import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TextService {

//I did not understand the task properly because the task says "Such call should return all strings that contains ‘Java'".What is string? line? paragraph ?
// in example it returns every paragraph that contains 'Java'. Well i wrote two methods that gets every line and paragraph, which contains 'word'.


    private static String path = "/Tomcat 8.0/webapps/textfileAPI/WEB-INF/testfile.txt";

    public ResponseObject scanLinesForQuery(String q, Integer length, Integer limit) throws IOException {

        List<StringBuilder> list = new LinkedList<>();
        Files.lines(Paths.get(path)).filter(line -> line.contains(q))
                .map(string -> string.length() > length ? string.substring(0, length) : string)
                .forEach(s -> list.add(new StringBuilder(s)));

        List<StringBuilder> responseList = limitTrimmer(list, limit);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setText(responseList);
        return responseObject;
    }

    public ResponseObject scanParagraphsForQuery(String q, Integer length, Integer limit) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(path));
        String line = bf.readLine();
        List<StringBuilder> paragraphs = new LinkedList<>();
        StringBuilder string = new StringBuilder();
        while (line != null) {
            if (line.isEmpty()) {
                paragraphs.add(string);
                string = new StringBuilder("");
                line = bf.readLine();
            }
            if (!line.isEmpty()) {
                string.append(line);
            }
            line = bf.readLine();
            if (line == null) {
                paragraphs.add(string);
            }
        }
        List<StringBuilder> list = new LinkedList<>();
        paragraphs.stream().filter(paragraph -> new String(paragraph).contains(q))
                .map(paragraph -> paragraph.length() > length ? paragraph.substring(0, length) : paragraph)
                .forEach(paragraph -> list.add(new StringBuilder(paragraph)));
        List<StringBuilder> responseList = limitTrimmer(list, limit);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setText(responseList);
        return responseObject;


    }

    private List<StringBuilder> limitTrimmer(List<StringBuilder> list, Integer limit) {
        Integer charsCount = 0;
        LinkedList<StringBuilder> trimmedList = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            charsCount = charsCount + list.get(i).length();
            if (charsCount > limit) {
                StringBuilder string = new StringBuilder(list.get(i).substring(0, list.get(i).length() - (charsCount - limit)));
                if (string.length() > 0) {
                    trimmedList.add(string);
                }
                return trimmedList;
            }
            trimmedList.add(list.get(i));
        }
        return trimmedList;
    }


}




