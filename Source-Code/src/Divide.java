/**
 * @Author Ding Li
 * @Date Nov. 2016
 * Divide users into different groups based on the result of clustering in weka.
 */

import java.io.*;
import java.util.*;

public class Divide {
    public static void main(String[] args){
        String dirPath = "D:\\Dataset\\Review_Texts";
        String cluster1 = "D:\\Dataset\\parsed\\cluster_2_1.txt";
        String cluster2 = "D:\\Dataset\\parsed\\cluster_2_2.txt";
        String clusterPath = "D:\\Dataset\\parsed\\cluster_2.arff";
        String userPath = "D:\\Dataset\\parsed\\cluster_user.txt";
        HashMap<String, Integer> userMap = new HashMap<>();
        HashMap<String, StyleVector> styleMap = new HashMap<>();
        ArrayList<String> fileNames = new ArrayList<>();
        ReadFileNames.getFile(dirPath,fileNames);
        ReadAllUsers.read(userMap,fileNames);
        RatingStyle.getRatingStyle(styleMap, fileNames);
        divide(cluster1,cluster2,clusterPath,userPath,styleMap,fileNames,userMap);
    }

    public static void divide(String path1,String path2, String clusterPath, String userPath,
                              HashMap<String, StyleVector> styleMap, ArrayList<String> fileNames,
                              HashMap<String, Integer> userMap){
        FileWriter fw0 = null;
        FileWriter fw1 = null;

        FileInputStream fis1 = null;
        InputStreamReader isr1 = null;
        BufferedReader br1 = null;

        FileInputStream fis2 = null;
        InputStreamReader isr2 = null;
        BufferedReader br2 = null;

        FileInputStream fis3 = null;
        InputStreamReader isr3 = null;
        BufferedReader br3 = null;

        String line1 = "";
        String line2 = "";
        //String str = "";
        HashMap<String, Integer> map = new HashMap<>();

        int value;
        int room;
        int location;
        int cleanliness;
        int service;
        int overall;
        int userId;
        int hotelId;
        String userName;
        StyleVector ratingStyle;
        int NUM_OF_USERS = userMap.size();

        try{
//            fw1 = new FileWriter(path1);
//            fw2 = new FileWriter(path2);

            fis1 = new FileInputStream(clusterPath);
            isr1 = new InputStreamReader(fis1);
            br1 = new BufferedReader(isr1);

            fis2 = new FileInputStream(userPath);
            isr2 = new InputStreamReader(fis2);
            br2 = new BufferedReader(isr2);

//            fis3 = new FileInputStream(originPath);
//            isr3 = new InputStreamReader(fis3);
//            br3 = new BufferedReader(isr3);

            while((line1 = br1.readLine())!=null && (line2 = br2.readLine())!=null){
                if(line1.charAt(line1.length()-1) == '0'){
                    map.put(line2, new Integer(0));
                }else{
                    map.put(line2, new Integer(1));
                }
            }

//        Iterator iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            String userName = (String)entry.getKey();
//            int cluster = ((Integer)entry.getValue()).intValue();
//            System.out.println(userName + ": ");
//            System.out.println(cluster);
//        }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fis1.close();
                isr1.close();
                br1.close();
                fis2.close();
                isr2.close();
                br2.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            fw0 = new FileWriter(path1);
            fw1 = new FileWriter(path2);
        } catch(IOException e){
            e.printStackTrace();
        }

        for(int i=1;i<100;i++){
            try {
                String str = "";
                fis3 = new FileInputStream(fileNames.get(i));
                isr3 = new InputStreamReader(fis3);
                br3 = new BufferedReader(isr3);

//                fw1 = new FileWriter(path1);
//                fw2 = new FileWriter(path2);

                while ((str = br3.readLine()) != null) {
                    if(str.startsWith("<Author>")){
                        userName = str.substring(8);
//                        if(map.get(userName) == null) {
//                            map.put(userName, new StyleVector());
//                        }
                        ratingStyle = styleMap.get(userName);
                        userId = userMap.get(userName);

                        do{
                            str = br3.readLine();
                        }while(!str.startsWith("<Overall>"));
                        overall = Integer.parseInt(str.substring(9));

                        str = br3.readLine();
                        value = Integer.parseInt(str.substring(7));
                        if(value < 1) {
                            value = 1;
                        }

                        str = br3.readLine();
                        room = Integer.parseInt(str.substring(7));
                        if(room < 1) {
                            room = 1;
                        }

                        str = br3.readLine();
                        location = Integer.parseInt(str.substring(10));
                        if(location < 1) {
                            location = 1;
                        }

                        str = br3.readLine();
                        cleanliness = Integer.parseInt(str.substring(13));
                        if(cleanliness < 1) {
                            cleanliness = 1;
                        }

                        str = br3.readLine();
                        str = br3.readLine();
                        service = Integer.parseInt(str.substring(9));
                        if(service < 1) {
                            service = 1;
                        }

                        hotelId = i;

                        if(map.get(userName).intValue()==0){
                            fw0.write(overall + " ");
                            fw0.write(Integer.toString(userId) + ":1");
                            fw0.write(" ");
                            fw0.write(Integer.toString(hotelId + NUM_OF_USERS) + ":1");
                            fw0.write(" ");

                            int idx = NUM_OF_USERS + 100;
                            fw0.write(Integer.toString(idx++) + ":" + value + " ");
                            fw0.write(Integer.toString(idx++) + ":" + room + " ");
                            fw0.write(Integer.toString(idx++) + ":" + location + " ");
                            fw0.write(Integer.toString(idx++) + ":" + cleanliness + " ");
                            fw0.write(Integer.toString(idx++) + ":" + service + " ");

                            StyleVector style = styleMap.get(userName);
                            for(int j=0;j<5;j++){
                                if(style.getValue()[j]==1){
                                    fw0.write(Integer.toString(idx++) + ":" + style.getValue()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getRoom()[j]==1){
                                    fw0.write(Integer.toString(idx++) + ":" + style.getRoom()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getLocation()[j]==1){
                                    fw0.write(Integer.toString(idx++) + ":" + style.getLocation()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getCleanliness()[j]==1){
                                    fw0.write(Integer.toString(idx++) + ":" + style.getCleanliness()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getService()[j]==1){
                                    if(j!=4) {
                                        fw0.write(Integer.toString(idx++) + ":" + style.getService()[j] + " ");
                                    }else{
                                        fw0.write(Integer.toString(idx++) + ":" + style.getService()[j]);
                                    }
                                }else{
                                    idx++;
                                }
                            }

                            fw0.write("\n");
                        }else{
                            fw1.write(overall + " ");
                            fw1.write(Integer.toString(userId) + ":1");
                            fw1.write(" ");
                            fw1.write(Integer.toString(hotelId + NUM_OF_USERS) + ":1");
                            fw1.write(" ");

                            int idx = NUM_OF_USERS + 100;
                            fw1.write(Integer.toString(idx++) + ":" + value + " ");
                            fw1.write(Integer.toString(idx++) + ":" + room + " ");
                            fw1.write(Integer.toString(idx++) + ":" + location + " ");
                            fw1.write(Integer.toString(idx++) + ":" + cleanliness + " ");
                            fw1.write(Integer.toString(idx++) + ":" + service + " ");

                            StyleVector style = styleMap.get(userName);
                            for(int j=0;j<5;j++){
                                if(style.getValue()[j]==1){
                                    fw1.write(Integer.toString(idx++) + ":" + style.getValue()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getRoom()[j]==1){
                                    fw1.write(Integer.toString(idx++) + ":" + style.getRoom()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getLocation()[j]==1){
                                    fw1.write(Integer.toString(idx++) + ":" + style.getLocation()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getCleanliness()[j]==1){
                                    fw1.write(Integer.toString(idx++) + ":" + style.getCleanliness()[j] + " ");
                                }else{
                                    idx++;
                                }
                            }
                            for(int j=0;j<5;j++){
                                if(style.getService()[j]==1){
                                    if(j!=4) {
                                        fw1.write(Integer.toString(idx++) + ":" + style.getService()[j] + " ");
                                    }else{
                                        fw1.write(Integer.toString(idx++) + ":" + style.getService()[j]);
                                    }
                                }else{
                                    idx++;
                                }
                            }

                            fw1.write("\n");
                        }


                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br3.close();
                    isr3.close();
                    fis3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try{
            fw0.close();
            fw1.close();;
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
