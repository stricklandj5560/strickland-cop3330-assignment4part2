package ucf.assignments.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Observable;

public class ToDoListModel extends ListView {
    private ListView<ToDoCell> list;
    private ObservableList<ToDoCell> cachedList;

    public ToDoListModel(ListView<ToDoCell> list) {
        this.list = list;
        // set the cell factory for the custom cells
        list.setCellFactory(new Callback<ListView<ToDoCell>, ListCell<ToDoCell>>() {
            @Override
            public ListCell<ToDoCell> call(ListView<ToDoCell> param) {
                ListCell<ToDoCell> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(ToDoCell item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setGraphic(item.getCellData());
                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });
    }

    public void removeCompletedItems() {
        if (list.getItems() == null)
            return;
        if (list.getItems().size() == 0)
            return;
        list.getItems().removeIf(item -> item.doneCheck.isSelected());
    }
    /**
     * Caches all of the items and then only displays the not completed items
     */
    public void showOnlyNotCompletedItems(boolean doShow) {
        // if we need to show only the not completed items, cache our current list
        if (doShow) {
            cachedList = FXCollections.observableArrayList(list.getItems());
            removeCompletedItems();
            return;
        }
        // set items as cached list
        list.setItems(cachedList);
    }

    public void addCell() {
        ObservableList<ToDoCell> output = list.getItems();
        ToDoCell cell = new ToDoCell();
        cell.setData();
        output.add(cell);
        list.setItems(output);
    }

    public class ToDoCell {
        private final HBox cellWrapper = new HBox();
        private final TextField textItem = new TextField();
        private final CheckBox doneCheck = new CheckBox("Completed");
        private final ToDoCell toDoInstance;
        ToDoCell() {
            toDoInstance = this;
            doneCheck.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // gray out if completed
                    cellWrapper.setOpacity(doneCheck.isSelected() ? 0.5 : 1);
                }
            });
        }
        public void setData() {
            textItem.setPromptText("Enter text here...");
            textItem.setMinWidth(250);
            cellWrapper.getChildren().addAll(textItem, doneCheck);
            cellWrapper.setAlignment(Pos.CENTER_LEFT);
            cellWrapper.setSpacing(5);
        }
        public HBox getCellData() {
            return cellWrapper;
        }
    }
}
