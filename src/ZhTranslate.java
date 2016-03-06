import java.io.*;
import java.nio.file.Files;

/**
 * Created by cavitation on 2016/3/2.
 */
public class ZhTranslate {
    public static void main(String[] args){

        final String[] jsonFiles = {"error.json" , "menu.json" , "pad.json" , "preference.json"};
        final String[] _jsonFiles = {"_error.json" , "_menu.json" , "_pad.json" , "_preference.json"};

        try{
            String haroopadPath = getHaroopadPath(args);

            File haroopadDir = new File(haroopadPath);
            if(haroopadDir.isDirectory()){
                // find language folder
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
                        // copy the traditional chinese file to here
                        File source = new File("zh-tw",jsonFiles[i]);
                        File target = new File(zh.getPath(),jsonFiles[i]);
                        Files.copy(source.toPath(), target.toPath());
                        System.out.println("translating......" + (25 + i * 25) + "%");
                    }
                    System.out.println("Finish!");
                } else {
                    throw new HaroopadNotFoundException("Cannot find language folder in the Haroopad!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static String getHaroopadPath(String[] args) throws HaroopadNotFoundException {
        if(args.length != 0){
            String path = args[0];
            File file = new File(path);
            if(!file.exists()) throw new HaroopadNotFoundException("Your input path does not exist!");
            if(!new File(path,"haroopad.exe").exists())
                throw new HaroopadNotFoundException("Cannot find \"haroopad.exe\" in your input path!");
            return file.getPath();
        } else {
            String haroopadPath = getAppDataPath() + "\\Haroo Studio\\Haroopad";
            File haroopadDir = new File(haroopadPath);
            if(!haroopadDir.exists()) throw new HaroopadNotFoundException("Cannot find  \"haroopad.exe\" !");
            if(!new File(haroopadPath,"haroopad.exe").exists())
                throw new HaroopadNotFoundException("Cannot find \"haroopad.exe\" !");
            return haroopadDir.getPath();
        }
    }

    public static String getAppDataPath() {
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

    static class HaroopadNotFoundException extends Exception {
        HaroopadNotFoundException(String message){
            super(message);
        }
    }

}
