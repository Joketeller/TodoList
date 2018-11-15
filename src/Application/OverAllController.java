package Application;

import Application.Mainapp;
import Model.CategoryName;
import Model.TabList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OverAllController {
    @FXML
    ListView<CategoryName> CategoryList;
    @FXML
    ListView<TabList> EventList;

    private Mainapp mainapp;

    public void Setmainapp(Mainapp mainapp){
        this.mainapp=mainapp;
        CategoryList.setItems(mainapp.getCategory());
    }


}
