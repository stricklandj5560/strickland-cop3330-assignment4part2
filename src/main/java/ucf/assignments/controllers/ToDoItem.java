package ucf.assignments.controllers;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ucf.assignments.models.ToDoListModel;
import ucf.assignments.models.VariableData;
import ucf.assignments.utils.SceneUtils;

import javax.swing.*;


public class ToDoItem {
    @FXML public TextField titleBox;
    @FXML public ListView<ToDoListModel.ToDoCell> toDoItemList;
    @FXML public CheckBox showCompleted;

    private ToDoListModel convertedToDoItemList;
    private ToDoItem currentInstance;

    @FXML
    public void initialize() {
        currentInstance = this;
        // save instance of to-do list
        VariableData.getInstance().storeToDoListInstance(currentInstance);
        titleBox.setText("Untitled " + VariableData.getInstance().getToDoListInstances().indexOf(currentInstance));
        convertedToDoItemList = new ToDoListModel(toDoItemList);
        convertedToDoItemList.addCell();
        showCompleted.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                convertedToDoItemList.showOnlyNotCompletedItems(newValue);
            }
        });
    }

    @FXML
    public void removeSelectedCell() {
        System.out.println("Call" + toDoItemList.getSelectionModel().getSelectedIndex() );
        convertedToDoItemList.remove(toDoItemList.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void deleteList() {
        // confirm deletion
        selfDestruct();
    }

    /**
     * Remove list
     */
    public void selfDestruct() {
        VariableData.getInstance().getToDoListInstances().remove(currentInstance);
        SceneUtils sInstance = SceneUtils.getInstance();
        // remove child from scrollview content
        sInstance.removeNodeFromParent(sInstance.getVBoxParent(titleBox), sInstance.getMainSceneControllerInstance().scrollContent);
    }

    @FXML
    public void addCell() {
        convertedToDoItemList.addCell();
    }

    public String getTitle() {
        return titleBox.getText();
    }

    public void setTitle(String title) {
        titleBox.setText(title);
    }

}
