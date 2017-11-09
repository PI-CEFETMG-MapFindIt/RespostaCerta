package br.cefetmg.respostacerta.JFX.controllers;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.server.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.server.OpenAnswerManagement;
import br.cefetmg.respostaCerta.model.server.UserManagement;
import br.cefetmg.respostacerta.JFX.RespostaCertaJFX;
import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
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
        if(!email.getText().equals(confEmail.getText())){
            return;
        }
        if(!senha.getText().equals(senhaConf.getText())){
            return;
        }
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2222);
            UserManagement  user = (UserManagement)registry.lookup("UserManagement");
            User us = new User();
            us.setLoginUsuario(email.getText());
            us.setNomeUsuario(primNome.getText()+" " + ultNome.getText());
            String s = senha.getText();
            MessageDigest m;
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            String senhaMd5 = new BigInteger(1,m.digest()).toString(16);
            us.setSenhaUsuario(senhaMd5);
            if(idtProf.isSelected()){
                us.setIdtUsuario('E');
            }else{
                us.setIdtUsuario('A');
            }
            user.registerUser(us);
        } catch (RemoteException | NotBoundException | NoSuchAlgorithmException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
            
    }
}
