package shufflemyfiles.ui.main;

import java.io.File;
import java.io.IOException;
import shufflemyfiles.filesrandomize.FilesRandomize;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shufflemyfiles.ShuffleMyFiles;

/**
 *
 * @author Goran
 */
public class MainController implements Initializable {

    private Stage primaryStage;

    private FilesRandomize filesRandomize;

    private File currentDirectory;

    @FXML
    private Label dirPathLabel;
    @FXML
    private Label messageLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filesRandomize = new FilesRandomize();
        primaryStage = ShuffleMyFiles.getStage();
        currentDirectory = new File(System.getProperty("user.home"));
        updatePathText(loadFiles());
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    @FXML
    private void shuffleMyFiles() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.NO, ButtonType.YES);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.setTitle("Question");
        Optional<ButtonType> resultButton = alert.showAndWait();
        if (resultButton.get() == ButtonType.YES) {
            try {
                loadFiles();
                filesRandomize.generateRandomNumerList();
                filesRandomize.mergeLists();
                showMessage(AlertType.INFORMATION, filesRandomize.getNumberOfFIles() + " files were shuffeled successfully.");
            } catch (IOException ex) {
                showMessage(AlertType.ERROR, "Error: \n" + ex.getMessage());
            }
        }
    }

    @FXML
    private void changeDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(currentDirectory);
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        if (selectedDirectory != null) {
            currentDirectory = selectedDirectory;
            updatePathText(loadFiles());
        }
    }

    private void updatePathText(int numberOfFiles) {
        dirPathLabel.setText(currentDirectory.getAbsolutePath());
        messageLabel.setText("Files: " + numberOfFiles);
    }

    private int loadFiles() {
        return filesRandomize.loadFiles(currentDirectory);
    }

    private void showMessage(AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}
