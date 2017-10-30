package br.cefetmg.respostacerta.JFX;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RespostaCertaJFX extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        showHome(primaryStage);
    }
    
    public void showHome(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 1270, 785));
        primaryStage.setTitle("Resposta Certa");
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
