package Model;
//列表视图属于显示的视图，会和View绑定
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategoryListNode {
    private StringProperty CategoryName=new SimpleStringProperty();
    private IntegerProperty UnfinishedEventNum =new SimpleIntegerProperty();
    private IntegerProperty FinishedEventNum=new SimpleIntegerProperty();

    public int getUnfinishedEventNum() {
        return UnfinishedEventNum.get();
    }

    public IntegerProperty unfinishedEventNumProperty() {
        return UnfinishedEventNum;
    }

    public void setUnfinishedEventNum(int unfinishedEventNum) {
        this.UnfinishedEventNum.set(unfinishedEventNum);
    }

    private IntegerProperty TotalEventNum=new SimpleIntegerProperty();
    public CategoryListNode(){
        this(null);
    }

    public int getTotalEventNum() {
        return UnfinishedEventNum.get();
    }

    public IntegerProperty totalEventNumProperty() {
        return UnfinishedEventNum;
    }

    public void setTotalEventNum(int totalEventNum) {
        this.UnfinishedEventNum.set(totalEventNum);
    }

    public CategoryListNode(String categoryName) {
        this.CategoryName.set(categoryName);
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

    public int getFinishedEventNum() {
        return FinishedEventNum.get();
    }

    public IntegerProperty finishedEventNumProperty() {
        return FinishedEventNum;
    }

    public void setFinishedEventNum(int finishedEventNum) {
        this.FinishedEventNum.set(finishedEventNum);
    }
}
