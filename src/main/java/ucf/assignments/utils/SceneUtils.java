package ucf.assignments.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SceneUtils {

    private static SceneUtils SINGLETON;

    public static SceneUtils getInstance() {
        if (SINGLETON == null)
            SINGLETON = new SceneUtils();
        return SINGLETON;
    }

    /**
     * Gets a new instance of ToDoModel.fxml
     * @return
     */
    public Parent getNewToDoListInstance() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ToDoModel.fxml"));
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
