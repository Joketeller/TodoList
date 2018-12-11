package Application;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ConfirmDialogController {

    Stage dialogStage = new Stage();
    public void setDialogStage(Stage stage)
    {
        this.dialogStage=stage;
    }
    @FXML
    private boolean okclicked=false;

    @FXML
    private void handleOK(){
        this.okclicked=true;
        dialogStage.close();
    }

    public boolean isokclicked(){
        return okclicked;
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
}
