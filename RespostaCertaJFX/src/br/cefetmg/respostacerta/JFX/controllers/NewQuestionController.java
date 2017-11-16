/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostacerta.JFX.controllers;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.server.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.server.ModuleManagement;
import br.cefetmg.respostaCerta.model.server.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.server.SubjectManagement;
import br.cefetmg.respostacerta.JFX.RespostaCertaJFX;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    //Textos das opções objetivas
    public TextArea txt1;
    public TextArea txt2;
    public TextArea txt3;
    public TextArea txt4;
    public TextArea txt5;
    
    public RespostaCertaJFX main;
    
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
        txt1 = new TextArea();
        RadioButton alt2 = new RadioButton();
        alt2.setToggleGroup(alt);
        txt2 = new TextArea();
        RadioButton alt3 = new RadioButton();
        alt3.setToggleGroup(alt);
        txt3 = new TextArea();
        RadioButton alt4 = new RadioButton();
        alt4.setToggleGroup(alt);
        txt4 = new TextArea();
        RadioButton alt5 = new RadioButton();
        alt5.setToggleGroup(alt);
        txt5 = new TextArea();
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
        if(idtDiscursiva.isSelected()){
            Question q = new Question();
            q.setCriador(main.getUsuarioLogado());
            q.setDataCriacao(LocalDate.now());
            q.setEnunciadoQuestao(enunciado.getText());
            q.setIdtQuestao(true);
            Module m = checkModule(modulo.getText());
            if(m==null){
               Registry registry;
               try {
                    registry = LocateRegistry.getRegistry("localhost", 2222);
                    SubjectManagement subj = (SubjectManagement)registry.lookup("SubjectManagement");
                    ModuleManagement mod = (ModuleManagement)registry.lookup("ModuleManagement");
                    Subject s = checkSubject(disciplina.getText());
                    if(s==null){
                        s.setNomeDominio(disciplina.getText());
                        subj.registerSubject(s);
                    }
                    m.setDominio(s);
                    m.setNomeModulo(modulo.getText());
                    mod.registerModule(m);
               } catch (RemoteException | NotBoundException ex) {
                    System.out.println("Erro: " + ex.getMessage());
               } catch (BusinessException | PersistenceException ex) {
                    System.out.println("Erro: " + ex.getMessage());
                }
            }
            q.setModulo(m);
            q.setTituloQuestao(titulo.getText());
            if(idtFacil.isSelected()){
                q.setIdtDificuldade('F');
            }
            if(idtModerada.isSelected()){
                q.setIdtDificuldade('M');
            }
            if(idtDificil.isSelected()){
                q.setIdtDificuldade('D');
            }
            if(idtDesafio.isSelected()){
                q.setIdtDificuldade('X');
            }
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry("localhost", 2222);
                OpenQuestionManagement opQ = (OpenQuestionManagement)registry.lookup("OpenQuestionManagement");
                opQ.registerQuestion(q);
            } catch (RemoteException | NotBoundException ex) {
                System.out.println("Erro: " + ex.getMessage());
            } catch (BusinessException | PersistenceException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
            
        }else{
            ClosedQuestion q = new ClosedQuestion();
            q.setCriador(main.getUsuarioLogado());
            q.setDataCriacao(LocalDate.now());
            q.setEnunciadoQuestao(enunciado.getText());
            q.setIdtQuestao(false);
            Module m = checkModule(modulo.getText());
            if(m==null){
               Registry registry;
               try {
                    registry = LocateRegistry.getRegistry("localhost", 2222);
                    SubjectManagement subj = (SubjectManagement)registry.lookup("SubjectManagement");
                    ModuleManagement mod = (ModuleManagement)registry.lookup("ModuleManagement");
                    Subject s = checkSubject(disciplina.getText());
                    if(s==null){
                        s.setNomeDominio(disciplina.getText());
                        subj.registerSubject(s);
                    }
                    m.setDominio(s);
                    m.setNomeModulo(modulo.getText());
                    mod.registerModule(m);
               } catch (RemoteException | NotBoundException ex) {
                    System.out.println("Erro: " + ex.getMessage());
               } catch (BusinessException | PersistenceException ex) {
                    System.out.println("Erro: " + ex.getMessage());
                }
            }
            q.setModulo(m);
            q.setTituloQuestao(titulo.getText());
            if(idtFacil.isSelected()){
                q.setIdtDificuldade('F');
            }
            if(idtModerada.isSelected()){
                q.setIdtDificuldade('M');
            }
            if(idtDificil.isSelected()){
                q.setIdtDificuldade('D');
            }
            if(idtDesafio.isSelected()){
                q.setIdtDificuldade('X');
            }
            q.setCorreta(opObjetiva);
            q.setAlt1(txt1.getText());
            q.setAlt2(txt2.getText());
            q.setAlt3(txt3.getText());
            q.setAlt4(txt4.getText());
            q.setAlt5(txt5.getText());
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry("localhost", 2222);
                ClosedQuestionManagement clQ = (ClosedQuestionManagement)registry.lookup("ClosedQuestionManagement");
                clQ.registerQuestion(q);
            } catch (RemoteException | NotBoundException ex) {
                System.out.println("Erro: " + ex.getMessage());
            } catch (BusinessException | PersistenceException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }
    
    public Module checkModule(String module){
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 2222);
            ModuleManagement mod = (ModuleManagement)registry.lookup("ModuleManagement");
            List<Module> lis = mod.getAllModules();
            for(Module m:lis){
                if(m.getNomeModulo().equals(module)){
                    return m;
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return null;
    }
    
    public Subject checkSubject(String sub){
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 2222);
            SubjectManagement subj = (SubjectManagement)registry.lookup("SubjectManagement");
            List<Subject> lis = subj.getAllSubjects();
            for(Subject s:lis){
                if(s.getNomeDominio().equals(sub)){
                    return s;
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return null;
    }
}
