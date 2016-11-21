/**
 * @Author Ding Li
 * @Date Oct. 2016
 * Extract overall ratings to form the input data matrix for baseline method
 */

import java.io.*;
import java.util.*;

public class ExtractRatings {

    public static void extract(ArrayList<String> fileNames, HashMap<String,Integer> userMap, String resultPath){
        String userName;
        String overall;
        int userId;
        int hotelId;
        final int NUM_OF_USERS = userMap.size();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(resultPath);
            for(int i=0;i<100;i++){
                String str;
                fis = new FileInputStream(fileNames.get(i));
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                while ((str = br.readLine()) != null) {
                    if(str.startsWith("<Author>")){
                        userName = str.substring(8);
                        userId = userMap.get(userName).intValue();
                        hotelId = i;
                        do{
                            str = br.readLine();
                        }while(!str.startsWith("<Overall>"));
                        overall = str.substring(9);
                        fw.write(overall);
                        fw.write(" ");
                        fw.write(Integer.toString(userId) + ":1");
                        fw.write(" ");
                        fw.write(Integer.toString(hotelId + NUM_OF_USERS) + ":1");
                        fw.write("\n");
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
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
