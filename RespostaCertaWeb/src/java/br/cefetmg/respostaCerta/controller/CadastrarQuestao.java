/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.ModuleManagement;
import br.cefetmg.respostaCerta.model.service.ModuleManagementImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import br.cefetmg.respostaCerta.model.service.SubjectManagementImpl;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author aluno
 */
public class CadastrarQuestao {
    public static String processa(HttpServletRequest request){
        try{
            int idtNovo = Integer.parseInt(request.getParameter("idtNovo"));
            ModuleManagement modMan = new ModuleManagementImpl(new ModuleDAOImpl());
            SubjectManagement subMan = new SubjectManagementImpl(new SubjectDAOImpl());
            UserManagement userMan = new UserManagementImpl(new UserDAOImpl());
            Module mod;
            if(idtNovo==1){
                Long idDisc = Long.parseLong(request.getParameter("disciplina"));
                mod = new Module();
                mod.setDominio(subMan.getSubjectById(idDisc));
                mod.setNomeModulo(request.getParameter("nomeModulo"));
                modMan.registerModule(mod);
            }else{
                if(idtNovo==2){
                    Subject sub = new Subject(request.getParameter("nomeDisciplina"));
                    subMan.registerSubject(sub);
                    mod = new Module();
                    mod.setDominio(sub);
                    mod.setNomeModulo(request.getParameter("nomeModulo"));
                    modMan.registerModule(mod);
                }else{
                    mod = modMan.getModuleById(Long.parseLong(request.getParameter("modulo")));
                }
            }
            if(request.getParameter("tipoQuestao")=="Discursiva"){
                Question questao = new Question();
                questao.setCriador(userMan.getUserById((Long) request.getSession().getAttribute("usuario")));
                questao.setDataCriacao(LocalDate.now());
                questao.setEnunciadoQuestao(request.getParameter("enunciado"));
                switch(request.getParameter("difQuestao")){
                    case "Fácil": questao.setIdtDificuldade('F'); break;
                    case "Moderada": questao.setIdtDificuldade('M'); break;
                    case "Difícil": questao.setIdtDificuldade('D'); break;
                    case "Desafio": questao.setIdtDificuldade('X'); break;
                }
                questao.setIdtQuestao(true);
                questao.setQuestPhoto(userMan.getUserById((Long) request.getSession().getAttribute("usuario")).getFotoUsuario());
                questao.setTituloQuestao(request.getParameter("titulo"));
            }
            
            
            return "index.jsp";
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
    }
}
