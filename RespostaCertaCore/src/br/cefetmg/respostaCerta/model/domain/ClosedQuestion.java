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
public class ClosedQuestion extends Question{
    private String alt1;
    private String alt2;
    private String alt3;
    private String alt4;
    private String alt5;
    private int correta;

    public ClosedQuestion() {
    }
    
    /**
     *
     * @param alt1
     * @param alt2
     * @param alt3
     * @param alt4
     * @param alt5
     * @param correta
     * @param modulo
     * @param criador
     * @param enunciadoQuestao
     * @param idtQuestao
     * @param dataCriacao
     * @param tituloQuestao
     * @param questPhoto
     * @param idtDificuldade
     */
    public ClosedQuestion(String alt1, String alt2, String alt3, String alt4, String alt5, int correta, Module modulo, User criador, String enunciadoQuestao, boolean idtQuestao, LocalDate dataCriacao, String tituloQuestao, Image questPhoto, char idtDificuldade) {
        super(modulo, criador, enunciadoQuestao, idtQuestao, dataCriacao, tituloQuestao, questPhoto, idtDificuldade);
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.alt3 = alt3;
        this.alt4 = alt4;
        this.alt5 = alt5;
        this.correta = correta;
    }

    /**
     *
     * @return
     */
    public String getAlt1() {
        return alt1;
    }

    /**
     *
     * @param alt1
     */
    public void setAlt1(String alt1) {
        this.alt1 = alt1;
    }

    public String getAlt2() {
        return alt2;
    }

    public void setAlt2(String alt2) {
        this.alt2 = alt2;
    }

    public String getAlt3() {
        return alt3;
    }

    public void setAlt3(String alt3) {
        this.alt3 = alt3;
    }

    public String getAlt4() {
        return alt4;
    }

    public void setAlt4(String alt4) {
        this.alt4 = alt4;
    }

    public String getAlt5() {
        return alt5;
    }

    public void setAlt5(String alt5) {
        this.alt5 = alt5;
    }

    public int getCorreta() {
        return correta;
    }

    public void setCorreta(int correta) {
        this.correta = correta;
    }
}
