/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostacerta.JFX.controllers;

import br.cefetmg.respostacerta.JFX.RespostaCertaJFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Pós Graduação
 */
public class NewQuestionController {
    @FXML
    public TextField modulo;
    @FXML
    public TextField disciplina;
    @FXML
    public TextField titulo;
    @FXML
    public TextArea enunciado;
    @FXML
    public RadioButton idtFacil;
    @FXML
    public RadioButton idtModerada;
    @FXML
    public RadioButton idtDificil;
    @FXML
    public RadioButton idtDesafio;
    @FXML
    public RadioButton idtDiscursiva;
    @FXML
    public RadioButton idtObjetiva;
    @FXML
    public Pane objPane;
    
    private int opObjetiva=0;
    
    public RespostaCertaJFX mainView;
    
    public NewQuestionController() {
        
    }
    
    @FXML
    public void objetiva(){
        ObservableList<Node> children = objPane.getChildren();
        FlowPane flow1 = new FlowPane();
        FlowPane flow2 = new FlowPane();
        FlowPane flow3 = new FlowPane();
        FlowPane flow4 = new FlowPane();
        FlowPane flow5 = new FlowPane();
        ToggleGroup alt = new ToggleGroup();
        RadioButton alt1 = new RadioButton();
        alt1.setToggleGroup(alt);
        TextArea txt1 = new TextArea();
        RadioButton alt2 = new RadioButton();
        alt2.setToggleGroup(alt);
        TextArea txt2 = new TextArea();
        RadioButton alt3 = new RadioButton();
        alt3.setToggleGroup(alt);
        TextArea txt3 = new TextArea();
        RadioButton alt4 = new RadioButton();
        alt4.setToggleGroup(alt);
        TextArea txt4 = new TextArea();
        RadioButton alt5 = new RadioButton();
        alt5.setToggleGroup(alt);
        TextArea txt5 = new TextArea();
        flow1.getChildren().add(alt1);
        flow1.getChildren().add(txt1);
        flow2.getChildren().add(alt2);
        flow2.getChildren().add(txt2);
        flow3.getChildren().add(alt3);
        flow3.getChildren().add(txt3);
        flow4.getChildren().add(alt4);
        flow4.getChildren().add(txt4);
        flow5.getChildren().add(alt5);
        flow5.getChildren().add(txt5);
        
        children.add(flow1);
        children.add(flow2);
        children.add(flow3);
        children.add(flow4);
        children.add(flow5);
        
        alt1.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    opObjetiva=1;
                }
            }
        });
        
        alt2.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    opObjetiva=2;
                }
            }
        });
        
        alt3.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    opObjetiva=3;
                }
            }
        });
        
        alt4.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    opObjetiva=4;
                }
            }
        });
        
        alt5.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    opObjetiva=5;
                }
            }
        });
    }
   
    @FXML
    public void discursiva(){
        ObservableList<Node> children = objPane.getChildren();
        children.clear();
    }
    
    @FXML
    public void initialize() {
        //Define grupos dos radios
        ToggleGroup diff = new ToggleGroup();
        
        idtFacil.setSelected(true);
        idtFacil.setToggleGroup(diff);
        
        idtModerada.setToggleGroup(diff);
        
        idtDificil.setToggleGroup(diff);
        
        idtDesafio.setToggleGroup(diff);
        
        ToggleGroup tipoQuest = new ToggleGroup();
        
        idtDiscursiva.setSelected(true);
        idtDiscursiva.setToggleGroup(tipoQuest);
        
        idtObjetiva.setToggleGroup(tipoQuest);
        
        //Define eventos
        idtDiscursiva.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    discursiva();
                }
            }
        });
        idtObjetiva.selectedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    objetiva();
                }
            }
        });
    }
    
    @FXML
    public void cadastrar(){
        
    }
}
