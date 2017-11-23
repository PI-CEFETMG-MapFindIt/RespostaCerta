/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import br.cefetmg.respostaCerta.util.Converter;
import java.awt.Image;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author umcan
 */
@Entity
@Table(name="Usuario")
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Long idUsuario;
    private String nomeUsuario;
    private String loginUsuario;
    private String senhaUsuario;
    private char idtUsuario;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private Byte[] fotoUsuario;

    public User() {
    }
    
    public User(Long idUsuario, String nomeUsuario, String loginUsuario, String senhaUsuario, char idtUsuario, Image fotoUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.idtUsuario = idtUsuario;
        this.fotoUsuario=Converter.ImageToByteArray(fotoUsuario);
    }
    
    public User(String nomeUsuario, String loginUsuario, String senhaUsuario, char idtUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.idtUsuario = idtUsuario;
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public char getIdtUsuario() {
        return idtUsuario;
    }

    public void setIdtUsuario(char idtUsuario) {
        this.idtUsuario = idtUsuario;
    }  

    public Image getFotoUsuario() {
        return Converter.ByteArrayToImage(fotoUsuario);
    }

    public void setFotoUsuario(Image fotoUsuario) {
        this.fotoUsuario=Converter.ImageToByteArray(fotoUsuario);
    }
    
}
