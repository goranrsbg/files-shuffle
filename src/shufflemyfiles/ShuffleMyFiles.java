package shufflemyfiles;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Goran
 */
public class ShuffleMyFiles extends Application {
    
    private static Stage primaryStage;
    
    public static Stage getStage() {
        return primaryStage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("ui/main/main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icons/32x32.png")));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Shuffle My Files");
        primaryStage.setOnHidden((e) -> {
            Platform.exit();
        });
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
