import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by cavitation on 2016/3/2.
 */
public class ZhTranslate {
    public static void main(String[] args){

        final String[] jsonFiles = {"error.json" , "menu.json" , "pad.json" , "preference.json"};
        final String[] _jsonFiles = {"_error.json" , "_menu.json" , "_pad.json" , "_preference.json"};

        try{
            String haroopadPath = getAppDataPath() + "\\Haroo Studio\\Haroopad";

            File haroopadDir = new File(haroopadPath);
            if(haroopadDir.isDirectory()){
                String zhPath = haroopadPath+"\\Libraries\\.locales\\zh";
                File zh = new File(zhPath);
                if(zh.isDirectory()){
                    for(int i = 0 ; i < jsonFiles.length ; i++){
                        // rename the original json files
                        File file1 = new File(zh.getPath(),jsonFiles[i]);
                        File file2 = new File(zh.getPath(),_jsonFiles[i]);
                        if(file2.exists())
                            file1.delete();
                        else
                            file1.renameTo(file2);

                        File source = new File("zh-tw",jsonFiles[i]);
                        File target = new File(zh.getPath(),jsonFiles[i]);
                        Files.copy(source.toPath(),target.toPath());

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static String getAppDataPath(){
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("win")){
            String appData = System.getenv("APPDATA");
            if(appData!=null) return appData;
            else return System.getProperty("user.home")+"\\AppData\\Roaming";
        } else if(OS.contains("mac")){
            return System.getProperty("user.home") + "\\Library\\Application Support";
        }
        return System.getProperty("user.home");
    }



}
