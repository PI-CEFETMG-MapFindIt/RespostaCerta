package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagement;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;

/* @author Pedro Almeida & Vitor Rodarte*/
public class Inicio {
    public static String processa(HttpServletRequest request){
        try{
            ClosedAnswerManagement closedAnswer = new ClosedAnswerManagementImpl(new ClosedAnswerDAOImpl());
            OpenAnswerManagement openAnswer = new OpenAnswerManagementImpl(new OpenAnswerDAOImpl());
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
            try{
                for(Long modulo:modulos.keySet()){
                    if(contModulos.containsKey(modulo)){
                        contModulos.replace(modulo, contModulos.get(modulo)+1);
                    }else{
                        contModulos.put(modulo, 1);
                    }
                }
            }catch(ClassCastException ex){
                request.setAttribute("erro", ex.getMessage());
                return "Erro.jsp";
            }
            //Ordena HashMap pelo value
            contModulos = ordenaPorValor(contModulos);
            Map<Long, Integer> contDisciplinas = new HashMap<>();
            try{
                for(Long disciplina:disciplinas.keySet()){
                    if(contDisciplinas.containsKey(disciplina)){
                        contDisciplinas.replace(disciplina, contDisciplinas.get(disciplina)+1);
                    }else{
                        contDisciplinas.put(disciplina, 1);
                    }
                }
            }catch(ClassCastException ex){
                request.setAttribute("erro", ex.getMessage());
                return "Erro.jsp";
            }
            //Ordena o HashMap pelo value
            contDisciplinas = ordenaPorValor(contDisciplinas);
            Map<Long, Integer> contQuestoes = new HashMap<>();
            try{
                for(Long questao:questoes.keySet()){
                    if(contQuestoes.containsKey(questao)){
                        contQuestoes.replace(questao, contQuestoes.get(questao)+1);
                    }else{
                        contQuestoes.put(questao, 1);
                    }
                }
            }catch(ClassCastException ex){
                request.setAttribute("erro", ex.getMessage());
                return "Erro.jsp";
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
            OpenQuestionManagement opMan = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            ClosedQuestionManagement clMan = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
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
            request.setAttribute("modulos", mod);
            request.setAttribute("disciplinas", disc);
            request.setAttribute("questoesVis", quest);
            request.setAttribute("questoesNovas", novasQuestoes);
            return "Home.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
    public static Map<Long, Integer> ordenaPorValor(Map<Long, Integer> map){
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
}
