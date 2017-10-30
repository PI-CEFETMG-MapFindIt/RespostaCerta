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
public class OpenAnswer extends QuestionAnswer implements Serializable{
    private String resposta;
    private Question questao;
    public OpenAnswer() {
    }

    public OpenAnswer(String resposta, User autor, Question questao, LocalDate dataResposta, char idtResposta, boolean correta) {
        super(autor, dataResposta, idtResposta, correta);
        this.questao=questao;
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Question getQuestao() {
        return questao;
    }

    public void setQuestao(Question questao) {
        this.questao = questao;
    }
    
    
}
