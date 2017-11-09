/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostacerta.JFX;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author Pós Graduação
 */
public class NewQuestionController {
    @FXML
    public TextField primNome;
    @FXML
    public TextField ultNome;
    @FXML
    public TextField email;
    @FXML
    public TextField confEmail;
    @FXML
    public PasswordField senha;
    @FXML
    public PasswordField senhaConf;
    @FXML
    public DatePicker dataNasc;
    @FXML
    public RadioButton idtProf;
    @FXML
    public RadioButton idtAluno;
    
    public RespostaCertaJFX mainView;
    
    public NewQuestionController() {
        
    }
    
    @FXML
    public void initialize() {
    }
    
    @FXML
    public void cadastrar(){
        
    }
}
