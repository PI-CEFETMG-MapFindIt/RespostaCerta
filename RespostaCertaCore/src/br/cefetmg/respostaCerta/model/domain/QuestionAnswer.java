/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author umcan
 */
public class QuestionAnswer implements Serializable{
    private User autor;
    private LocalDate dataResposta;
    private char idtResposta;
    private Long idResposta;
    private boolean correta;

    public QuestionAnswer() {
    }

    public QuestionAnswer(User autor, LocalDate dataResposta, char idtResposta, boolean correta) {
        this.autor = autor;
        this.dataResposta = dataResposta;
        this.idtResposta = idtResposta;
        this.correta=correta;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public LocalDate getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDate dataResposta) {
        this.dataResposta = dataResposta;
    }

    public char getIdtResposta() {
        return idtResposta;
    }

    public void setIdtResposta(char idtResposta) {
        this.idtResposta = idtResposta;
    }
    
    public Long getIdResposta(){
        return this.idResposta;
    }
    
    public void setIdResposta(Long idResposta){
        this.idResposta=idResposta;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }  
}
