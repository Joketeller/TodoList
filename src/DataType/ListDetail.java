package DataType;
//存储TodoList的
public class ListDetail {
    String detail;
    String summary;
    int id=0;
    int urgency=0;
    public ListDetail(int id,String detail,String summary,int urgency) {
        this.id = id;
        this.detail = detail;
        this.summary = summary;
        this.urgency = urgency;
    }

    public ListDetail() {
        super();
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

}

