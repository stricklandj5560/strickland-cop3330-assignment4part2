package ucf.assignments.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ucf.assignments.models.ToDoListModel;
import ucf.assignments.models.VariableData;
import ucf.assignments.utils.SceneUtils;


public class ToDoItemController {
    @FXML public TextField titleBox;
    @FXML public ListView<ToDoListModel.ToDoCell> toDoItemList;
    @FXML public RadioButton showCompleted;
    @FXML public RadioButton showAll;
    @FXML public RadioButton showIncompleted;

    private ToDoListModel convertedToDoItemList;
    private ToDoItemController currentInstance;

    @FXML
    public void initialize() {
        currentInstance = this;
        // save instance of to-do list
        VariableData.getInstance().storeToDoListInstance(currentInstance);
        titleBox.setText("Untitled " + VariableData.getInstance().getToDoListInstances().indexOf(currentInstance));
        convertedToDoItemList = new ToDoListModel(toDoItemList);
        convertedToDoItemList.addCell();
        ToggleGroup selectionGroup = new ToggleGroup();
        selectionGroup.getToggles().addAll(showCompleted, showAll, showIncompleted);
        selectionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (showAll.isSelected()) {
                    convertedToDoItemList.showItems(0);
                }
                if (showIncompleted.isSelected()) {
                    convertedToDoItemList.showItems(1);
                }
                if (showCompleted.isSelected()) {
                    convertedToDoItemList.showItems(2);
                }
            }
        });
    }

    @FXML
    public void removeSelectedCell() {
        showAll.setSelected(true);
        convertedToDoItemList.remove(toDoItemList.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void saveList() {

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
        // select show all
        showAll.setSelected(true);
        convertedToDoItemList.addCell();
    }

    public String getTitle() {
        return titleBox.getText();
    }

    public void setTitle(String title) {
        titleBox.setText(title);
    }

}
