/**
 * @Author Ding Li
 * @Date Oct. 2016
 * Obtain all users from the data set, and allocate each user a user id.
 */

import java.io.*;
import java.util.*;


public class ReadAllUsers {

//    public static void main(String[] args){
//        HashMap<String, Integer> userMap = new HashMap<>();
//        String dirPath = "D:\\Dataset\\Review_Texts";
//        ArrayList<String> fileNames = new ArrayList<>();
//        ReadFileNames.getFile(dirPath,fileNames);
//        read(userMap,fileNames);
//        Iterator iter = userMap.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            String userName = (String)entry.getKey();
//            Integer userId = (Integer)entry.getValue();
//            System.out.println(userName + ": " + userId.intValue());
//        }
//
//    }
    public static void read(HashMap<String, Integer> map, ArrayList<String> fileNames){
        int userId = 0;
        String userName = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        //FileWriter fw = null
        for(int i=0;i<100;i++){
            try {
                String str = "";
                fis = new FileInputStream(fileNames.get(i));
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                while ((str = br.readLine()) != null) {
                    if(str.startsWith("<Author>")){
                        userName = str.substring(8);
                        if(map.get(userName)==null){
                            map.put(userName,new Integer(userId));
                            userId++;
                        }else{
                            continue;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    isr.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
