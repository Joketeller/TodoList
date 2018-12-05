package DataType;
//存储TodoList的
//需要开始时间和结束时间
//boolean 标记是否完成
//完成的紧急度标为-1 //同时特殊标示

public class EventDetail {
    String detail;
    String summary;
    private int id;
    private int urgency=0;
    String begintime;
    String endtime;
    String RootList;
    //数据库内存为tinyint
    boolean status =false;
    public EventDetail(int id, String detail, String summary, int urgency,boolean status,String begintime,String endtime) {
        this.id = id;
        this.detail = detail;
        this.summary = summary;
        this.urgency = urgency;
        this.status = status;
        this.begintime=begintime;
        this.endtime=endtime;
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

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRootList() {
        return RootList;
    }

    public void setRootList(String rootList) {
        RootList = rootList;
    }
}

