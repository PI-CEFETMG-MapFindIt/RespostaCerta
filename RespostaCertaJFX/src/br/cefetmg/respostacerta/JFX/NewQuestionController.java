/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostacerta.JFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    
    public RespostaCertaJFX mainView;
    
    public NewQuestionController() {
        
    }
    
    @FXML
    public void objetiva(){
        ObservableList<Node> children = objPane.getChildren();
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
        children.add(alt1);
        children.add(txt1);
        children.add(alt2);
        children.add(txt2);
        children.add(alt3);
        children.add(txt3);
        children.add(alt4);
        children.add(txt4);
        children.add(alt5);
        children.add(txt5);
    }
   
    @FXML
    public void discursiva(){
        ObservableList<Node> children = objPane.getChildren();
        children.clear();
    }
    
    @FXML
    public void initialize() {
        //Define grupos dos radio's
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
