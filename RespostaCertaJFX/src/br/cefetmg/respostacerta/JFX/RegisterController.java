package br.cefetmg.respostacerta.JFX;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RegisterController {
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
    
    public RegisterController() {
    }
    
    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        idtAluno.setToggleGroup(group);
        idtAluno.setSelected(true);

        idtProf.setToggleGroup(group);
    }
    
    @FXML
    public void cadastrar(){
        
    }
}
