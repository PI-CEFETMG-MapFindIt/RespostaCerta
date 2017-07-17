package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagement;
import br.cefetmg.respostaCerta.model.service.OpenAnswerManagementImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
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
            for(ClosedAnswer fechada:respostasFechadas){
                modulos.put(fechada.getQuestao().getModulo().getIdModulo(), fechada.getQuestao().getModulo());
            }
            for(OpenAnswer aberta:respostasAbertas){
                modulos.put(aberta.getQuestao().getModulo().getIdModulo(), aberta.getQuestao().getModulo());
            }
            TreeMap<Long, Integer> contModulos = new TreeMap<>();
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
            ArrayList<Module> lista = new ArrayList<>();
            Iterator<Long> it = contModulos.descendingKeySet().descendingIterator();
            while(it.hasNext()){
                lista.add(modulos.get(it.next()));
            }
            request.setAttribute("modulo", lista);
            return "Home.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
