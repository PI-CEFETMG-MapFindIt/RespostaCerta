package br.cefetmg.respostacerta.JFX;
        
import javafx.scene.control.ChoiceBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class MainController {
    
    //Elemento da interface
    public ChoiceBox<String> choiceBox;
 
    //Classe principal da aplicação
    public RespostaCertaJFX main;
    
    public MainController() {
        choiceBox = new ChoiceBox<>();  
    }
    
    //Chamado automaicamente - pseudo construtor da interface
    public void initialize() {
        choiceBox.getItems().addAll("Questão", "Módulo", "Disciplina");
        choiceBox.setValue("Questão");
    }
    
    //Abre a janela de Login
    public void entrar() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");
        window.setResizable(false);
        window.setScene(new Scene(main.load("Entrar.fxml"), 500, 200));
        window.showAndWait();
    }
    
    public void cadastrar() {
        
    }
    
    public void pesquisar() {
        
    }
    
    public void setMain(RespostaCertaJFX main) {
        this.main = main;
    }
    
}
