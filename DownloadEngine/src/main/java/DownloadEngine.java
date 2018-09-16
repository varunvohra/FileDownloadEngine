import com.agodaservices.helpers.DownloadHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// main class takes minimum 2 args : comma-separated list of URLs and final directory location
public class DownloadEngine {

    public static void main(String[] args) {

        System.out.println("Starting File Downloading Engine !!");

        ArrayList<String> fileUrls = new ArrayList<String>();
        String saveDir;
        Map<String, String> argsMap = new HashMap<String, String>();

        if(args.length >= 2){

            // get all key-value argument pairs in a map
            // url=dcdc.com , savedir=qsd/qsd
            for(String arg : args) {
                String[] argSplit = arg.split("=");
                argsMap.put(argSplit[0], argSplit[1]);
            }

            String[] urlStringArray =  argsMap.get("url").split(",");
            for(String url : urlStringArray){
                fileUrls.add(url);
            }
            if(urlStringArray.length == 0) {
                System.out.println("Please provide some urls to download files");
                return;
            }
            saveDir = argsMap.get("saveDir");
        }else {
            throw new RuntimeException("Insufficient parameters");
        }

        DownloadHelper.downloadFilesInParallel(fileUrls, saveDir);
    }

}
