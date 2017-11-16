 package br.cefetmg.respostacerta.JFX.controllers;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostacerta.JFX.RespostaCertaJFX;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class MyQuestionController {
    
    public RespostaCertaJFX main;
    
    @FXML
    public FlowPane panel;
        
    public void initialize() {
        ObservableList<Node> lista = panel.getChildren();
        List<Question> minhasQuestoes = null; //TODO
        for(Question q:minhasQuestoes){
            FlowPane pane = new FlowPane();
            Label nome = new Label(q.getTituloQuestao());
            Button editar = new Button("Editar");
            Button deletar = new Button("Deletar");
            pane.getChildren().add(nome);
            pane.getChildren().add(editar);
            pane.getChildren().add(deletar);
            lista.add(pane);
        }          
    }
}
