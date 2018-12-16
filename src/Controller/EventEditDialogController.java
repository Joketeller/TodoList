package Controller;

import Model.EventListNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
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

    private Mainapp  mainapp=null;

    public
    @FXML
    TextField EventName;
    @FXML
    DatePicker StartTime;
    @FXML
    DatePicker EndTime;
    @FXML
    TextArea EventDetail;
    @FXML
    MenuButton EventUrgency;
    @FXML
    MenuButton Status;

    int urgency=1;
    boolean status=false;
    boolean okclicked=false;
    int startmonth=0;
    int startday=0;
    int endday=0;
    int endmonth=0;
    int startyear=0;
    int endyear=0;
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


    public void setMainapp(Mainapp mainapp){
        this.mainapp=mainapp;
    }

    public void setEventInfo(EventListNode pre){
        if (pre==null){
            EventName.setText("");
            EventDetail.setText("");
        }
        else
        {
            this.event=pre;
            this.startyear=pre.getStartYear();
            this.startmonth=pre.getStartMonth();
            this.startday=pre.getStartDay();
            this.endyear=pre.getEndYear();
            this.endmonth=pre.getEndMonth();
            this.endday=pre.getEndDay();
            EventName.setText(pre.getName());
            if (startday!=0 && startmonth!=0 && startday!=0){
                StartTime.setValue(LocalDate.of(startyear,startmonth,startday));
            }
            else
                StartTime.setValue(LocalDate.now());
            if (endyear!=0 && endmonth!=0 && endday!=0){
                EndTime.setValue(LocalDate.of(endyear,endmonth,endday));
            }
            else
                EndTime.setValue(LocalDate.now());
         //   StartTime.setText(pre.getBeginTime());
         //   EndTime.setText(pre.getEndTime());
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
        if (EndTime.getValue().isBefore(StartTime.getValue())){
            errorMessage+="事件起始时间不合法\n";
        }
        if (urgency==0){
            errorMessage+="无效紧急度\n";
        }
   /*     if (EventDetail.getText()==null||EventDetail.getText().length()==0){
            errorMessage+="详细信息未填写\n";
        }*/
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            mainapp.ShowErrorPage(errorMessage);
            return false;
        }
    }

    @FXML
    private void handleOK(){
        if (isinputvalid()){
            event.setName(EventName.getText());
      //      event.setBeginTime(StartTime.getText());
        //    event.setEndTime(EndTime.getText());
            event.setDetail(EventDetail.getText());
            event.setStatus(status);
            event.setUrgency(urgency);
            event.setStartYear(StartTime.getValue().getYear());
            event.setStartMonth(StartTime.getValue().getMonthValue());
            event.setStartDay(StartTime.getValue().getDayOfMonth());
            event.setEndYear(EndTime.getValue().getYear());
            event.setEndMonth(EndTime.getValue().getMonthValue());
            event.setEndDay(EndTime.getValue().getDayOfMonth());
            System.out.println(StartTime.getValue().getDayOfMonth());
            okclicked=true;
            dialogStage.close();
        }

    }


}
