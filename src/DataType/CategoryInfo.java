package DataType;

import java.util.List;

public class CategoryInfo {
    String Name;

    private int TotalEventNum=0;
    private int UnfinishedEventNum=0;

    List<EventDetail> Info=null;

    public List<EventDetail> getInfo() {
        return Info;
    }

    public void setInfo(List<EventDetail> info) {
        Info = info;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getUnfinishedEventNum() {
        return UnfinishedEventNum;
    }

    public void setUnfinishedEventNum(int unfinishedEventNum) {
        UnfinishedEventNum = unfinishedEventNum;
    }

    public int getTotalEventNum() {
        return TotalEventNum;
    }

    public void setTotalEventNum(int totalEventNum) {
        TotalEventNum = totalEventNum;
    }

    public void increasenum(){
        TotalEventNum++;
    }

    public void decreasenum() {TotalEventNum--;}

    public void calculatenum(){
        TotalEventNum=0;
        UnfinishedEventNum=0;
        for (EventDetail now:Info)
        {
            TotalEventNum++;
            if (!now.isStatus())
                UnfinishedEventNum++;
        }
//        System.out.println(TotalEventNum);
//        System.out.println(UnfinishedEventNum);
    }

    public void DeleteEvent(String Name){
        for (int i=Info.size()-1;i>=0;i--)
        {
            EventDetail now=Info.get(i);
            if (now.getSummary().equals(Name)){
                Info.remove(now);
                break;
            }
        }
        calculatenum();
    }
}
