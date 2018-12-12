package Model;
//列表视图属于显示的视图，会和View绑定
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategoryListNode {
    private final StringProperty CategoryName=new SimpleStringProperty();
    private final IntegerProperty UnfinishedEventNum =new SimpleIntegerProperty();
    private final IntegerProperty TotalEventNum=new SimpleIntegerProperty();

    public int getUnfinishedEventNum() {
        return UnfinishedEventNum.get();
    }

    public IntegerProperty unfinishedEventNumProperty() {
        return UnfinishedEventNum;
    }

    public void setUnfinishedEventNum(int unfinishedEventNum) {
        this.UnfinishedEventNum.set(unfinishedEventNum);
    }


    public CategoryListNode(){
        super();
    }

    public int getTotalEventNum() {
        return TotalEventNum.get();
    }

    public IntegerProperty totalEventNumProperty() {
        return TotalEventNum;
    }

    public void setTotalEventNum(int totalEventNum) {
        this.TotalEventNum.set(totalEventNum);
    }

    public CategoryListNode(String categoryName, int total, int unfinished) {
        this.CategoryName.set(categoryName);
        this.UnfinishedEventNum.set(unfinished);
        this.TotalEventNum.set(total);
       // System.out.println(this.TotalEventNum);
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

    public void decreaseNum(boolean status) {

        if (!status)
        {
            int un=getUnfinishedEventNum();
            setUnfinishedEventNum(un-1);
        }
        int tmp=getTotalEventNum();
        setTotalEventNum(tmp-1);
        //System.out.println(tmp-1);
     //   System.out.println(TotalEventNum);
    }

    public  void increaseNum(boolean status){
        if (!status)
        {
            int un=getUnfinishedEventNum();
            setUnfinishedEventNum(un+1);
        }
        int tmp=getTotalEventNum();
        setTotalEventNum(tmp+1);
    }


}
