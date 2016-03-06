import java.io.File;
import java.nio.file.Files;

/**
 * Created by Yang on 2016/3/6.
 */
public class ZhTranslateRedo {
    public static void main(String[] args){
        final String[] jsonFiles = {"error.json" , "menu.json" , "pad.json" , "preference.json"};
        final String[] _jsonFiles = {"_error.json" , "_menu.json" , "_pad.json" , "_preference.json"};

        try{
            String haroopadPath = getHaroopadPath(args);

            File haroopadDir = new File(haroopadPath);
            if(haroopadDir.isDirectory()){
                String zhPath = haroopadPath+"\\Libraries\\.locales\\zh";
                File zh = new File(zhPath);
                if(zh.isDirectory()){
                    for(int i = 0 ; i < jsonFiles.length ; i++){
                        File file1 = new File(zh.getPath(),_jsonFiles[i]);
                        File file2 = new File(zh.getPath(),jsonFiles[i]);
                        file2.delete();
                        file1.renameTo(file2);
                    }
                    System.out.println("Redo!");
                } else {
                    throw new HaroopadNotFoundException("Cannot find language folder! Please check the path!");
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
            if(!file.exists()) throw new HaroopadNotFoundException("Cannot find the input path!");
            if(!new File(path,"haroopad.exe").exists())
                throw new HaroopadNotFoundException("Cannot find \"haroopad.exe\" in your input path!");
            return file.getPath();
        } else {
            String haroopadPath = getAppDataPath() + "\\Haroo Studio\\Haroopad";
            File haroopadDir = new File(haroopadPath);
            if(!haroopadDir.exists()) throw new HaroopadNotFoundException("Cannot find the input path!");
            if(!new File(haroopadPath,"haroopad.exe").exists())
                throw new HaroopadNotFoundException("Cannot find \"haroopad.exe\" in your input path!");
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
