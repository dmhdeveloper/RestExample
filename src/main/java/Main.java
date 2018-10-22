import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\Users\\someone\\Desktop";
        System.out.println(formatFilePath(path));
        path = "/Users/someone//Desktop";
        System.out.println(formatFilePath(path));
    }

    public static String formatFilePath(String filePath){
        if(filePath == null){
            return "";
        }
        String regex = "(\\\\)|(\\/+\\/)";
        StringBuffer buffer = new StringBuffer();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(filePath);

        while(m.find()){
            m.appendReplacement(buffer, "/");
        }
        m.appendTail(buffer);
        return buffer.toString();
    }
}
