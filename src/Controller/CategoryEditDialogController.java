package Controller;

import Model.CategoryListNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryEditDialogController implements Initializable {

    Stage dialogStage = new Stage();
    @FXML
    TextField CategoryName;

    Mainapp mainapp=null;

    public void setMainapp(Mainapp mainapp){
        this.mainapp=mainapp;
    }

    boolean okclicked=false;
 //   CategoryInfo cate=new CategoryInfo();
    public void setDialogStage(Stage stage)
    {
        this.dialogStage=stage;
    }

    private CategoryListNode category;

    public void setcategory(CategoryListNode now){
        if (now!=null) {
            this.category = now;
            CategoryName.setText(now.getCategoryName());
        }
    }

    public boolean isokclicked(){
        return okclicked;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private boolean isinputvalid(){
        String errorMessage="";
        if (CategoryName.getText()==null || CategoryName.getText().length()==0){
            errorMessage+="分类名不能为空！";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
//            Dialogs.create()
//                    .title("无效输入")
//                    .masthead("请修正错误输入")
//                    .message(errorMessage)
//                    .showError();
            mainapp.ShowErrorPage(errorMessage);
            return false;
        }
    }

    @FXML
            private void handleOK(){
                if (isinputvalid()){
                    category.setCategoryName(CategoryName.getText());
                    okclicked=true;
                    dialogStage.close();
        }
    }
}
