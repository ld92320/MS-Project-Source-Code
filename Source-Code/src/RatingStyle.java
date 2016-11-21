/**
 * @Author Ding Li
 * @Date Oct. 2016
 * Calculate the rating style for each user, and write the styles into an arff file for weka.
 */
import java.io.*;
import java.util.*;

public class RatingStyle {
    public static void main(String args[]){
        String dirPath = "D:\\Dataset\\Review_Texts";
        String stylePath = "D:\\Dataset\\parsed\\cluster_weka.txt";
        String userPath = "D:\\Dataset\\parsed\\cluster_user.txt";
        HashMap<String, StyleVector> styleMap = new HashMap<>();
        ArrayList<String> fileNames = new ArrayList<>();
        ReadFileNames.getFile(dirPath,fileNames);
        getRatingStyle(styleMap, fileNames);
//        Iterator iter = styleMap.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            String userName = (String)entry.getKey();
//            StyleVector style = (StyleVector) entry.getValue();
//            System.out.println(userName + ": " + style.toString());
//        }
        Cluster.arff(stylePath, userPath, styleMap);
    }

    public static void getRatingStyle(HashMap<String, StyleVector> map, ArrayList<String> fileNames){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String userName;
        int value;
        int room;
        int location;
        int cleanliness;
        int service;
        StyleVector ratingStyle = null;
        for(int i=1;i<100;i++){
            try {
                String str = "";
                fis = new FileInputStream(fileNames.get(i));
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                while ((str = br.readLine()) != null) {
                    if(str.startsWith("<Author>")){
                        userName = str.substring(8);
                        if(map.get(userName) == null) {
                            map.put(userName, new StyleVector());
                        }
                        ratingStyle = map.get(userName);

                        do{
                            str = br.readLine();
                        }while(!str.startsWith("<Value>"));
                        value = Integer.parseInt(str.substring(7));
                        if(value>0) {
                            ratingStyle.setValue(value - 1);
                        }

                        str = br.readLine();
                        room = Integer.parseInt(str.substring(7));
                        if(room>0) {
                            ratingStyle.setRoom(room - 1);
                        }

                        str = br.readLine();
                        location = Integer.parseInt(str.substring(10));
                        if(location>0) {
                            ratingStyle.setLocation(location - 1);
                        }

                        str = br.readLine();
                        cleanliness = Integer.parseInt(str.substring(13));
                        if(cleanliness>0) {
                            ratingStyle.setCleanliness(cleanliness - 1);
                        }

                        str = br.readLine();
                        str = br.readLine();
                        service = Integer.parseInt(str.substring(9));
                        if(service>0) {
                            ratingStyle.setService(service - 1);
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
                    //System.out.println("...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
