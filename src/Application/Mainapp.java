package Application;
import java.io.IOException;
import java.util.List;

import DataType.CategoryInfo;
import DataType.EventDetail;
import Database.MySQLIO;
import Model.CategoryListNode;
import Model.EventListNode;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class Mainapp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<EventListNode> Events = FXCollections.observableArrayList();
    private ObservableList<CategoryListNode> Categories = FXCollections.observableArrayList();
    private List<CategoryInfo> table=null;
    MySQLIO Link=new MySQLIO();



    @Override
    public void start(Stage primaryStage) {
        table=Link.QueryTheTableName();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToDoList");
        initRootLayout();
        Backgroundshow();
        // System.out.println("ok!");
    }


    //背景窗口 包含菜单栏
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("/View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Backgroundshow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("/View/background.fxml"));
            Parent Background =  loader.load();
            rootLayout.setCenter(Background);
            OverAllController controller=loader.getController();
            controller.Setmainapp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setCategory(){
        Categories.clear();
        for (CategoryInfo tmp:table){
            tmp.setInfo(Link.QueryListInfo(tmp.getName()));
            tmp.calculatenum();
            //System.out.println(tmp.getTotalEventNum());
            Categories.add(new CategoryListNode(tmp.getName(),tmp.getTotalEventNum(),tmp.getUnfinishedEventNum()));
        }
    }



    //to be continued
    public ObservableList<CategoryListNode> getCategory(){
        setCategory();
        return Categories;
    }

    //to be continued
    public ObservableList<EventListNode> getEvents(String CategoryName) {
        setEvents(CategoryName);
        return Events;
    }

    public void setEvents(String Cate){
        Events.clear();
        for (CategoryInfo now:table){
            if (Cate.equals(now.getName()))
            {
            //    if (now.getInfo()!=null)
                    for (EventDetail tmp:now.getInfo())
                    {
                        if (tmp!=null)
                            Events.add(new EventListNode(tmp));
                    }
                return;
            }
        }
    }
    public void newCategory(String cateName)
    {
        CategoryInfo now=new CategoryInfo(cateName);
        now.newinfo();
        table.add(now);
        Categories.add(new CategoryListNode(cateName,0,0));
        Link.CreateTable(cateName);
    }


    public void DeleteEvent(String Name,String RootName,boolean finsished) {
        for (CategoryInfo tmp:table){
            if (tmp.getName().equals(RootName)){
                tmp.DeleteEvent(Name);
                Link.DeleteListInfo(Name,RootName);
            }
        }
    }


    //新增加的界面
    //编辑事项
    public boolean EventEditDialogShow(EventListNode now)
    {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("/View/EventEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("编辑事项");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EventEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEventInfo(now);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isokclicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //添加event
    public void AddEvent(EventListNode now,String root){
        for (CategoryInfo tmp:table){
            if (tmp.getName().equals(root)){
                EventDetail one=new EventDetail(0,now.getDetail(),now.getName(),now.getUrgency(),now.isStatus(),now.getBeginTime(),now.getEndTime());
                tmp.addevent(one);
                Link.InsertList(one,root);
            }
        }
    }


    //添加Category
    public boolean CategoryDialogShow(CategoryListNode now){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("/View/CategoryEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("编辑分类");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CategoryEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setcategory(now);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isokclicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void EditCategory(String before,String after){
        Link.RenameTable(before,after);
        for (CategoryInfo now:table){
            if (now.getName().equals(before)) {
                now.setName(after);
                break;
            }
        }
    }

    public void DeleteCategory(String CateName){
        for (CategoryInfo tmp:table){
            if (tmp.getName().equals(CateName)){
                table.remove(tmp);
                break;
            }
        }
        Link.DropTable(CateName);
    }


    public void ShowErrorPage(String ErorrInformation){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("/View/Warning.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("出错了");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            WarningController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.seterrorinformation(ErorrInformation);
         //   controller.setcategory(now);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            //return controller.isokclicked();
        } catch (IOException e) {
            e.printStackTrace();
         //   return false;
        }
    }

}
