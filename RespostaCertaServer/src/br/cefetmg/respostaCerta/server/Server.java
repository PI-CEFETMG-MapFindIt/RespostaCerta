/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server;

import br.cefetmg.respostaCerta.model.server.ClosedAnswerManagement;
import br.cefetmg.respostaCerta.model.server.ClosedQuestionManagement;
import br.cefetmg.respostaCerta.model.server.ForumManagement;
import br.cefetmg.respostaCerta.model.server.LoginManagement;
import br.cefetmg.respostaCerta.model.server.ModuleManagement;
import br.cefetmg.respostaCerta.model.server.OpenAnswerManagement;
import br.cefetmg.respostaCerta.model.server.OpenQuestionManagement;
import br.cefetmg.respostaCerta.model.server.PerformanceManagement;
import br.cefetmg.respostaCerta.model.server.SubjectManagement;
import br.cefetmg.respostaCerta.model.server.TopicAnswerManagement;
import br.cefetmg.respostaCerta.model.server.TopicManagement;
import br.cefetmg.respostaCerta.model.server.UserManagement;
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
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try { 
            System.out.println("Security ok");
            ClosedAnswerManagement closedAnswer = new ClosedAnswerManagementImpl();
            ClosedAnswerManagement closedAnswerStub = (ClosedAnswerManagement) UnicastRemoteObject.exportObject(closedAnswer, 0);
            
            ClosedQuestionManagement closedQuestion = new ClosedQuestionManagementImpl();
            ClosedQuestionManagement closedQuestionStub = (ClosedQuestionManagement) UnicastRemoteObject.exportObject(closedQuestion, 0);
            
            ForumManagement forum = new ForumManagementImpl();
            ForumManagement forumStub = (ForumManagement) UnicastRemoteObject.exportObject(forum, 0);
            
            LoginManagement login = new LoginManagementImpl();
            LoginManagement loginStub = (LoginManagement) UnicastRemoteObject.exportObject(login, 0);
            
            ModuleManagement module = new ModuleManagementImpl();
            ModuleManagement moduleStub = (ModuleManagement) UnicastRemoteObject.exportObject(module, 0);
            
            OpenAnswerManagement openAnswer = new OpenAnswerManagementImpl();
            OpenAnswerManagement openAnswerStub = (OpenAnswerManagement) UnicastRemoteObject.exportObject(openAnswer, 0);
            
            OpenQuestionManagement openQuestion = new OpenQuestionManagementImpl();
            OpenQuestionManagement openQuestionStub = (OpenQuestionManagement) UnicastRemoteObject.exportObject(openQuestion, 0);
            
            PerformanceManagement performance = new PerformanceManagementImpl();
            PerformanceManagement performanceStub = (PerformanceManagement) UnicastRemoteObject.exportObject(performance, 0);
            
            SubjectManagement subject = new SubjectManagementImpl();
            SubjectManagement subjectStub = (SubjectManagement) UnicastRemoteObject.exportObject(subject, 0);
            
            TopicAnswerManagement topicAnswer = new TopicAnswerManagementImpl();
            TopicAnswerManagement topicAnswerStub = (TopicAnswerManagement) UnicastRemoteObject.exportObject(topicAnswer, 0);
            
            TopicManagement topic = new TopicManagementImpl();
            TopicManagement topicStub = (TopicManagement) UnicastRemoteObject.exportObject(topic, 0);
            
            UserManagement user = new UserManagementImpl();
            UserManagement userStub = (UserManagement) UnicastRemoteObject.exportObject(user, 0);
            
            System.out.println("Export ok");
            Registry registry = LocateRegistry.createRegistry(2222);
            System.out.println("Registry ok");
            registry.rebind("ClosedAnswerManagement", closedAnswerStub);
            registry.rebind("ClosedQuestionManagement", closedQuestionStub);
            registry.rebind("ForumManagement", forumStub);
            registry.rebind("LoginManagement", loginStub);
            registry.rebind("ModuleManagement", moduleStub);
            registry.rebind("OpenAnswerManagement", openAnswerStub);
            registry.rebind("OpenQuestionManagement", openQuestionStub);
            registry.rebind("PerformanceManagement", performanceStub);
            registry.rebind("SubjectManagement", subjectStub);
            registry.rebind("TopicAnswerManagement", topicAnswerStub);
            registry.rebind("TopicManagement", topicStub);
            registry.rebind("UserManagement", userStub);
            System.err.println("Rebind ok");
        } catch (RemoteException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
