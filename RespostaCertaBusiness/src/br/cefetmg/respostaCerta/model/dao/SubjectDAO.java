/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface SubjectDAO {
    public void insert(Subject disciplina) throws PersistenceException;
    public void update(Subject disciplina) throws PersistenceException;
    public Subject delete(Long disciplinaId) throws PersistenceException;
    public Subject getSubjectById(Long disciplinaId) throws PersistenceException;
    public List<Subject > listAll() throws PersistenceException;
}
