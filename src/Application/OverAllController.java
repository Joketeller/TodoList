package Application;

import DataType.CategoryInfo;
import DataType.EventDetail;
import Model.CategoryListNode;
import Model.EventListNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

//主界面
public class OverAllController implements Initializable {
    @FXML
    ListView<CategoryListNode> CategoryList;
    @FXML
    ListView<EventListNode> EventList;
    @FXML
    TextArea EventDetail;

    private Mainapp mainapp=null;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        CategoryList.setPrefHeight(30);
        CategoryList.setPrefWidth(50);
        CategoryList.setCellFactory((ListView<CategoryListNode> data)-> new TitleCell());
        EventList.setCellFactory((ListView<EventListNode> data)-> new EventCell());
    }

    //分类列表视图
    private class TitleCell extends ListCell<CategoryListNode> {
        @Override
        protected void updateItem(CategoryListNode item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty && item != null) {
                BorderPane cell = new BorderPane();
                Text title = new Text(item.getCategoryName());
                title.setFont(Font.font("STKaiTi",16));
                cell.setTop(title);
                Text numinfo = new Text(item.getTotalEventNum() + " 个任务");  //attention
                numinfo.setFill(Color.BLUE);
                numinfo.setFont(Font.font("STKaiTi",10));
                cell.setLeft(numinfo);
                Text unfinished = new Text("其中 " + item.getUnfinishedEventNum() + " 个未完成");
                unfinished.setFont(Font.font("STKaiTi",10));
                unfinished.setFill(Color.LIGHTCORAL);
                cell.setRight(unfinished);
                setGraphic(cell);
            } else if (empty) {
                setText(null);
                setGraphic(null);
            }
        }
    }

    //Event列表视图
    private class EventCell extends ListCell<EventListNode> {
        @Override
        protected void updateItem(EventListNode item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty &&  item!=null)
            {
                BorderPane cell=new BorderPane();
                Text title= new Text(item.getName());
                title.setFont(Font.font("STKaiTi",14));
                cell.setTop(title);
                Text Status=null;
                Text BeginTime=new Text("开始时间："+item.getBeginTime());
                Text EndTime=new Text("结束时间："+item.getEndTime());
                BeginTime.setFont(Font.font("STKaiTi",10));
                EndTime.setFont(Font.font("STKaiTi",10));
                BeginTime.setFill(Color.BLUE);
                EndTime.setFill(Color.BLUE);
                Status.setFont(Font.font("STKaiTi",10));
                Circle Cir=new Circle(10);
                if (item.getUrgency()==3)
                {
                    Cir.setFill(Color.RED);
                }
                else {
                    if (item.getUrgency() == 2) {
                        Cir.setFill(Color.CORAL);
                    } else if (item.getUrgency() == 1) {
                        Cir.setFill(Color.LIGHTGOLDENRODYELLOW);
                    } else if (item.getUrgency() == 0) {
                        Cir.setFill(Color.LIGHTGREY);
                    }
                }
                if (item.isStatus()){
                    Status=new Text("完成情况：已完成");
                    Cir.setFill(Color.LIGHTGREY);
                    Status.setFill(Color.LIGHTGREY);
                }
                else {
                    Status=new Text("完成情况：未完成");
                    Status.setFill(Color.LIGHTGOLDENRODYELLOW);
                }
                cell.setBottom(Status);
                cell.setLeft(Cir);
                cell.setCenter(BeginTime);
                cell.setRight(EndTime);
                setGraphic(cell);
            }
            else if (empty)
            {
                setText(null);
                setGraphic(null);
            }
        }
    }

    //显示事项列表
    //未完成
    private void ShowEventList(CategoryInfo Cate)
    {
        EventList.setItems(null);
        EventList.setItems(mainapp.getEvents(Cate.getName()));
    }


    //显示细节
    private void ShowEventDetail(EventDetail detail)
    {
        if (detail!=null)
        {
            EventDetail.setText(detail.getDetail());
            EventDetail.setFont(Font.font(12));
        }
        else
        {
            EventDetail.setText("");
        }
    }


    public void Setmainapp(Mainapp mainapp){
        this.mainapp=mainapp;
        CategoryList.setItems(mainapp.getCategory());
    }


}
