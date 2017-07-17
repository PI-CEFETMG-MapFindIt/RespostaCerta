package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.service.ClosedAnswerManagementImpl;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.PerformanceManagement;
import br.cefetmg.respostaCerta.model.service.PerformanceManagementImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class Desempenho {
    public static String processa(HttpServletRequest request){
        try {
            PerformanceManagement perf = new PerformanceManagementImpl(new ClosedAnswerDAOImpl(), new ModuleDAOImpl(), new SubjectDAOImpl(), new ClosedQuestionDAOImpl());
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
            SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
            User usuario = userMan.getUserById((Long)request.getSession().getAttribute("usuario"));
            request.setAttribute("erros", perf.calculateErrors(usuario));
            
            HashMap<Module, Double> errosModulo = new HashMap<>();
            
            ClosedAnswerManagement clAnswer = new ClosedAnswerManagementImpl(new ClosedAnswerDAOImpl());
            
            List<ClosedAnswer> listQuest=clAnswer.getAnswerByUser((Long)request.getSession().getAttribute("usuario"));
            request.setAttribute("respostas", listQuest);
            HashSet<Long> modulos = new HashSet<>();
            for(ClosedAnswer resp:listQuest){
                modulos.add(resp.getQuestao().getModulo().getIdModulo());
            }
            
            HashSet<Long> dominios = new HashSet<>();
            for(Long id:modulos){
                Module m = modMan.getModuleById(id);
                errosModulo.put(m, perf.calculateErrorsByModule(usuario, m));
                dominios.add(m.getDominio().getIdDominio());
            }
            request.setAttribute("errosModulo", errosModulo);
            HashMap<Subject, Double> errosDominio = new HashMap<>();
            for(Long id:dominios){
                Subject d = subMan.getSubjectById(id);
                errosDominio.put(d, perf.calculateErrorsBySubject(usuario, d));
            }
            request.setAttribute("errosDominio", errosDominio);
            return "Desempenho.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
