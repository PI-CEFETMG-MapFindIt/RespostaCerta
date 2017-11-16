/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.awt.Image;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author umcan
 */
@Entity
public class Topic implements Serializable{
    @ManyToOne
    private User autor;
    private String txtMensagem;
    @Temporal(TemporalType.DATE)
    private LocalDate dataPostagem;
    @Lob
    private Image msgPhoto;
    @ManyToOne
    private Forum forum;
    @Id
    @GeneratedValue
    private Long topicoId;

    public Topic() {
    }
    
    public Topic(User autor, String txtMensagem, LocalDate dataPostagem, Image msgPhoto, Forum forum) {
        this.autor = autor;
        this.txtMensagem = txtMensagem;
        this.dataPostagem = dataPostagem;
        this.msgPhoto = msgPhoto;
        this.forum = forum;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public String getTxtMensagem() {
        return txtMensagem;
    }

    public void setTxtMensagem(String txtMensagem) {
        this.txtMensagem = txtMensagem;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public Image getMsgPhoto() {
        return msgPhoto;
    }

    public void setMsgPhoto(Image msgPhoto) {
        this.msgPhoto = msgPhoto;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Long getTopicoId() {
        return topicoId;
    }

    public void setTopicoId(Long topicoId) {
        this.topicoId = topicoId;
    } 
}
