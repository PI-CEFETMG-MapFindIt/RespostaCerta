package br.cefetmg.respostacerta.JFX;
        
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    
    public ChoiceBox<String> choiceBox;
    private final FXMLLoader loader;
    public RespostaCertaJFX mainView;
    
    public MainController() {
        loader = new FXMLLoader();
        choiceBox = new ChoiceBox<>();  
    }
    
    public void initialize() {
        choiceBox.getItems().addAll("Questão", "Módulo", "Disciplina");
        choiceBox.setValue("Questão");
    }
    
    public void entrar() throws Exception {
        loader.setLocation(getClass().getResource("Entrar.fxml"));
        Parent root = loader.load();
   
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");
        window.setResizable(false);

        window.setScene(new Scene(root, 500, 200));
        window.showAndWait();
    }
    
    public void cadastrar() {
        
    }
}
