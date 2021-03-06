/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Jonathan Strickland
 */

package ucf.assignments.models;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;


public class ToDoListModel extends ListView {
    private ListView<ToDoCell> list;
    private ObservableList<ToDoCell> cachedList;

    /**
     * Custom list view to display to-do details
     * @param list listview to convert
     */
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

    /**
     * Removes completed items
     */
    public void removeCompletedItems() {
        if (list.getItems() == null)
            return;
        if (list.getItems().size() == 0)
            return;
        list.getItems().removeIf(item -> item.doneCheck.isSelected());
    }

    /**
     * Removes incompletedItems
     */
    public void removeIncompletedItems() {
        if (list.getItems() == null)
            return;
        if (list.getItems().size() == 0)
            return;
        list.getItems().removeIf(item -> !item.doneCheck.isSelected());
    }
    /**
     * Removes a cell at a specified index
     * @param index index to remove from
     */
    public void remove(int index) {
        if (list == null)
            return;
        if (list.getItems().size() <= index)
            return;
        if (index == -1)
            return;
        list.getItems().remove(index);
    }

    /**
     * Caches all of the items and then only displays the not completed items
     * @param showType 0 = all, 1 = only incompleted, 2 = only completed
     */
    public void showItems(int showType) {
        // set items as cached list
        if (cachedList != null)
            list.setItems(cachedList);
        // if we need to show only the not completed items, cache our current list
        switch (showType) {
            case 0:
                // do nothing, already showing all
                break;
                // show all complete
            case 1:
                cachedList = FXCollections.observableArrayList(list.getItems());
                removeCompletedItems();
                break;
            case 2:
                cachedList = FXCollections.observableArrayList(list.getItems());
                removeIncompletedItems();
                break;
        }

    }

    /**
     * Adds a cell to the stored list
     */
    public void addCell() {
        ObservableList<ToDoCell> output = list.getItems();
        ToDoCell cell = new ToDoCell();
        cell.setData();
        output.add(cell);
        list.setItems(output);
    }

    /**
     * Loads a cell with the following items
     * @param textContent to-do text
     * @param date date to be completed
     * @param isCompleted is the item completed?
     */
    public void loadCell(String textContent, String date, boolean isCompleted) {
        ObservableList<ToDoCell> output = list.getItems();
        ToDoCell cell = new ToDoCell();
        cell.setData();
        cell.setText(textContent);
        cell.setDate(date);
        cell.setDoneCheck(isCompleted);
        output.add(cell);
        list.setItems(output);
    }

    /**
     * Class to model our custom cells.
     */
    public class ToDoCell {

        private final HBox cellWrapper = new HBox();
        private final TextField textItem = new TextField();
        private final CheckBox doneCheck = new CheckBox("Completed");
        private final DatePicker datePicker = new DatePicker();
        private final ToDoCell toDoInstance;

        ToDoCell() {
            toDoInstance = this;
            doneCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    // gray out if completed
                    cellWrapper.setOpacity(newValue ? 0.5 : 1);
                }
            });
        }

        /**
         * Sets the data in the cell
         */
        public void setData() {
            textItem.setPromptText("Enter text here...");
            textItem.setMinWidth(250);
            cellWrapper.getChildren().addAll(textItem, new HBox(new Label("Date Due: ") ,datePicker), doneCheck);
            cellWrapper.setAlignment(Pos.CENTER_LEFT);
            cellWrapper.setSpacing(5);
        }

        /**
         * Returns text content of to do item
         */
        public String getTextContent() {
            return textItem.getText();
        }

        /**
         * @return true if completed
         */
        public boolean isCompleted() {
            return doneCheck.isSelected();
        }

        /**
         * Returns string version of date
         * @return str version of date
         */
        public String getStrDate() {
            return datePicker.getConverter().toString(datePicker.getValue());
        }

        public void setDate(String date) {
            datePicker.getConverter().fromString(date);
        }

        public void setText(String text) {
            textItem.setText(text);
        }

        public void setDoneCheck(boolean checked) {
            doneCheck.setSelected(checked);
        }
        public HBox getCellData() {
            return cellWrapper;
        }
    }
}
