package ucf.assignments.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ucf.assignments.utils.SceneUtils;

/**
 * Main Controller for the MainPane.fxml
 */
public class MainSceneController {
    @FXML public ScrollPane todoScroll;
    @FXML public VBox scrollContent;

    @FXML
    public void initialize() {
        System.out.println("Test");
    }

    @FXML
    public void addNew() {
        addNewToDoList("test");
    }

    /**
     * Adds a new to-do list
     * @param title title of to-do list.
     */
    public void addNewToDoList(String title) {
        scrollContent.getChildren().add(SceneUtils.getInstance().getNewToDoListInstance());
        //todoScroll.getChildrenUnmodifiable().add(SceneUtils.getInstance().getNewToDoListInstance());
    }
}
