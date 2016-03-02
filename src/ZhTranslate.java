import java.io.File;

/**
 * Created by cavitation on 2016/3/2.
 */
public class ZhTranslate {
    public static void main(String[] args){

        String haroopadPath = getAppDataPath() + "\\Haroo Studio\\Haroopad";
        File haroopadDir = new File(haroopadPath);
        if(haroopadDir.isDirectory()){
            String zhPath = haroopadPath+"\\Libraries\\.locales\\zh";
            File zh = new File(zhPath);
            if(zh.isDirectory()){
                String[] fileList = zh.list();
                for(String filename:fileList){
                    if(filename.endsWith(".json")){
                        // rename the original json files
                        File file1 = new File(zh.getPath(),filename);
                        File file2 = new File(zh.getPath(),"old_"+filename);
                        file1.renameTo(file2);
                    }
                }
                // TODO : copy 4 .json files to here
            }
            System.out.println(zh.isDirectory());
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
