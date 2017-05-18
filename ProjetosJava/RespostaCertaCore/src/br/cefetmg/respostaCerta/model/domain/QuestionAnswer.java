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
public class QuestionAnswer {
    private User autor;
    private Question questao;
    private LocalDate dataResposta;
    private char idtResposta;
    private Long idResposta;

    public QuestionAnswer() {
    }

    public QuestionAnswer(User autor, Question questao, LocalDate dataResposta, char idtResposta) {
        this.autor = autor;
        this.questao = questao;
        this.dataResposta = dataResposta;
        this.idtResposta = idtResposta;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Question getQuestao() {
        return questao;
    }

    public void setQuestao(Question questao) {
        this.questao = questao;
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
    
    
}
