/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.time.LocalDate;

/**
 *
 * @author umcan
 */
public class TopicAnswer {
    private Topic mensagem;
    private Long idMensagemResposta;
    private User autor;
    private String txtMensagem;
    private LocalDate dataResposta;

    public TopicAnswer() {
    }
    
    public TopicAnswer(Topic mensagem, User autor, String txtMensagem, LocalDate dataResposta) {
        this.mensagem = mensagem;
        this.autor = autor;
        this.txtMensagem = txtMensagem;
        this.dataResposta = dataResposta;
    }

    public Topic getMensagem() {
        return mensagem;
    }

    public void setMensagem(Topic mensagem) {
        this.mensagem = mensagem;
    }

    public Long getIdMensagemResposta() {
        return idMensagemResposta;
    }

    public void setIdMensagemResposta(Long idMensagemResposta) {
        this.idMensagemResposta = idMensagemResposta;
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

    public LocalDate getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDate dataResposta) {
        this.dataResposta = dataResposta;
    }
    
    
}
