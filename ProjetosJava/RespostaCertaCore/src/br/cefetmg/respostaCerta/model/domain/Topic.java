/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.awt.Image;
import java.time.LocalDate;

/**
 *
 * @author umcan
 */
public class Topic {
    private Question questao;
    private Long idMensagem;
    private User autor;
    private String txtMensagem;
    private LocalDate dataPostagem;
    private Image msgPhoto;
    private Forum forum;

    public Topic() {
    }
    
    public Topic(Question questao, User autor, String txtMensagem, LocalDate dataPostagem, Image msgPhoto, Forum forum) {
        this.questao = questao;
        this.autor = autor;
        this.txtMensagem = txtMensagem;
        this.dataPostagem = dataPostagem;
        this.msgPhoto = msgPhoto;
        this.forum = forum;
    }

    public Question getQuestao() {
        return questao;
    }

    public void setQuestao(Question questao) {
        this.questao = questao;
    }

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
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
    
    
}
