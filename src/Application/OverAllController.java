package Application;

import DataType.CategoryInfo;
import DataType.EventDetail;
import Model.CategoryListNode;
import Model.EventListNode;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.controlsfx.dialog.Dialogs;

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



    @FXML
    private void clickbutton(){
        System.out.println("点击了按钮");
      //  mainapp.
    }

    private Mainapp mainapp=null;

    //初始化
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
        EventList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2 )
                {
                    System.out.println("双击了条目");
                    int index=EventList.getSelectionModel().getSelectedIndex();
                    EventListNode selected=EventList.getSelectionModel().getSelectedItem();
                    CategoryListNode rootselected=CategoryList.getSelectionModel().getSelectedItem();
                    if (selected!=null && rootselected!=null)
                     EventRemove(index,selected,rootselected);
                  //  mainapp.EventEditDialogShow();
                }
                else if (event.getButton()==MouseButton.PRIMARY  && event.getClickCount() == 1)
                {
                    
                    System.out.println("单击了条目");
                }
            }
        });
    }

    //删除某个Event
    public void EventRemove(int index, EventListNode now,CategoryListNode root){
        mainapp.DeleteEvent(now.getName(),now.getRootListName(),now.isStatus());
        root.decreaseNum(now.isStatus());
        // ShowCategory();
        //
        //    System.out.println(CategoryList.getSelectionModel().getSelectedItem().getCategoryName());
        //    System.out.println(CategoryList.getSelectionModel().getSelectedItem().getTotalEventNum());
       // CategoryList.setItems(null);
      //  CategoryList.setItems(mainapp.getCategory());
        EventList.getItems().remove(index);
        ShowEventDetail(null);
    }

    //分类列表视图
    private class TitleCell extends ListCell<CategoryListNode> {
        @Override
        protected void updateItem(CategoryListNode item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty && item != null) {
                BorderPane cell = new BorderPane();
                Text title = new Text();
                title.textProperty().bind(item.categoryNameProperty());
                title.setFont(Font.font("STKaiTi",16));
                cell.setTop(title);
//                System.out.println(item.getTotalEventNum());
                //Text numinfo = new Text(item.getTotalEventNum() + " 个任务");  //attention
                Text numinfo = new Text();
                numinfo.textProperty().bind(item.totalEventNumProperty().asString().concat("个任务"));
                numinfo.setFill(Color.BLUE);
                numinfo.setFont(Font.font("STKaiTi",10));
                cell.setLeft(numinfo);
                Text unfinished = new Text();
                unfinished.textProperty().bind(item.unfinishedEventNumProperty().asString().concat("个任务未完成"));
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
                Text title= new Text();
                title.textProperty().bind(item.nameProperty());
                title.setFont(Font.font("STKaiTi",14));
                Text BeginTime=new Text();
                BeginTime.textProperty().bind(item.beginTimeProperty().concat("开始"));
                Text EndTime=new Text();
                EndTime.textProperty().bind(item.endTimeProperty().concat("截止"));
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
                item.urgencyProperty().addListener(((observable, oldValue, newValue) -> {
                    if (newValue.intValue()==3)
                    {
                        Cir.setFill(Color.PURPLE);
                    }
                    else
                    {
                        if (newValue.intValue()==2){
                            Cir.setFill(Color.CORAL);
                        }
                        else
                        {
                            if (newValue.intValue()==1){
                                Cir.setFill(Color.LIGHTCORAL);
                            }
                            else
                                Cir.setFill(Color.GREY);
                        }
                    }
                }));
                Text FinishedStatus=new Text("完成情况：已完成");
                FinishedStatus.setFill(Color.GREY);
                FinishedStatus.setFont(Font.font("STKaiTi",10));
                Text UnfinishedStatus=new Text("完成情况：未完成");
                UnfinishedStatus.setFont(Font.font("STKaiTi",10));
                UnfinishedStatus.setFill(Color.RED);
                if (item.isStatus()){
                    Cir.setFill(Color.LIGHTGREY);
                    cell.setBottom(FinishedStatus);
                   // Status.setFill(Color.GREY);
                }
                else {
                  //  Status.setText("完成情况：未完成");
                    cell.setBottom(UnfinishedStatus);
                }
                item.statusProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.booleanValue()==true){
                        //Status.setText("完成情况：已完成");
                        Cir.setFill(Color.LIGHTGREY);
                        cell.setBottom(FinishedStatus);
                     //   Status.setFill(Color.GREY);
                        ;
                    }
                    else {
                        cell.setBottom(UnfinishedStatus);
                    }
                });
           //     Status.setFont(Font.font("STKaiTi",10));
                cell.setTop(title);
              //  cell.setBottom(Status);
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

    //显示列表视图
    public void ShowCategory(){
        CategoryList.setItems(null);
        CategoryList.setItems(mainapp.getCategory());
    }


    public void Setmainapp(Mainapp mainapp){
        this.mainapp=mainapp;
        ShowCategory();
        CategoryList.getFocusModel().focus(0);
    }



    @FXML
    private void show()
    {
        mainapp.addCategory();
    }

    @FXML
    private void AddNewEvent() {
        if (CategoryList.getSelectionModel().getSelectedItem()==null){
            //弹窗界面得自己写，这个jar包感觉问题挺大的，会出错
            Dialogs.create()
                    .title("未选择分类")
                    .masthead("请选择分类")
                    .message("没有选择分类")
                    .showError();
        }
        else {
            EventListNode tmp = new EventListNode();
            tmp.setRootListName(CategoryList.getSelectionModel().getSelectedItem().getCategoryName());
            boolean ok = mainapp.EventEditDialogShow(tmp);
            if (ok) {
                mainapp.getEvents(tmp.getRootListName()).add(tmp);
                CategoryList.getSelectionModel().getSelectedItem().increaseNum(tmp.isStatus());
            }
        }
    }

}
