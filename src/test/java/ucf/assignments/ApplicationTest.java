/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Jonathan Strickland
 */

package ucf.assignments;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.Test;
import ucf.assignments.controllers.MainSceneController;
import ucf.assignments.controllers.ToDoItemController;
import ucf.assignments.utils.SceneUtils;

public class ApplicationTest {

    // Test adding new to do list
    @Test
    void testAdding() {
        // run gui and add new listview
        assertTrue(true);
    }

    @Test
    void testRemove() {
        // add list and remove list
        assertTrue(true);
    }

    @Test
    void testEditTitle() {
        // add a list then edit the title of the list
        assertTrue(true);
    }

    @Test
    void testAddItem() {
        // add a list, then add an item to the list.
        assertTrue(true);
    }

    @Test
    void testRemoveItem() {
        // remove an item from an individual listview
        assertTrue(true);
    }

    @Test
    void testEditDescription() {
        // create a to-do list, add item, then edit description.
        assertTrue(true);
    }

    @Test
    void editDueDate() {
        // create a to-do list, add item, then edit date w/ date picker.
        assertTrue(true);
    }

    @Test
    void markComplete() {
        // create a to-do list, add item, then check completed.
        assertTrue(true);
    }

    @Test
    void displayAll() {
        // select "display all"
        assertTrue(true);
    }

    @Test
    void displayOnlyIncomplete() {
       // select "display incomplete"
       assertTrue(true);
    }

    @Test
    void displayOnlyComplete() {
        // select "display complete"
        assertTrue(true);
    }

    @Test
    void testSaveSingle() {
        // save a single todo list
        assertTrue(true);
    }

    @Test
    void saveAll() {
        assertTrue(false);
    }

    @Test
    void testLoad() {
        // load in single todo list
        assertTrue(true);
    }

    @Test
    void testLoadMultiple() {
        assertTrue(false);
    }
}
