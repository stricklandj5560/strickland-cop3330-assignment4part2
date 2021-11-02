package ucf.assignments.utils;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ucf.assignments.controllers.ToDoItemController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveUtils {
    private static SaveUtils SINGLETON;
    private Path outputDir;

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
            outputDir = dir.toPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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
            outputDir.resolve("Assignment4").toFile().mkdir();
            outputDir = outputDir.resolve("Assignment4");
            // output file as title.
            String outputName = controller.getTitle() + ".yaml";
            File f = outputDir.resolve(outputName).toFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
