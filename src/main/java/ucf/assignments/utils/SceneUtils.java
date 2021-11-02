package ucf.assignments.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import ucf.assignments.controllers.MainSceneController;

public class SceneUtils {

    private static SceneUtils SINGLETON;
    private MainSceneController mainSceneControllerInstance;

    public static SceneUtils getInstance() {
        if (SINGLETON == null)
            SINGLETON = new SceneUtils();
        return SINGLETON;
    }

    public void setMainSceneControllerInstance(MainSceneController mainSceneControllerInstance) {
        this.mainSceneControllerInstance = mainSceneControllerInstance;
    }

    public MainSceneController getMainSceneControllerInstance() {
        return this.mainSceneControllerInstance;
    }

    /**
     * Basic method to remove a node from a parent
     * @param node node to remove
     * @param p parent to remove node from
     */
    public void removeNodeFromParent(Node node, VBox p) {
        p.getChildren().removeIf(child -> child == node);
    }

    /**
     * Gets the ultimate vbox parent of a child
     * @param node parent
     * @return parent
     */
    public Parent getVBoxParent(Node node) {
        if (node instanceof VBox)
            return (Parent) node;
        if (node.getParent() != null)
            return getVBoxParent(node.getParent());
        // we never should reach this case.
        return null;
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
