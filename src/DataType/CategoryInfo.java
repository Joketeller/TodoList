package DataType;

import java.util.List;

public class CategoryInfo {
    String Name;

    public List<EventDetail> getInfo() {
        return Info;
    }

    public void setInfo(List<EventDetail> info) {
        Info = info;
    }

    private int numberofdata=0;

    List<EventDetail> Info=null;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumberofdata() {
        return numberofdata;
    }

    public void setNumberofdata(int numberofdata) {
        this.numberofdata = numberofdata;
    }

    public void increasenum(){
        numberofdata++;
    }

    public void decreasenum() {numberofdata--;}

}
