package Model;

import javafx.beans.property.*;
import javafx.beans.property.StringProperty;

import java.time.LocalTime;

//列表视图属于显示的视图，会和View绑定
public class EventListNode {
    private final StringProperty RootListName;
    private final StringProperty Name;
    private final StringProperty BeginTime;
    private final StringProperty EndTime;
    private final BooleanProperty Status;
    private final IntegerProperty Urgency;
    public EventListNode() {
        this(null,null,null,null,null,0);
    }

    public EventListNode(String rootListName, String name, String BeginTime,String EndTime,Boolean status,int urgency) {
        this.RootListName = new SimpleStringProperty(rootListName);
        this.Name = new SimpleStringProperty(name);
        this.BeginTime=new SimpleStringProperty(BeginTime);
        this.EndTime=new SimpleStringProperty(EndTime);
        this.Status=new SimpleBooleanProperty(status);
        this.Urgency=new SimpleIntegerProperty(urgency);
    }

    public String getEndTime() {
        return EndTime.get();
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
}
