package sdv.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Start self driving car gui.
 *
 * @author Eirik G. Gustafsson
 * @version 20.09.2018
 */
public class SdcGuiStart extends Application {
    // Scene title.
    public static String sceneTitle = "SLAM car";

    // Sets and starts the scene.
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("scenes/Login.fxml"));
        primaryStage.setTitle(sceneTitle);
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }
}
