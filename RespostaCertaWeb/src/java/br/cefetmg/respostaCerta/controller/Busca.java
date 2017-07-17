package br.cefetmg.respostaCerta.controller;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adalbs & Vitor
 */
public class Busca {
    public static String processa(HttpServletRequest request){
        try{
            String query = request.getParameter("query");
            switch(request.getParameter("opcao")){
                case "Questão": ClosedQuestionManagement cq = new ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
                                OpenQuestionManagement co = new OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
                                List<Question> questoes  = new ArrayList();
                                questoes.addAll(cq.searchClosedQuestion(query));
                                questoes.addAll(co.searchQuestion(query));
                                request.setAttribute("questoes", questoes);
                                request.setAttribute("query", query);
                                return "BuscarQuestao.jsp";
                case "Módulo":  ModuleManagement mMan = new ModuleManagementImpl(new ModuleDAOImpl());
                                List<Module> modulos = mMan.searchModules(query);
                                request.setAttribute("modulos", modulos);
                                request.setAttribute("query", query);
                                return "BuscarMódulo.jsp";
                case "Disciplina": 
                                SubjectManagement sMan = new SubjectManagementImpl(new SubjectDAOImpl());
                                List<Subject> disciplinas = sMan.searchSubjects(query);
                                request.setAttribute("disciplinas", disciplinas);
                                request.setAttribute("query", query);
                                return "BuscarDisciplina.jsp";
            }
        }catch (PersistenceException | BusinessException  e) {
            request.setAttribute("erro", e.getMessage());
            return "Erro.jsp";
        }
        request.setAttribute("erro", "Erro na busca");
        return "Erro.jsp";
    }
}
