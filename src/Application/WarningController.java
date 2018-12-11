package Application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WarningController implements Initializable {

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    Label ErrorInformation;

    @FXML
    private void handleOK(){
        dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    public void seterrorinformation(String Name){
        ErrorInformation.setText(Name);
        ErrorInformation.setTextFill(Color.RED);
    }

}
