/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostacerta.JFX;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Topic;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

/**
 *
 * @author aluno
 */
public class TopicsQuestion {
    public RespostaCertaJFX mainView;
    
    @FXML
    public FlowPane panel;
    
    public TopicsQuestion() {
        
    }
    
    public void initialize() {
        ObservableList<Node> lista = panel.getChildren();
        List<Topic> topicos = null; //TODO
        for(Topic t:topicos){
            FlowPane pane = new FlowPane();
            Label nome = new Label(t.getTxtMensagem());
            nome.setFont(Font.font(12));
            Label autor = new Label(t.getAutor().getNomeUsuario());
            autor.setFont(Font.font(10));
            
            Button deletar = new Button("Deletar");
            pane.getChildren().add(nome);
            pane.getChildren().add(deletar);
            lista.add(pane);
        }
                
    }
    
    @FXML
    public void criarNovoTopico(){
        
    }
}
