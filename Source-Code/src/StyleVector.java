/**
 * @Author Ding Li
 * @Date Oct. 2016
 * This class is used to represent the Rating Style of a user.
 */
public class StyleVector {
    private int[] value;
    private int[] room;
    private int[] location;
    private int[] cleanliness;
    private int[] service;

    public StyleVector(){
        this.value = new int[5];
        this.room = new int[5];
        this.location = new int[5];
        this.cleanliness = new int[5];
        this.service = new int[5];
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int index) {
        this.value[index]++;
    }

    public int[] getRoom() {
        return room;
    }

    public void setRoom(int index) {
        this.room[index]++;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int index) {
        this.location[index]++;
    }

    public int[] getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(int index) {
        this.cleanliness[index]++;
    }

    public int[] getService() {
        return service;
    }

    public void setService(int index) {
        this.service[index]++;
    }

    public String toString(){
        String result = "";
        for(int i=0;i<5;i++){
            result += String.valueOf(value[i]) + ",";
        }
        for(int i=0;i<5;i++){
            result += String.valueOf(room[i]) + ",";
        }
        for(int i=0;i<5;i++){
            result += String.valueOf(location[i]) + ",";
        }
        for(int i=0;i<5;i++){
            result += String.valueOf(cleanliness[i]) + ",";
        }
        for(int i=0;i<5;i++){
            if(i!=4) {
                result += String.valueOf(service[i]) + ",";
            }else{
                result += String.valueOf(service[i]);
            }
        }
        result += "\n";
        return result;
    }
}
