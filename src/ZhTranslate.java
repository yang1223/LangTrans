import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 其昌 on 2016/2/27.
 */
public class ZhTranslate {
    public static void main(String[] args){

        File langs = new File("langs","language_zh_tw.csv");

        List<String> simple2traditional = readLine(langs,"UTF-8");
        Map<String,String> map = new HashMap<>();
        String whitespace = "_space_";
        String leftBracket = "_leftbracket_";
        String rightBracket = "_rightbracket_";

        for(String line:simple2traditional){
            String[] strs = line.split(",");
            strs[0] = strs[0].replaceAll(" ",whitespace);
            strs[0] = strs[0].replaceAll("\\(",leftBracket);
            strs[0] = strs[0].replaceAll("\\)",rightBracket);


            strs[1] = strs[1].replaceAll(" ",whitespace);
            strs[1] = strs[1].replaceAll("\\(",leftBracket);
            strs[1] = strs[1].replaceAll("\\)",rightBracket);


            map.put(strs[0],strs[1]);
        }


        File file = new File("zh");
        String[] list = file.list();

        for(String filename:list) {
            if (filename.endsWith(".json")) {
                File json = new File("zh",filename);
                String content = readAllWithReturn(json,"UTF-8");

                content = content.replaceAll(" ",whitespace);
                content = content.replaceAll("\\(",leftBracket);
                content = content.replaceAll("\\)",rightBracket);

                for(Map.Entry entry:map.entrySet()){
                    String key = entry.getKey().toString();
                    String value = entry.getValue().toString();
                    content = content.replace(key,value);
                }

                content = content.replaceAll(whitespace," ");
                content = content.replaceAll(leftBracket,"(");
                content = content.replaceAll(rightBracket,")");


                File traditionalChineseJson = new File("langs",filename);
                writeAll(traditionalChineseJson , content , "UTF-8");
            }
        }
    }

    public static void writeAll(File file , String content , String charsetName) {
        try{
            FileOutputStream fout = new FileOutputStream(file);
            OutputStreamWriter outWriter = new OutputStreamWriter(fout , charsetName);
            Writer writer = new BufferedWriter(outWriter);
            writer.write(content);
            writer.close();
            outWriter.close();
            fout.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String readAllWithReturn(File file , String charsetName) {

        String content = "";

        try {
            FileInputStream fin = new FileInputStream(file);
            InputStreamReader isReader = new InputStreamReader(fin , charsetName);
            BufferedReader br = new BufferedReader(isReader);
            while(br.ready()){
                content += br.readLine() + System.getProperty("line.separator");
            }
            br.close();
            isReader.close();
            fin.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return content;
    }

    public static List<String> readLine(File file , String charsetName) {

        List<String> content = new ArrayList<>();

        try {
            FileInputStream fin = new FileInputStream(file);
            InputStreamReader isReader = new InputStreamReader(fin , charsetName);
            BufferedReader br = new BufferedReader(isReader);
            while(br.ready()){
                content.add(br.readLine());
            }
            br.close();
            isReader.close();
            fin.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return content;
    }
}
