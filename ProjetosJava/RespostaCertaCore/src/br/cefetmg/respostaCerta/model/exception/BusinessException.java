package br.cefetmg.respostaCerta.model.exception;

public class BusinessException extends Exception {

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Exception ex) {
        super(ex);
    }    
}
