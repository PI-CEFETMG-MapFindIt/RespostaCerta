/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.domain.User;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author umcan
 */
public class Cadastro {
    public static String processa(HttpServletRequest request){
        User usuario = new User();
        usuario.setFotoUsuario(null);
        usuario.setIdtUsuario(0);
        usuario.setLoginUsuario(loginUsuario);
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setSenhaUsuario(senhaUsuario);
    }
}
