package br.cefetmg.respostacerta.JFX;
        
import javafx.scene.control.ChoiceBox;

public class MainController {
    
    public ChoiceBox<String> choiceBox;
    
    public RespostaCertaJFX mainView;
    
    public MainController() {
        choiceBox = new ChoiceBox<>();
    }
    
    public void initialize() {
        choiceBox.getItems().addAll("Questão", "Módulo", "Disciplina");
        choiceBox.setValue("Questão");
    }
    
    public void entrar() {
    
    }
    
    public void cadastrar() {
        
    }
}
