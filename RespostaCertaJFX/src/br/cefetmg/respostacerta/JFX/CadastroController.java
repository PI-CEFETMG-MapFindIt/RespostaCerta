package br.cefetmg.respostacerta.JFX;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class CadastroController {
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
    
    public CadastroController() {
    }
    
    @FXML
    public void initialize() {
    }
}
