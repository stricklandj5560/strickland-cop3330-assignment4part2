/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Jonathan Strickland
 */

package ucf.assignments.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ucf.assignments.models.VariableData;
import ucf.assignments.utils.SaveUtils;
import ucf.assignments.utils.SceneUtils;

import java.io.File;

/**
 * Main Controller for the MainPane.fxml
 */
public class MainSceneController {
    @FXML public ScrollPane todoScroll;
    @FXML public VBox scrollContent;

    @FXML
    public void initialize() {
        SceneUtils.getInstance().setMainSceneControllerInstance(this);
    }

    /**
     * adds a new list
     */
    @FXML
    public void addNew() {
        addNewToDoList();
    }

    /**
     * Loads a list
     */
    @FXML
    public void loadList() {
        try {
            FileChooser fc = new FileChooser();
            File result = fc.showOpenDialog(new Stage());
            if (result == null)
                return;
            addNewToDoList();
            // load w/ latest instance of todolist
            SaveUtils.getInstance().loadList(result, VariableData.getInstance().getToDoListInstances()
                    .get(VariableData.getInstance().getToDoListInstances().size() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new to-do list
     */
    public void addNewToDoList() {
        scrollContent.getChildren().add(SceneUtils.getInstance().getNewToDoListInstance());
    }
}
