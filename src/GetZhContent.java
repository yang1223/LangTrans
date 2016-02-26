import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 其昌 on 2016/2/26.
 */
public class GetZhContent {
    public static void main(String[] args){

        File file = new File("zh");
        String[] list = file.list();

        List<String> zhContent = new ArrayList<>();

        for(String filename:list){
            if(filename.endsWith(".json")){
                File json = new File("zh" , filename);
                List<String> content = readLine(json,"UTF-8");

                Pattern pattern = Pattern.compile("\"[\\w\\-].*?\"\\s*?:\\s*?\"(.+?)\",");
                Matcher matcher;
                for(String str:content){
                    matcher = pattern.matcher(str+",");
                    if(matcher.find())
                        zhContent.add(matcher.group(1));
                }
            }
        }
        File outputFile = new File("langs","language.csv");
        writeLine(outputFile , zhContent , "UTF-8");
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

    public static void writeLine(File file , List<String> content , String charsetName) {
        try{
            FileOutputStream fout = new FileOutputStream(file);
            OutputStreamWriter outWriter = new OutputStreamWriter(fout , charsetName);
            Writer writer = new BufferedWriter(outWriter);
            for(String line:content){
                writer.write(line+System.getProperty("line.separator"));
            }
            writer.close();
            outWriter.close();
            fout.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
