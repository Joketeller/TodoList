package Application;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class EventEditDialogController {
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }




}
