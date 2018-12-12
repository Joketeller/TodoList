package Model;

import Utils.EventDetail;
import javafx.beans.property.*;
import javafx.beans.property.StringProperty;

//列表视图属于显示的视图，会和View绑定
public class EventListNode {
    private final StringProperty RootListName=new SimpleStringProperty();
    private final StringProperty Name=new SimpleStringProperty();
    private final IntegerProperty StartMonth=new SimpleIntegerProperty();
    private final IntegerProperty StartDay=new SimpleIntegerProperty();
    private final IntegerProperty EndMonth=new SimpleIntegerProperty();
    private final IntegerProperty EndDay=new SimpleIntegerProperty();
    private final BooleanProperty Status=new SimpleBooleanProperty();
    private final IntegerProperty Urgency=new SimpleIntegerProperty();
    private final StringProperty Detail=new SimpleStringProperty();
    private final IntegerProperty StartYear=new SimpleIntegerProperty();
    private final IntegerProperty EndYear=new SimpleIntegerProperty();


    public EventListNode(){
        super();
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
        this.StartMonth.set(now.getStartmonth());
        this.StartDay.set(now.getStartday());
        this.EndMonth.set(now.getEndmonth());
        this.EndDay.set(now.getEndday());
        this.Name.set(now.getSummary());
        this.RootListName.set(now.getRootList());
        this.Detail.set(now.getDetail());
        this.StartYear.set(now.getStartyear());
        this.EndYear.set(now.getEndyear());
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

    public int getEndDay() {
        return EndDay.get();
    }

    public IntegerProperty endDayProperty() {
        return EndDay;
    }

    public void setEndDay(int endDay) {
        this.EndDay.set(endDay);
    }

    public int getEndMonth() {
        return EndMonth.get();
    }

    public IntegerProperty endMonthProperty() {
        return EndMonth;
    }

    public void setEndMonth(int endMonth) {
        this.EndMonth.set(endMonth);
    }

    public int getStartDay() {
        return StartDay.get();
    }

    public IntegerProperty startDayProperty() {
        return StartDay;
    }

    public void setStartDay(int startDay) {
        this.StartDay.set(startDay);
    }

    public int getStartMonth() {
        return StartMonth.get();
    }

    public IntegerProperty startMonthProperty() {
        return StartMonth;
    }

    public void setStartMonth(int startMonth) {
        this.StartMonth.set(startMonth);
    }

    public int getEndYear() {
        return EndYear.get();
    }

    public IntegerProperty endYearProperty() {
        return EndYear;
    }

    public void setEndYear(int endYear) {
        this.EndYear.set(endYear);
    }

    public int getStartYear() {
        return StartYear.get();
    }

    public IntegerProperty startYearProperty() {
        return StartYear;
    }

    public void setStartYear(int startYear) {
        this.StartYear.set(startYear);
    }
}
