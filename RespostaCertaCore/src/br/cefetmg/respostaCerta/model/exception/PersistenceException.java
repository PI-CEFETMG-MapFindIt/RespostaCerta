package br.cefetmg.respostaCerta.model.exception;

public class PersistenceException extends Exception {

    public PersistenceException(String msg) {
        super(msg);
    }
    
    public PersistenceException(Exception ex) {
        super(ex);
    }
}
