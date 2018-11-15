package Application;
import java.io.IOException;
import java.util.List;

import DataType.TableAttributes;
import LinkToDataBase.LinkMySQL;
import Model.CategoryName;
import Model.TabList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Mainapp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<TabList> ListData = FXCollections.observableArrayList();
    private ObservableList<CategoryName> Categories = FXCollections.observableArrayList();;

    public ObservableList<TabList> getListData() {
        return ListData;
    }
    LinkMySQL Link=new LinkMySQL();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TodoList");
        initRootLayout();
        Backgroundshow();
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

    //
    public void Backgroundshow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainapp.class.getResource("/View/background.fxml"));
            Parent Background =  loader.load();
            rootLayout.setCenter(Background);
         //   OverAllController controller=loader.getController();
            //controller.Setmainapp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCategory(){
        List<TableAttributes> table=Link.QueryTheTableName();
        for (TableAttributes tmp:table){
            Categories.add(new CategoryName(tmp.getName()));
        }
    }

    //to be continued
    public ObservableList<CategoryName> getCategory(){
        setCategory();
        return Categories;
    }

}
