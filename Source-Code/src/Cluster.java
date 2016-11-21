/**
 * @Author Ding Li
 * @Date Nov. 2016
 * Write
 */

import java.util.*;
import java.io.*;

public class Cluster {
    public static void arff(String stylePath, String userPath, HashMap<String, StyleVector> map){
        FileWriter fw1 = null;
        FileWriter fw2 = null;
        try{
            fw1 = new FileWriter(stylePath);
            fw2 = new FileWriter(userPath);
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String userName = (String)entry.getKey();
                StyleVector style = (StyleVector) entry.getValue();
                fw2.write(userName + "\n");
                fw1.write(style.toString());
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                fw1.close();
                fw2.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
