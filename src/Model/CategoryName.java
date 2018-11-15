package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategoryName {
    private final StringProperty CategoryName;

    public CategoryName(){
        this(null);
    }

    public CategoryName(String categoryName) {
        this.CategoryName = new SimpleStringProperty(categoryName);
    }



    public String getCategoryName() {
        return CategoryName.get();
    }

    public StringProperty categoryNameProperty() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName.set(categoryName);
    }
}
