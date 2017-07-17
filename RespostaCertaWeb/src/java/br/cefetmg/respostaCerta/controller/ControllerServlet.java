/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author umcan
 */
public class ControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String jsp="";
        String control = request.getParameter("control");
        if(control==null){
            control="";
        }
        switch(control){
            case "Login": jsp = Login.processa(request); break;
            case "Cadastrar": jsp = Cadastro.processa(request); break;
            case "PagCadastrar": jsp = "Cadastro.jsp"; break;
            case "Logout": jsp = Logout.processa(request); break;
            case "PagCadastrarQuestao": jsp = PagCadastrarQuestao.processa(request); break;
            case "PagBuscarQuestao": jsp = PagCadastrarQuestao.processa(request); break;
            case "CadastrarQuestao": jsp = CadastrarQuestao.processa(request); break;
            case "PagMinhasQuestoes": jsp = PagMinhasQuestoes.processa(request); break;
            case "PagEditarQuestao": jsp = PagEditarQuestao.processa(request); break;
            case "ExcluirQuestao": jsp = ExcluirQuestao.processa(request); break;
            case "Busca": jsp = Busca.processa(request); break;
            case "Questao": jsp = VisualizarQuestao.processa(request); break;
            case "EditarQuestao": jsp = EditarQuestao.processa(request); break;
            case "Desempenho": jsp = Desempenho.processa(request); break;
            case "SalvarResposta": jsp = SalvarResposta.processa(request); break;
            case "PagGerenciarCadastro": jsp = PagGerenciarCadastro.processa(request); break;
            case "PagEditarModulo": jsp = PagEditarModulo.processa(request); break;
            case "PagEditarDominio": jsp = PagEditarDominio.processa(request); break;
            case "ExcluirModulo": jsp = ExcluirModulo.processa(request); break;
            case "ExcluirDominio": jsp = ExcluirDominio.processa(request); break;
            case "AceitarProfessor": jsp = AceitarProfessor.processa(request); break;
            case "RecusarProfessor": jsp = RecusarProfessor.processa(request); break;
            case "EditarDisciplina": jsp = EditarDominio.processa(request); break;
            case "EditarModulo": jsp = EditarModulo.processa(request); break;
            case "ForumQuestao": jsp = ForumQuestao.processa(request); break;
            case "TopicoQuestao": jsp = TopicoQuestao.processa(request); break;
            case "ModulosDisciplina": jsp = ModulosDisciplina.processa(request); break;
            case "QuestoesModulo": jsp = QuestoesModulo.processa(request); break;
            case "PagPerfil": jsp = Perfil.processa(request); break;
            case "AlteraDados": jsp = AlteraDados.processa(request); break;
            case "AlteraSenha": jsp = AlteraSenha.processa(request); break;
            case "AlteraImagem": jsp = AlteraImagem.processa(request); break;
            case "DeletaConta": jsp = DeletaConta.processa(request); break;
            case "ResponderQuestaoAberta": jsp = ResponderQuestaoAberta.processa(request); break;
            case "EnviarRespostaAberta": jsp = EnviarRespostaAberta.processa(request); break;
            default: jsp = Inicio.processa(request);
        }
        //Redirecionando pagina
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
