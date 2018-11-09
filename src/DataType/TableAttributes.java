package DataType;

import java.util.ArrayList;
import java.util.List;

public class TableAttributes {
    String Name;
    int numberofdata;
    List<ListDetail> Info;
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

}
