/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;
import br.cefetmg.respostaCerta.model.dao.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adalbs
 */
public class BuscarQuestao {
    public static String processa(HttpServletRequest request){
        try{
            return PagBuscarQuestoes.processa(request);
        }catch (Exception e) {
            request.setAttribute("erro", e.getMessage());
            return "Erro.jsp";
        }
    }
}
