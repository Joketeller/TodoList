package Utils;

public class MyDate {
    int Month=0;
    int Day=0;
    public MyDate(int Month,int Day){
        this.Day=Day;
        this.Month=Month;
    }

    public MyDate(){
        super();
    }

    @Override
    public String toString() {
        return new String(Month+"月"+Day+"日");
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }
}

