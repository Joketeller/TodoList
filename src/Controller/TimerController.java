package Controller;


import Model.CategoryListNode;
import Utils.MyTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class TimerController   implements Initializable {
   Stage dialogStage = new Stage();
    Mainapp mainapp=null;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainapp(Mainapp mainapp){
        this.mainapp=mainapp;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
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
    System.out.println(Hour.getText());
     System.out.println(Min.getText());
     System.out.println(Sec.getText());
 }
}