/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ForumManagement{
    public void registerForum(Forum forum) throws BusinessException, PersistenceException;
    public void updateForum(Long id, Forum  forum) throws BusinessException, PersistenceException;
    public void removeForum(Long id) throws BusinessException, PersistenceException;
    public Forum getForumById(Long id) throws BusinessException, PersistenceException;
}
