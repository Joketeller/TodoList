package Controller;

import Utils.EventDetail;
import Model.CategoryListNode;
import Model.EventListNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    CheckBox FilterTag;

    @FXML
    ChoiceBox SortWay;

    SortedList<EventListNode> SortedEventList=null;
    FilteredList<EventListNode> FilteredEventList=null;
    ObservableList<EventListNode> OrginalEventList=null;
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
                     //   System.out.println("selecting " + newValue);
                        ShowEventList(newValue);
                        EventDetail.setText("");
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
                    //int index=EventList.getSelectionModel().getSelectedIndex();
                    EventListNode selected=EventList.getSelectionModel().getSelectedItem();
                 //   CategoryListNode rootselected=CategoryList.getSelectionModel().getSelectedItem();
                    if (selected!=null)
                     //EventRemove(index,selected,rootselected);
                        EditEvent(selected);
                    boolean freshedtt=FilterTag.isSelected();
                    FilterTag.setSelected(!freshedtt);
                    FilterTag.setSelected(freshedtt);
                   // EventList.refresh();
                  //  mainapp.EventEditDialogShow();
                }
                else if (event.getButton()==MouseButton.PRIMARY  && event.getClickCount() == 1)
                {
                    
                    System.out.println("单击了条目");
                    if (EventList.getSelectionModel().getSelectedItem()!=null) {
                        EventDetail.setText(EventList.getSelectionModel().getSelectedItem().getDetail());
                        System.out.println(EventList.getSelectionModel().getSelectedItem().getName());
                        System.out.println(EventList.getSelectionModel().getSelectedItem().getRootListName());
                    }

                }
                else if (event.getButton()==MouseButton.SECONDARY && event.getClickCount()==1){
                    System.out.println("右键点击了条目");
                    boolean ok=mainapp.ShowConfirmPage();
                    if (ok){
                       // int index=EventList.getSelectionModel().getSelectedIndex();
                        EventListNode selected=EventList.getSelectionModel().getSelectedItem();
                        CategoryListNode rootselected=CategoryList.getSelectionModel().getSelectedItem();
                        EventRemove(selected,rootselected);
                        //EventList.refresh();
                    }
                }
            }
        });

        CategoryList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2 )
                {
                    System.out.println("双击了条目");
                    int index=CategoryList.getSelectionModel().getSelectedIndex();
                    CategoryListNode selected=CategoryList.getSelectionModel().getSelectedItem();
                  //  CategoryListNode rootselected=CategoryList.getSelectionModel().getSelectedItem();
                    if (selected!=null)
                        //EventRemove(index,selected,rootselected);
                    // DeleteCategory(selected,index);
                           EditCategory(selected);
                    //  mainapp.EventEditDialogShow();
                }
                else if (event.getButton()==MouseButton.PRIMARY  && event.getClickCount() == 1) {
                    System.out.println("单击了条目");
                    //ShowEventDetail(null);
                    EventDetail.setText("");
                } else if (event.getButton()==MouseButton.SECONDARY && event.getClickCount() == 1){
                    boolean ok=mainapp.ShowConfirmPage();
                    if (ok) {
                        int index = CategoryList.getSelectionModel().getSelectedIndex();
                        CategoryListNode selected = CategoryList.getSelectionModel().getSelectedItem();
                        DeleteCategory(selected, index);
                    }
                }
            }
        });

        FilterTag.allowIndeterminateProperty().setValue(false);
        //过滤列表使用
        FilterTag.selectedProperty().addListener((observable, oldValue, newValue) -> {
            FilteredEventList.setPredicate(Event->{
                if (newValue.booleanValue()==false){
                    return true;
                }
                else
                {
                    if (Event.isStatus())
                        return false;
                    else
                        return true;
                }
            });
        });

//        SortTag.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.booleanValue()==false){
//                SortedEventList.setComparator(null);
//            }
//            else
//            {
//                SortedEventList.setComparator((u,v)->{
//                    return v.getUrgency()-u.getUrgency();
//                });
//            }
//        });
        SortWay.setItems(FXCollections.observableArrayList("按修改时间排序","按紧急度排序", "按开始时间排序", "按截止时间排序"));
       // SortWay.setAccessibleText("选择排序方式");
        SortWay.getSelectionModel().select(0);
        SortWay.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue()==0){
                SortedEventList.setComparator(null);
            }
            else
                if (newValue.intValue()==1){
                    SortedEventList.setComparator((u,v)->{
                        return v.getUrgency()-u.getUrgency();
                    });
                }
                else
                    if (newValue.intValue()==2){
                        SortedEventList.setComparator((u,v)->{
                            if (u.getStartYear()!=v.getStartYear())
                                return u.getStartYear()-v.getStartYear();
                            else
                                if (u.getStartMonth()!=u.getStartMonth())
                                    return u.getStartMonth()-v.getStartMonth();
                                else
                                    return u.getStartDay()-v.getStartDay();
                        });
                    }
                    else
                        if (newValue.intValue()==3){
                            SortedEventList.setComparator((u,v)->{
                                if (u.getEndYear()!=v.getEndYear())
                                    return u.getEndYear()-v.getEndYear();
                                else
                                    if (u.getEndMonth()!=v.getEndMonth())
                                        return u.getEndMonth()-v.getEndMonth();
                                    else
                                        return u.getEndDay()-v.getEndDay();
                            });
                        }
        });

        SortWay.setTooltip(new Tooltip("选择排序方式"));
    }

    //删除某个Event
    public void EventRemove(EventListNode now,CategoryListNode root){
        mainapp.DeleteEvent(now.getName(),now.getRootListName(),now.isStatus());
        root.decreaseNum(now.isStatus());
        // ShowCategory();
        //    System.out.println(CategoryList.getSelectionModel().getSelectedItem().getCategoryName());
        //    System.out.println(CategoryList.getSelectionModel().getSelectedItem().getTotalEventNum());
       // CategoryList.setItems(null);
      //  CategoryList.setItems(mainapp.getCategory());
        //EventList.getItems().remove();
        OrginalEventList.remove(now);
        //ShowEventDetail(null);
        EventDetail.setText("");
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
                ProgressBar pb=new ProgressBar();
                pb.setMinHeight(10);
                pb.setMaxHeight(10);
                pb.setMaxWidth(10000);
                pb.setProgress((double)(item.getTotalEventNum()-item.getUnfinishedEventNum())/(double)item.getTotalEventNum());
                item.unfinishedEventNumProperty().addListener(observable -> {
                    pb.setProgress((double)(item.getTotalEventNum()-item.getUnfinishedEventNum())/(double)item.getTotalEventNum());
                });
                item.totalEventNumProperty().addListener(observable -> {
                    pb.setProgress((double)(item.getTotalEventNum()-item.getUnfinishedEventNum())/(double)item.getTotalEventNum());
                });

                cell.setBottom(pb);
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
                BeginTime.textProperty().bind(item.startMonthProperty().asString().concat("月").concat(item.startDayProperty().asString().concat("日开始")));
                Text EndTime=new Text();
                EndTime.textProperty().bind(item.endMonthProperty().asString().concat("月").concat(item.endDayProperty().asString().concat("日截止")));
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
                        Cir.setFill(Color.GREEN);
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
                                Cir.setFill(Color.GREEN);
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
        FilterTag.setSelected(false);
        if (Cate!=null) {
            OrginalEventList=mainapp.getEvents(Cate.getCategoryName());
            FilteredEventList=new FilteredList<>(OrginalEventList,p->true);
            SortedEventList=new SortedList<>(FilteredEventList);
            SortedEventList.setComparator(null);
            EventList.setItems(SortedEventList);
            EventList.getSelectionModel().selectFirst();
        }
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
        CategoryList.getSelectionModel().selectFirst();
    }

    @FXML
    private void AddNewEvent() {
        if (CategoryList.getSelectionModel().getSelectedItem()==null){
            //弹窗界面得自己写，这个jar包感觉问题挺大的，会出错
//            Dialogs.create()
//                    .title("未选择分类")
//                    .masthead("请选择分类")
//                    .message("没有选择分类")
//                    .showError();
            mainapp.ShowErrorPage("未选择分类");
        }
        else {
            EventListNode tmp = new EventListNode();
            tmp.setRootListName(CategoryList.getSelectionModel().getSelectedItem().getCategoryName());
            boolean ok = mainapp.EventEditDialogShow(tmp);
            if (ok) {
                //界面层数据更改
                OrginalEventList.add(tmp);
                CategoryList.getSelectionModel().getSelectedItem().increaseNum(tmp.isStatus());
                //下面是数据层数据更改
                mainapp.AddEvent(tmp,CategoryList.getSelectionModel().getSelectedItem().getCategoryName());
            }
        }
    }


    private void EditEvent(EventListNode now){
        if (CategoryList.getSelectionModel().getSelectedItem()==null){
            //弹窗界面得自己写，这个jar包感觉问题挺大的，会出错
//            Dialogs.create()
//                    .title("未选择分类")
//                    .masthead("请选择分类")
//                    .message("没有选择分类")
//                    .showError();
            mainapp.ShowErrorPage("未选择分类");
        }
        else {
           // EventListNode tmp = new EventListNode();
//            now.setRootListName(CategoryList.getSelectionModel().getSelectedItem().getCategoryName());
            boolean changed=now.isStatus();
            String name=new String(now.getName());
            String root=new String(now.getRootListName());
            boolean ok = mainapp.EventEditDialogShow(now);
            if (ok) {
                //界面层数据更改
                CategoryList.getSelectionModel().getSelectedItem().decreaseNum(changed);
//                mainapp.getEvents(now.getRootListName()).add(now);
                CategoryList.getSelectionModel().getSelectedItem().increaseNum(now.isStatus());
                //下面是数据层数据更改
                mainapp.DeleteEvent(name,root,changed);
                mainapp.AddEvent(now,CategoryList.getSelectionModel().getSelectedItem().getCategoryName());
            }
        }
    }

    @FXML
    private void AddCategory(){
        CategoryListNode now=new CategoryListNode();
        boolean ok=mainapp.CategoryDialogShow(now);
        if (ok){
            //界面层修改
           // CategoryList.getItems().add(now);
          //  CategoryList.refresh();
            //数据层修改
            mainapp.newCategory(now.getCategoryName());
        }
    }

    //编辑Category
    private void EditCategory(CategoryListNode now){
        String changed=now.getCategoryName();
        boolean ok=mainapp.CategoryDialogShow(now);
        if (ok){
            mainapp.EditCategory(changed,now.getCategoryName());
        }
    }

    //删除分类
    private void DeleteCategory(CategoryListNode now,int index){
      //  ShowEventDetail(null);
        ShowEventList(null);
        EventDetail.setText("");
        mainapp.DeleteCategory(now.getCategoryName());
        CategoryList.getItems().remove(index);
    }

}
