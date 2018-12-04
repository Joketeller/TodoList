package Application;
import java.io.IOException;
import java.util.List;

import DataType.CategoryInfo;
import Database.MySQLIO;
import Model.CategoryListNode;
import Model.EventListNode;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Mainapp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<EventListNode> Events = FXCollections.observableArrayList();
    private ObservableList<CategoryListNode> Categories = FXCollections.observableArrayList();
    private List<CategoryInfo> table=null;
    public ObservableList<EventListNode> getEvents(String CategoryName) {
        Events.clear();

        return Events;
    }
    MySQLIO Link=new MySQLIO();

    @Override
    public void start(Stage primaryStage) {
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
        table=Link.QueryTheTableName();
        for (CategoryInfo tmp:table){
            tmp.setInfo(Link.QueryListInfo(tmp.getName()));
            Categories.add(new CategoryListNode(tmp.getName()));
        }
    }


    //to be continued
    public ObservableList<CategoryListNode> getCategory(){
        setCategory();
        return Categories;
    }


}
