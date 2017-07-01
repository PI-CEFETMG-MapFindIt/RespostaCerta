/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.controller;

import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.service.UserManagement;
import br.cefetmg.respostaCerta.model.service.UserManagementImpl;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import static javax.swing.Spring.height;

/**
 *
 * @author umcan
 */
public class Cadastro {
    public static String processa(HttpServletRequest request){
        User usuario = new User();
        //Cria imagem vazia
        BufferedImage bi = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();
        ig2.setBackground(Color.WHITE);
        ig2.clearRect(0, 0, 300, 300);
        usuario.setFotoUsuario(bi);
        usuario.setIdtUsuario(request.getParameter("tipo").charAt(0));//A = Aluno e E=Professor em espera
        usuario.setLoginUsuario(request.getParameter("email"));
        usuario.setNomeUsuario(request.getParameter("primNome") + " " + request.getParameter("ultimoNome"));
        String senha = request.getParameter("password");
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        m.update(senha.getBytes(), 0, senha.length());
        senha = new BigInteger(1,m.digest()).toString(16);
        usuario.setSenhaUsuario(senha);
        UserManagement user = new UserManagementImpl(new UserDAOImpl());
        try {
            user.registerUser(usuario);
        } catch (BusinessException | PersistenceException ex) {
            request.setAttribute("erro", ex.getMessage());
            return "Erro.jsp";
        }
        request.getSession().setAttribute("usuario", usuario.getIdUsuario());
        return "index.jsp";
    }
}