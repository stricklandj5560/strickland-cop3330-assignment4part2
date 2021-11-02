package ucf.assignments.models;

import ucf.assignments.controllers.ToDoItemController;

import java.util.ArrayList;

/**
 * Class to store varying data.
 */
public class VariableData {
    private ArrayList<ToDoItemController> toDoItemInstances;
    private static VariableData SINGLETON;

    /**
     * Initializer for variable data... we'll initialize all of our variables here.
     */
    public VariableData() {
        toDoItemInstances = new ArrayList<>();
    }
    /**
     * Preserves one instance of VariableData
     * @return instance of VariableData
     */
    public static VariableData getInstance() {
        if (SINGLETON == null)
            SINGLETON = new VariableData();
        return SINGLETON;
    }

    public void clearToDoAllInstances() {
        toDoItemInstances.clear();
    }

    public void removeToDoListInstance(ToDoItemController instance) {
        toDoItemInstances.remove(instance);
    }

    public void storeToDoListInstance(ToDoItemController instance) {
        toDoItemInstances.add(instance);
    }

    public ArrayList<ToDoItemController> getToDoListInstances() {
        return toDoItemInstances;
    }
}
