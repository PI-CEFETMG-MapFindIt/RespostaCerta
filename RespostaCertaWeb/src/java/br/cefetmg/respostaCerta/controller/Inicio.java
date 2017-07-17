package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagement;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagementImpl;
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
            for(ClosedAnswer fechada:respostasFechadas){
                modulos.put(fechada.getQuestao().getModulo().getIdModulo(), fechada.getQuestao().getModulo());
                disciplinas.put(fechada.getQuestao().getModulo().getDominio().getIdDominio(), fechada.getQuestao().getModulo().getDominio());
            }
            for(OpenAnswer aberta:respostasAbertas){
                modulos.put(aberta.getQuestao().getModulo().getIdModulo(), aberta.getQuestao().getModulo());
                disciplinas.put(aberta.getQuestao().getModulo().getDominio().getIdDominio(), aberta.getQuestao().getModulo().getDominio());
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
            ArrayList<Module> mod = new ArrayList<>();
            Iterator<Long> it = contModulos.keySet().iterator();
            while(it.hasNext()){
                mod.add(modulos.get(it.next()));
            }
            ArrayList<Subject> disc = new ArrayList<>();
            it = contDisciplinas.keySet().iterator();
            while(it.hasNext()){
                disc.add(disciplinas.get(it.next()));
            }
            request.setAttribute("modulos", mod);
            request.setAttribute("disciplinas", disc);
            request.setAttribute("questao", it);
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
        });
        Map<Long, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Long, Integer> entry : list){
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
