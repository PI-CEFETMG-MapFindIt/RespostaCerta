/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;

/**
 *
 * @author umcan
 */
public interface ThreadManagement {
    public Long registerThread(Thread thread) throws BusinessException, PersistenceException;
    public void updateThread(Long id, Thread  thread) throws BusinessException, PersistenceException;
    public void removeThread(Long id) throws BusinessException, PersistenceException;
    public Thread getThreadById(Long id) throws BusinessException, PersistenceException;
}
