package Application;

import DataType.CategoryInfo;
import javafx.stage.Stage;

public class CategoryAddDialogController {

    Stage stage = new Stage();
    CategoryInfo cate=new CategoryInfo();
    public void setDialogStage(Stage stage)
    {
        this.stage=stage;
    }
    public void setCategory(CategoryInfo cate)
    {
        this.cate=cate;
    }
}
