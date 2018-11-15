package Application;
import LinkToDataBase.LinkMySQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TodoStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/background.fxml"));
        primaryStage.setTitle("TEST");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        LinkMySQL Link=new LinkMySQL();
        //Link.CreateTable("Fuck");
//        ListDetail tmp=new ListDetail();
//        tmp.setDetail("FUK");
//        tmp.setSummary("Fck");
//        tmp.setUrgency(10);
//        tmp.setId(1);
//        Link.DeleteListInfo(tmp,"Fuck");
     //   Link.RenameTable("Fuck","FFFF");
        launch(args);
    }
}
