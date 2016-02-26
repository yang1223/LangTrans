import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 其昌 on 2016/2/26.
 */
public class LangTrans {
    public static void main(String[] args){

        File file = new File("zh");
        String[] list = file.list();

        try {
            for(String filename:list){
                File json = new File("zh" , filename);
                List<String> content = readLine(json,"UTF-8");
                List<String> outputContent = new ArrayList<>();

                Pattern pattern = Pattern.compile("\"[\\w\\-].*?\"\\s*?:\\s*?\"(.*?)\",");
                Matcher matcher;
                for(String str:content){
                    matcher = pattern.matcher(str+",");
                    if(matcher.find())
                        outputContent.add(matcher.group(1));
                }

                File outputFile = new File("zh",filename.split("\\.")[0]+".csv");
                writeLine(outputFile , outputContent , "UTF-8");
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static List<String> readLine(File file , String charsetName) throws IOException {
        List<String> content = new ArrayList<>();
        FileInputStream fin = new FileInputStream(file);
        InputStreamReader isReader = new InputStreamReader(fin , charsetName);
        BufferedReader br = new BufferedReader(isReader);
        while(br.ready())
            content.add(br.readLine());
        br.close();
        isReader.close();
        fin.close();
        return content;
    }

    public static void writeLine(File file , List<String> content , String charsetName) throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        OutputStreamWriter outWriter = new OutputStreamWriter(fout , charsetName);
        Writer writer = new BufferedWriter(outWriter);
        for(String line:content){
            writer.write(line+System.getProperty("line.separator"));
        }
        writer.close();
        outWriter.close();
        fout.close();
    }

}
