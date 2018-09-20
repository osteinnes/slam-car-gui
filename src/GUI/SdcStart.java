package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Start self driving car GUI.
 *
 * @author Eirik G. Gustafsson
 * @version 20.09.2018
 */
public class SdcStart extends Application {
    // Scene title.
    public static String sceneTitle = "Atonomous car 3000";

    // Sets and starts the scene.
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/Login.fxml"));
        primaryStage.setTitle(sceneTitle);
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}