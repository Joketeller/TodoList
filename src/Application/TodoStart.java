package Application;
import DataType.ListDetail;
import DataType.TableAttributes;
import LinkToDataBase.LinkMySQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class TodoStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("TEST");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        LinkMySQL Link=new LinkMySQL();
      //  Link.DropTable("Fuck");
        launch(args);
    }
}
