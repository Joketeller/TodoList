package Application;

import Model.CategoryListNode;
import Model.EventListNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import java.net.URL;
import java.util.ResourceBundle;

public class EventEditDialogController implements Initializable {
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    private EventListNode event;
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    TextField EventName;
    @FXML
    TextField StartTime;
    @FXML
    TextField EndTime;
    @FXML
    TextArea EventDetail;
    @FXML
    MenuButton EventUrgency;
    @FXML
    MenuButton Status;

    int urgency=0;
    boolean status=false;
    boolean okclicked=false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //EventUrgency.setText("Fuck");
        MenuItem urgent=new MenuItem("紧急");
        MenuItem important=new MenuItem("重要");
        MenuItem usual=new MenuItem("平常");
        EventUrgency.setText("请选择紧急程度");
        EventUrgency.getItems().addAll(urgent,important,usual);
        urgent.setOnAction((ActionEvent e)->{
            EventUrgency.setText("紧急");
            urgency=3;
        });
        important.setOnAction((ActionEvent e)->{
            EventUrgency.setText("重要");
            urgency=2;
        });
        usual.setOnAction((ActionEvent e)->{
            EventUrgency.setText("平常");
            urgency=1;
        });
        MenuItem finished=new MenuItem("已完成");
        MenuItem unfinished=new MenuItem("未完成");
        Status.getItems().addAll(finished,unfinished);
        Status.setText("是否完成");
        finished.setOnAction((ActionEvent e)->{
            Status.setText("已完成");
            status=true;
        });
        unfinished.setOnAction((ActionEvent e)->{
            Status.setText("未完成");
            status=false;
        });
    }

    public void setEventInfo(EventListNode pre){
        if (pre==null){
            EventName.setText("");
            StartTime.setText("现在");
            EndTime.setText("");
            EventDetail.setText("");
        }
        else
        {
            this.event=pre;
            EventName.setText(pre.getName());
            StartTime.setText(pre.getBeginTime());
            EndTime.setText(pre.getEndTime());
            EventDetail.setText(pre.getDetail());
            if (pre.isStatus()){
                Status.setText("已完成");
                status=true;
            }
            else{
                Status.setText("未完成");
                status=false;
            }

            if (pre.getUrgency()==3){
                EventUrgency.setText("紧急");
                urgency=3;
            }else{
                if (pre.getUrgency()==2){
                    EventUrgency.setText("重要");
                    urgency=2;
                }
                else{
                    EventUrgency.setText("平常");
                    urgency=1;
                }
            }
        }
    }

    public boolean isokclicked(){
        return okclicked;
    }

    private boolean isinputvalid(){
        String errorMessage="";
        if (EventName.getText()==null || EventName.getText().length()==0){
            errorMessage+="无效事件名\n";
        }
        if (StartTime.getText()==null || StartTime.getText().length()==0){
            errorMessage+="无效开始时间\n";
        }
        if (EndTime.getText()==null || EndTime.getText().length()==0){
            errorMessage+="无效截止时间\n";
        }
        if (urgency==0){
            errorMessage+="无效紧急度\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                    .title("无效输入")
                    .masthead("请修正错误输入")
                    .message(errorMessage)
                    .showError();
            return false;
        }


    }

    @FXML
    private void handleOK(){
        if (isinputvalid()){
            event.setName(EventName.getText());
            event.setBeginTime(StartTime.getText());
            event.setEndTime(EndTime.getText());
            event.setDetail(EventDetail.getText());
            event.setStatus(status);
            event.setUrgency(urgency);
            okclicked=true;
            dialogStage.close();
        }

    }


}
