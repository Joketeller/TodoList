package Controller;

import Utils.MyTimer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class CountdownController {

    Stage dialogStage = new Stage();
    Mainapp mainapp=null;
    MyTimer timer=new MyTimer();
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainapp(Mainapp mainapp){
        this.mainapp=mainapp;
    }
    public
    @FXML
    TextField Hour;
    @FXML
    TextField Min;
    @FXML
    TextField Sec;

    public void setTimerInfo(MyTimer timer)
    {
    /* timer.getStartHour(Hour.getText());
     timer.getStartMin(valueOf(Min.getText()));
     timer.getStartSec(Sec.getText());*/
//  System.out.println(Hour.getText());
        //  System.out.println(Min.getText());
        //System.out.println(Sec.getText());
        dialogStage.close();
    }


    @FXML
    boolean okclicked=false;

    public boolean isokclicked(){
        return okclicked;
    }

    @FXML
    private void handleCancelTimer() {
        dialogStage.close();
    }

    // @FXML
    @FXML
    private void handleOKTimer(){
        okclicked=true;
        dialogStage.close();
    }

    private  boolean clockState = false;//时钟状态
    //格式化日期、时间
    private static SimpleDateFormat timeDF = new SimpleDateFormat("HH:mm:ss");

    //开启时钟,start按钮的onAction事件
    public void startClock() {
        clockState = true;
//        clock();
    }
    //关闭时钟,close按钮的onAction事件
    public void closeClock() {
        clockState = false;
      //  clock();
    }



}
