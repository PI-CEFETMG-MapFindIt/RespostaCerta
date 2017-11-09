package br.cefetmg.respostacerta.JFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RespostaCertaJFX extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Resposta Certa");
        primaryStage.setScene(new Scene(root, 1270, 785));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
