/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxtester;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Govinda
 */
public class Main extends Application{
    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Allows global access for primary stage
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }
    public static void main(String[] args){
        launch(args);
    }
}
