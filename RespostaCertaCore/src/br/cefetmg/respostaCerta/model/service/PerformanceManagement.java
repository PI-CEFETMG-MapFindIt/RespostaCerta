/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author umcan
 */
public interface PerformanceManagement{
    public Double calculateErrors(User usuario) throws BusinessException, PersistenceException;
    public Double calculateErrorsByModule(User usuario, Module modulo) throws BusinessException, PersistenceException;
    public Double calculateErrorsBySubject(User usuario, Subject disciplina) throws BusinessException, PersistenceException;
}
