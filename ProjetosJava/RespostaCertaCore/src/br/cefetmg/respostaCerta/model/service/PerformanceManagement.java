/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Performance;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;

/**
 *
 * @author umcan
 */
public interface PerformanceManagement {
    public Long registerPerformance(Performance performance) throws BusinessException, PersistenceException;
    public void updatePerformance(Long id, Performance  performance) throws BusinessException, PersistenceException;
    public void removePerformance(Long id) throws BusinessException, PersistenceException;
    public Performance getPerformanceById(Long id) throws BusinessException, PersistenceException;
}
