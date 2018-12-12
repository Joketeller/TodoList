package Utils;
//存储TodoList的
//需要开始时间和结束时间
//boolean 标记是否完成
//完成的紧急度标为-1 //同时特殊标示

public class EventDetail {
    String detail;
    String summary;
    private int id;
    private int urgency=0;
    private int startmonth=0;
    private int startday=0;
    private int endmonth=0;
    private int endday=0;
    private int startyear=0;
    private int endyear=0;

    String RootList;
    //数据库内存为tinyint
    boolean status =false;
    public EventDetail(int id, String detail, String summary, int urgency,boolean status,int startmonth,int startday,int endmonth,int endday,int startyear,int endyear) {
        this.id = id;
        this.detail = detail;
        this.summary = summary;
        this.urgency = urgency;
        this.status = status;
        this.startmonth=startmonth;
        this.startday=startday;
        this.endmonth=endmonth;
        this.endday=endday;
        this.startyear=startyear;
        this.endyear=endyear;
    }

    public EventDetail() {
        super();
        this.urgency=0;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStartmonth() {
        return startmonth;
    }

    public void setStartmonth(int startmonth) {
        this.startmonth = startmonth;
    }

    public int getEndday() {
        return endday;
    }

    public void setEndday(int endday) {
        this.endday = endday;
    }

    public int getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(int endmonth) {
        this.endmonth = endmonth;
    }

    public int getStartday() {
        return startday;
    }

    public void setStartday(int startday) {
        this.startday = startday;
    }

    public String getRootList() {
        return RootList;
    }

    public void setRootList(String rootList) {
        RootList = rootList;
    }

    public int getEndyear() {
        return endyear;
    }

    public void setEndyear(int endyear) {
        this.endyear = endyear;
    }

    public int getStartyear() {
        return startyear;
    }

    public void setStartyear(int startyear) {
        this.startyear = startyear;
    }
}

