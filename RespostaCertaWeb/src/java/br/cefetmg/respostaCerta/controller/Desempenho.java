/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.PerformanceManagement;
import br.cefetmg.respostaCerta.model.service.PerformanceManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aluno
 */
public class Desempenho {
    public static String processa(HttpServletRequest request){
        try {
            PerformanceManagement perf = new PerformanceManagementImpl(new ClosedAnswerDAOImpl(), new ModuleDAOImpl(), new SubjectDAOImpl(), new ClosedQuestionDAOImpl());
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            User usuario = userMan.getUserById((Long)request.getSession().getAttribute("usuario"));
            request.setAttribute("erros", perf.calculateErrors(usuario));
            
            HashMap<Module, Double> errosModulo = new HashMap<>();
            
            OpenQuestionManagement opQuest = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
            ClosedQuestionManagement clQuest = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
            
            List<Question> listQuest = opQuest.getQuestionsByUser((Long)request.getSession().getAttribute("usuario"));
            listQuest.addAll(clQuest.getQuestionsByUser((Long)request.getSession().getAttribute("usuario")));
            HashSet<Module> modulos = new HashSet<>();
            for(Question q:listQuest){
                modulos.add(q.getModulo());
            }
            HashSet<Subject> dominios = new HashSet<>();
            for(Module m:modulos){
                errosModulo.put(m, perf.calculateErrorsByModule(usuario, m));
                dominios.add(m.getDominio());
            }
            request.setAttribute("errosModulo", errosModulo);
            HashMap<Subject, Double> errosDominio = new HashMap<>();
            for(Subject d:dominios){
                errosDominio.put(d, perf.calculateErrorsBySubject(usuario, d));
            }
            request.setAttribute("errosModulo", errosDominio);
            return "Desempenho.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
