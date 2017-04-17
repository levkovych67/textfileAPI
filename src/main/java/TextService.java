import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class TextService {

//I did not understand the task properly because the task says "Such call should return all strings that contains ‘Java'".What is string? line? paragraph ?
// in example it returns every paragraph that contains 'Java'. Well i wrote few methods that gets every line and paragraph, which contains 'word'.


    private static String path = "src/main/webapp/WEB-INF/testfile.txt";

    public List<StringBuilder> ScanLinesForQuery(String q, Integer length, Integer limit) throws IOException {
        List<StringBuilder> list = new LinkedList<>();
        Files.lines(Paths.get(path)).filter(line -> line.contains(q))
                .map(string -> string.length() > length ? string.substring(0, length) : string)
                .forEach(s -> list.add(new StringBuilder(s)));

        return limitTrimmer(list, limit);
    }

    public List<StringBuilder> limitTrimmer(List<StringBuilder> list, Integer limit) {
        Integer charsCount = 0;
        LinkedList<StringBuilder> trimmedList = new LinkedList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            charsCount = charsCount + list.get(i).length();
            if (charsCount > limit) {
                trimmedList.add(new StringBuilder(list.get(i).substring(0, list.get(i).length() - (charsCount - limit))));
                return trimmedList;
            }
            trimmedList.add(new StringBuilder(list.get(i)));
        }
        return trimmedList;
    }

    public static void main(String[] args) throws IOException {
        TextService textService = new TextService();
        textService.ScanLinesForQuery("Java", 5, 20).forEach(System.out::println);
    }


}




