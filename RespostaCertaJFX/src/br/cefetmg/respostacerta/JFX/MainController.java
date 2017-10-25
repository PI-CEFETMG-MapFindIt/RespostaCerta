package br.cefetmg.respostacerta.JFX;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class MainController {
    
    public ChoiceBox<String> choiceBox;
    
    public MainController() {
        choiceBox = new ChoiceBox<>();
    }
    
    @FXML
    public void initialize() {
        choiceBox.getItems().addAll("Questão", "Módulo", "Disciplina");
        choiceBox.setValue("Questão");
    }
}
