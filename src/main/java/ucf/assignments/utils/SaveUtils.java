/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Jonathan Strickland
 */

package ucf.assignments.utils;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ucf.assignments.controllers.ToDoItemController;
import ucf.assignments.models.ToDoListModel;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to handle Saving/Loading lists.
 */
public class SaveUtils {
    private static SaveUtils SINGLETON;
    private Path outputDir;
    private final String categoryRegex = "%#%&%#%";
    private final String singleLineRegex = "%#%@%#%";

    public static SaveUtils getInstance() {
        if (SINGLETON == null)
            SINGLETON = new SaveUtils();
        return SINGLETON;
    }

    public SaveUtils() {

    }

    /**
     * Prompts the user to choose and output directory
     * @return true if directory is successfully chosen.
     */
    public boolean promptToSaveOutput() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Output Directory");
            File dir = directoryChooser.showDialog(new Stage());
            if (dir == null)
                return false;
            if (!dir.canWrite())
                return false;
            // make an assignment4 folder
            outputDir = dir.toPath();
            outputDir.resolve("Assignment4").toFile().mkdir();
            outputDir = outputDir.resolve("Assignment4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Loads a to-do list from a file
     * @param file
     * @param controller
     */
    public void loadList(File file, ToDoItemController controller) {
        if (file == null)
            return;
        if (!file.exists())
            return;
        if (!file.getName().contains(".jon"))
            return;
        try {
            Scanner scan = new Scanner(file);
            String line = "";
            while (scan.hasNextLine()) {
                line += scan.nextLine();
            }
            String[] catSplit = line.split(categoryRegex);
            // first index is title
            controller.setTitle(catSplit[0]);
            // add each item
            for (int i = 1; i < catSplit.length; i++) {
                String[] temp = catSplit[i].split(singleLineRegex);
                // check for incorrect format
                if (temp.length != 3)
                    continue;
                // add cell
                controller.convertedToDoItemList.loadCell(temp[0], temp[1], temp[2].equals("true"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a list to the output directory
     * @param controller list controller
     */
    public void saveList(ToDoItemController controller) {
        if (controller == null)
            return;
        // prompt the user to set the output directory
        if (outputDir == null) {
            // check to see if we successfully set the directory
            if(!promptToSaveOutput())
                return;
        }

        try {
            // output file as title.
            String outputName = controller.getTitle() + ".jon";
            File f = outputDir.resolve(outputName).toFile();
            if (f.exists())
                f.delete();
            f.createNewFile();
            ArrayList<String> toDoItems = new ArrayList<>();
            toDoItems.add(controller.getTitle());
            // add to-do cell info
            for (ToDoListModel.ToDoCell cell : controller.toDoItemList.getItems()) {
                toDoItems.add(cell.getTextContent() + singleLineRegex + cell.getStrDate() + singleLineRegex
                        + (cell.isCompleted() ? "true" : "false"));
            }
            // write file
            FileWriter fw = new FileWriter(f);
            PrintWriter write = new PrintWriter(fw);
            for (int i = 0; i < toDoItems.size(); i++) {
                // if we're at the last index, print w/o cat. regex
                if (i == toDoItems.size() - 1)
                    write.println(toDoItems.get(i));
                // print w/ cat regex
                else
                    write.println(toDoItems.get(i) + categoryRegex);
            }
            write.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
