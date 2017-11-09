package br.cefetmg.respostacerta.JFX;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RespostaCertaJFX extends Application {
    //Loader para carregar as telas em FXML
    private FXMLLoader loader;
    
    //Lança o aplicativo
    public static void main(String[] args) {
        launch(args);
    }   
    
    //Pseudo Construtor
    @Override
    public void start(Stage primaryStage) {
        loader = new FXMLLoader();
        home(primaryStage);
    }
    
    //Tela principal da aplicação
    private void home(Stage primaryStage){
        //Seta as configurações da tela e carrega a interface FXML
        primaryStage.setTitle("Resposta Certa");
        primaryStage.setScene(new Scene(load("Main.fxml"), 1270, 785));
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    //Retorna a interface FXML carregada
    public Parent load(String url){
        try {
            loader.setLocation(getClass().getResource(url));
            //Adiciona o main ao controller ativo 
            MainController main = loader.getController();
            main.setMain(this);
            return loader.load();
        } catch (Exception ex) {
            //Fecha a aplicação caso ocorra erro no Loader
            System.exit(0);
            return null;
        }
    }
}
