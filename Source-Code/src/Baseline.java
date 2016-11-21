/**
 * @Author Ding Li
 * @Date Oct. 2016
 * Create the input data file for baseline method.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Baseline {
    public static void main(String[] args){
        String dirPath = "D:\\Dataset\\Review_Texts";
        String resultPath = "D:\\Dataset\\parsed\\baseline.txt";
        HashMap<String, Integer> userMap = new HashMap<>();
        ArrayList<String> fileNames = new ArrayList<>();
        ReadFileNames.getFile(dirPath,fileNames);
        ReadAllUsers.read(userMap,fileNames);
        ExtractRatings.extract(fileNames,userMap,resultPath);
    }
}
