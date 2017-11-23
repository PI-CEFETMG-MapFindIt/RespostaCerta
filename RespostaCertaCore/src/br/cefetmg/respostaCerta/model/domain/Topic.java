/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import br.cefetmg.respostaCerta.util.Converter;
import java.awt.Image;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author umcan
 */
@Entity
public class Topic implements Serializable{
    @ManyToOne
    private User autor;
    private String txtMensagem;
    private LocalDate dataPostagem;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private Byte[] msgPhoto;
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
        this.msgPhoto = Converter.ImageToByteArray(msgPhoto);
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
        return Converter.ByteArrayToImage(msgPhoto);
    }

    public void setMsgPhoto(Image msgPhoto) {
        this.msgPhoto = Converter.ImageToByteArray(msgPhoto);
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
