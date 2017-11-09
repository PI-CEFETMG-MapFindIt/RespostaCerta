package br.cefetmg.respostacerta.JFX.controllers;

import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostacerta.JFX.RespostaCertaJFX;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import br.cefetmg.respostaCerta.model.server.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.server.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.server.OpenAnswerManagement;
import br.cefetmg.respostaCerta.model.server.OpenQuestionManagement;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public ListView questRecentes;
    @FXML
    public ListView questVisualizadas;
    @FXML
    public ListView modVisualizados;
    @FXML
    public ListView subVisualizados;
    private RespostaCertaJFX main;
    
    public MainController() {
        choiceBox = new ChoiceBox<>();
    }
    
    //Abre a janela de Login
    public void entrar() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");
        window.setResizable(false);
        window.setScene(new Scene(main.load("Entrar.fxml"), 500, 200));
        window.showAndWait();
    }
    
    public void cadastrar() {
        
    }
    
    public void pesquisar() {
        
    }
    
    @FXML
    public void initialize() {
        choiceBox.getItems().addAll("Questão", "Módulo", "Disciplina");
        choiceBox.setValue("Questão");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2222);
            ClosedAnswerManagement  closedAnswer = (ClosedAnswerManagement)registry.lookup("ClosedAnswerManagement");
            OpenAnswerManagement openAnswer = (OpenAnswerManagement)registry.lookup("OpenAnswerManagement");
            List<ClosedAnswer> respostasFechadas;
            List<OpenAnswer> respostasAbertas;
            respostasFechadas=closedAnswer.getAllAnswers();
            respostasAbertas=openAnswer.getAllAnswers();
            HashMap<Long, Module> modulos = new HashMap();
            HashMap<Long, Subject> disciplinas = new HashMap();
            HashMap<Long, Question> questoes = new HashMap();
            for(ClosedAnswer fechada:respostasFechadas){
                modulos.put(fechada.getQuestao().getModulo().getIdModulo(), fechada.getQuestao().getModulo());
                disciplinas.put(fechada.getQuestao().getModulo().getDominio().getIdDominio(), fechada.getQuestao().getModulo().getDominio());
                questoes.put(fechada.getQuestao().getIdQuestao(), fechada.getQuestao());
            }
            for(OpenAnswer aberta:respostasAbertas){
                modulos.put(aberta.getQuestao().getModulo().getIdModulo(), aberta.getQuestao().getModulo());
                disciplinas.put(aberta.getQuestao().getModulo().getDominio().getIdDominio(), aberta.getQuestao().getModulo().getDominio());
                questoes.put(aberta.getQuestao().getIdQuestao(), aberta.getQuestao());
            }
            Map<Long, Integer> contModulos = new HashMap<>();
            for(Long modulo:modulos.keySet()){
                if(contModulos.containsKey(modulo)){
                    contModulos.replace(modulo, contModulos.get(modulo)+1);
                }else{
                    contModulos.put(modulo, 1);
                }
            }
            //Ordena HashMap pelo value
            contModulos = ordenaPorValor(contModulos);
            Map<Long, Integer> contDisciplinas = new HashMap<>();
            for(Long disciplina:disciplinas.keySet()){
                if(contDisciplinas.containsKey(disciplina)){
                    contDisciplinas.replace(disciplina, contDisciplinas.get(disciplina)+1);
                }else{
                    contDisciplinas.put(disciplina, 1);
                }
            }
            //Ordena o HashMap pelo value
            contDisciplinas = ordenaPorValor(contDisciplinas);
            Map<Long, Integer> contQuestoes = new HashMap<>();
            for(Long questao:questoes.keySet()){
                if(contQuestoes.containsKey(questao)){
                    contQuestoes.replace(questao, contQuestoes.get(questao)+1);
                }else{
                    contQuestoes.put(questao, 1);
                }
            }
            //Ordena HashMap pelo value
            contQuestoes = ordenaPorValor(contQuestoes);
            List<Module> mod = new ArrayList<>();
            Iterator<Long> it = contModulos.keySet().iterator();
            while(it.hasNext()){
                mod.add(modulos.get(it.next()));
            }
            List<Subject> disc = new ArrayList<>();
            it = contDisciplinas.keySet().iterator();
            while(it.hasNext()){
                disc.add(disciplinas.get(it.next()));
            }
            List<Question> quest = new ArrayList<>();
            it = contQuestoes.keySet().iterator();
            while(it.hasNext()){
                quest.add(questoes.get(it.next()));
            }
            if(mod.size()>7)
                mod=mod.subList(0, 7);
            if(disc.size()>7)
                disc=disc.subList(0, 7);
            if(quest.size()>7)
                quest=quest.subList(0, 7);
            OpenQuestionManagement opMan = (OpenQuestionManagement)registry.lookup("OpenQuestionManagement");
            ClosedQuestionManagement clMan = (ClosedQuestionManagement)registry.lookup("ClosedQuestionManagement");
            List<ClosedQuestion> closed = clMan.getAllQuestions();
            List<Question> novasQuestoes = opMan.getAllQuestions();
            if(closed.size()>7){
                closed = closed.subList(0, 7);
            }
            if(novasQuestoes.size()>7){
                novasQuestoes = novasQuestoes.subList(0, 7);
            }
            novasQuestoes.addAll(closed);
            Collections.sort(novasQuestoes, new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    return o1.getDataCriacao().compareTo(o2.getDataCriacao());
                }
            }.reversed());
            if(novasQuestoes.size()>7){
                novasQuestoes = novasQuestoes.subList(0, 7);
            }
            ObservableList items=modVisualizados.getItems();
            for(Module modulo: mod){
                System.out.println(modulo.getNomeModulo());
                items.add(modulo.getNomeModulo().trim() + "-" + modulo.getDominio().getNomeDominio().trim());
            }
            modVisualizados.setItems(items);
            items=subVisualizados.getItems();
            for(Subject sub:disc){
                System.out.println(sub.getNomeDominio());
                items.add(sub.getNomeDominio().trim());
            }
            subVisualizados.setItems(items);
            items=questRecentes.getItems();
            for(Question question:novasQuestoes){
                System.out.println(question.getTituloQuestao());
                items.add(question.getTituloQuestao().trim() + "-" + question.getModulo().getNomeModulo().trim() + "-" + question.getModulo().getDominio().getNomeDominio().trim());
            }
            questRecentes.setItems(items);
            items=questVisualizadas.getItems();
            for(Question question:quest){
                System.out.println(question.getTituloQuestao());
                items.add(question.getTituloQuestao().trim() + "-" + question.getModulo().getNomeModulo().trim() + "-" + question.getModulo().getDominio().getNomeDominio().trim());
            }
            questVisualizadas.setItems(items);
        } catch (RemoteException | NotBoundException | BusinessException | PersistenceException | ClassCastException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Map<Long, Integer> ordenaPorValor(Map<Long, Integer> map){
        List<Map.Entry<Long, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>(){
                @Override
                public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2){
                    return ( o1.getValue() ).compareTo( o2.getValue() );
                }
        }.reversed());
        Map<Long, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Long, Integer> entry : list){
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public void setMain(RespostaCertaJFX main) {
        this.main = main;
    }
   
}
