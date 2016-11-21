/**
 * @Author Ding Li
 * @Date Oct. 2016
 * Obtain all the file names in an directory.
 */

import java.io.*;
import java.util.*;

public class ReadFileNames {

//    public static void main(String[] args) {
//        // The path of data set directory
//        String dirPath = "D:\\Dataset\\Review_Texts";
//        ArrayList<String> fileNames = new ArrayList<>();
//        getFile(dirPath,fileNames);
//        for(int i=0;i<fileNames.size();i++){
//            System.out.println(fileNames.get(i));
//        }
//    }

    public static void getFile(String path, ArrayList<String> fileNames){
        File file = new File(path);
        File[] files = file.listFiles();
        for(int i=0;i<files.length;i++){
            fileNames.add(files[i].getPath());
        }
    }
}