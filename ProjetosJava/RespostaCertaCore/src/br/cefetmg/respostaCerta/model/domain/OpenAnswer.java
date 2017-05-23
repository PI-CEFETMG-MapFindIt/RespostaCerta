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
public class OpenAnswer extends QuestionAnswer{
    private String resposta;
    
    public OpenAnswer() {
    }

    public OpenAnswer(String resposta, User autor, Question questao, LocalDate dataResposta, char idtResposta, boolean correta) {
        super(autor, questao, dataResposta, idtResposta, correta);
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
