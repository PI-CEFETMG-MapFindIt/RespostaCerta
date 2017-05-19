/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ModuleDAO {
    public void insert(Module modulo) throws PersistenceException;
    public void update(Module modulo) throws PersistenceException;
    public Module delete(Long moduloId) throws PersistenceException;
    public Module getModuleById(Long moduloId) throws PersistenceException;
    public List<Module> listAll() throws PersistenceException;
}
