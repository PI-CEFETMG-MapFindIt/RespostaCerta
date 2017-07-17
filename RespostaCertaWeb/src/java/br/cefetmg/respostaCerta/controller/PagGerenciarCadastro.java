package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vitor
 */
public class PagGerenciarCadastro {
    public static String processa(HttpServletRequest request){
        try{
            ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
            List<Module> modulos = modMan.getAllModules();
            request.setAttribute("modulos", modulos);
            SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
            List<Subject> disciplinas = subMan.getAllSubjects();
            request.setAttribute("disciplinas", disciplinas);
            UserManagement usMan = new UserManagementImpl(new UserDAOImpl());
            List<User> profPendentes = usMan.getUserByIdt('E');
            request.setAttribute("profPendentes", profPendentes);
            return "GerenciarCadastros.jsp";
        }catch(BusinessException | PersistenceException ex){
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
