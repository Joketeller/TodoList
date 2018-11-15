package Model;

import javafx.beans.property.*;
import javafx.beans.property.StringProperty;

import java.time.LocalTime;


public class TabList {
    private final StringProperty RootListName;
    private final StringProperty Name;
    private final ObjectProperty<LocalTime> LastModifiedTime;
    public TabList() {
        this(null,null,null);
    }

    public TabList(String rootListName, String name, ObjectProperty<LocalTime> lastModifiedTime) {
        this.RootListName = new SimpleStringProperty(rootListName);
        this.Name = new SimpleStringProperty(name);
        LastModifiedTime = lastModifiedTime;
    }

    public LocalTime getLastModifiedTime() {
        return LastModifiedTime.get();
    }

    public ObjectProperty<LocalTime> lastModifiedTimeProperty() {
        return LastModifiedTime;
    }

    public void setLastModifiedTime(LocalTime lastModifiedTime) {
        this.LastModifiedTime.set(lastModifiedTime);
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
}
