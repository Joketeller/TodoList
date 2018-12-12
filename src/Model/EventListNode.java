package Model;

import Utils.EventDetail;
import javafx.beans.property.*;
import javafx.beans.property.StringProperty;

//列表视图属于显示的视图，会和View绑定
public class EventListNode {
    private final StringProperty RootListName=new SimpleStringProperty();
    private final StringProperty Name=new SimpleStringProperty();
    private final StringProperty BeginTime=new SimpleStringProperty();
    private final StringProperty EndTime=new SimpleStringProperty();
    private final BooleanProperty Status=new SimpleBooleanProperty();
    private final IntegerProperty Urgency=new SimpleIntegerProperty();
    private final StringProperty Detail=new SimpleStringProperty();

    public String getEndTime() {
        return EndTime.get();
    }

    public EventListNode(){
        super();
    }

    public StringProperty endTimeProperty() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        this.EndTime.set(endTime);
    }

    public String getBeginTime() {
        return BeginTime.get();
    }

    public StringProperty beginTimeProperty() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        this.BeginTime.set(beginTime);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getRootListName() {
        return RootListName.get();
    }

    public StringProperty rootListNameProperty() {
        return RootListName;
    }

    public void setRootListName(String rootListName) {
        this.RootListName.set(rootListName);
    }

    public boolean isStatus() {
        return Status.get();
    }

    public BooleanProperty statusProperty() {
        return Status;
    }

    public void setStatus(boolean status) {
        this.Status.set(status);
    }

    public int getUrgency() {
        return Urgency.get();
    }

    public IntegerProperty urgencyProperty() {
        return Urgency;
    }

    public void setUrgency(int urgency) {
        this.Urgency.set(urgency);
    }

    public EventListNode(EventDetail now){
        this.Status.set(now.isStatus());
        this.Urgency.set(now.getUrgency());
        this.EndTime.set(now.getEndtime());
        this.BeginTime.set(now.getBegintime());
        this.Name.set(now.getSummary());
        this.RootListName.set(now.getRootList());
        this.Detail.set(now.getDetail());
    }

    public String getDetail() {
        return Detail.get();
    }

    public StringProperty detailProperty() {
        return Detail;
    }

    public void setDetail(String detail) {
        this.Detail.set(detail);
    }

}
