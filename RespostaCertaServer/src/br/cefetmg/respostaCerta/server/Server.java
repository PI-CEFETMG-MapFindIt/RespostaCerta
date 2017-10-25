/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server;

import br.cefetmg.respostaCerta.server.business.UserManagementImpl;
import br.cefetmg.respostaCerta.server.business.ClosedQuestionManagementImpl;
import br.cefetmg.respostaCerta.server.business.ClosedAnswerManagementImpl;
import br.cefetmg.respostaCerta.server.business.ForumManagementImpl;
import br.cefetmg.respostaCerta.server.business.LoginManagementImpl;
import br.cefetmg.respostaCerta.server.business.ModuleManagementImpl;
import br.cefetmg.respostaCerta.server.business.OpenAnswerManagementImpl;
import br.cefetmg.respostaCerta.server.business.OpenQuestionManagementImpl;
import br.cefetmg.respostaCerta.server.business.PerformanceManagementImpl;
import br.cefetmg.respostaCerta.server.business.SubjectManagementImpl;
import br.cefetmg.respostaCerta.server.business.TopicAnswerManagementImpl;
import br.cefetmg.respostaCerta.server.business.TopicManagementImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author aluno
 */
public class Server {
    
    public static Registry registry;
    
    public static void main(String[] args){
        try {
            registry = LocateRegistry.createRegistry(2222);
            registry.rebind("ClosedAnswerManagement", UnicastRemoteObject.exportObject(new ClosedAnswerManagementImpl(), 0));
            registry.rebind("ClosedQuestionManagement", UnicastRemoteObject.exportObject(new ClosedQuestionManagementImpl(), 0));
            registry.rebind("ForumManagement", UnicastRemoteObject.exportObject(new ForumManagementImpl(), 0));
            registry.rebind("LoginManagement", UnicastRemoteObject.exportObject(new LoginManagementImpl(), 0));
            registry.rebind("ModuleManagement", UnicastRemoteObject.exportObject(new ModuleManagementImpl(), 0));
            registry.rebind("OpenAnswerManagement", UnicastRemoteObject.exportObject(new OpenAnswerManagementImpl(), 0));
            registry.rebind("OpenQuestionManagement", UnicastRemoteObject.exportObject(new OpenQuestionManagementImpl(), 0));
            registry.rebind("PerformanceManagement", UnicastRemoteObject.exportObject(new PerformanceManagementImpl(), 0));
            registry.rebind("SubjectManagement", UnicastRemoteObject.exportObject(new SubjectManagementImpl(), 0));
            registry.rebind("TopicAnswerManagement", UnicastRemoteObject.exportObject(new TopicAnswerManagementImpl(), 0));
            registry.rebind("TopicManagement", UnicastRemoteObject.exportObject(new TopicManagementImpl(), 0));
            registry.rebind("UserManagement", UnicastRemoteObject.exportObject(new UserManagementImpl(), 0));
        } catch (RemoteException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
