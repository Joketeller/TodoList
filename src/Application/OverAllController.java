package Application;

import DataType.CategoryInfo;
import DataType.EventDetail;
import Model.CategoryListNode;
import Model.EventListNode;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
        CategoryList.getFocusModel().focusedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if (newValue!=null) {
                        System.out.println("selecting " + newValue);
                        ShowEventList(newValue);
                    }
                })
        );
        EventList.getFocusModel().focusedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        System.out.println("selecting " + newValue);
                        EventDetail.setText(newValue.getDetail());
                    }
                })
           );


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
//                System.out.println(item.getTotalEventNum());
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
                Text Status=null;
                Text BeginTime=new Text("开始时间："+item.getBeginTime());
                Text EndTime=new Text("结束时间："+item.getEndTime());
                BeginTime.setFont(Font.font("STKaiTi",10));
                EndTime.setFont(Font.font("STKaiTi",10));
                BeginTime.setFill(Color.BLUE);
                EndTime.setFill(Color.BLUE);
                Circle Cir=new Circle(5);
                if (item.getUrgency()==3)
                {
                    Cir.setFill(Color.PURPLE);
                }
                else {
                    if (item.getUrgency() == 2) {
                        Cir.setFill(Color.CORAL);
                    } else if (item.getUrgency() == 1) {
                        Cir.setFill(Color.LIGHTCORAL);
                    } else if (item.getUrgency() == 0) {
                        Cir.setFill(Color.GREY);
                    }
                }
                if (item.isStatus()){
                    Status=new Text("完成情况：已完成");
                    Cir.setFill(Color.LIGHTGREY);
                    Status.setFill(Color.GREY);
                }
                else {
                    Status=new Text("完成情况：未完成");
                    Status.setFill(Color.RED);
                }
                Status.setFont(Font.font("STKaiTi",10));
                cell.setTop(title);
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
    private void ShowEventList(CategoryListNode Cate)
    {
        EventList.setItems(null);
        EventList.setItems(mainapp.getEvents(Cate.getCategoryName()));
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
    @FXML
    private void show()
    {
        mainapp.addCategory();
    }


}
