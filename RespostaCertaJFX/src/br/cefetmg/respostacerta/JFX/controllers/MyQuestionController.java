/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author aluno
 */
public class MyQuestionController {
    public RespostaCertaJFX mainView;
    
    @FXML
    public FlowPane panel;
    
    public MyQuestionController() {
        
    }
    
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
