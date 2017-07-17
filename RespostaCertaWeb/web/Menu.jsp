<%--Author:Pedro Almeida & Vitor --%>

<%@page import="br.cefetmg.respostaCerta.model.domain.User"%>
<%@page import="br.cefetmg.respostaCerta.model.dao.UserDAOImpl"%>
<%@page import="br.cefetmg.respostaCerta.model.service.UserManagement"%>
<%@page import="br.cefetmg.respostaCerta.model.service.UserManagementImpl"%>

<%  Boolean falhouLogin = (Boolean) request.getAttribute("falha");
    if(falhouLogin==null){
       falhouLogin=false;
    }   %>

<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/RespostaCerta" style="color:white">RespostaCerta</a>         
        <%  if (request.getSession().getAttribute("usuario") != null) {
                UserManagement management = new UserManagementImpl(new UserDAOImpl());
                User usuario = management.getUserById((Long)request.getSession().getAttribute("usuario"));  %>
            <ul class="nav navbar-nav navbar-left">
                <li><a href="#Perfil" style="color:white">Bem Vindo, <%=usuario.getNomeUsuario()%></a></li>
                <%  char acesso = usuario.getIdtUsuario();  
                    if(acesso=='E') {  %>
                        <p>Cadastro em Análise</p>
                    <%} else {%>
                        <li><a href="#Perfil">Perfil</a></li>
                        <li><a href="/RespostaCerta/ControllerServlet?control=Desempenho">Meu Desempenho</a></li> 
                    <%if(acesso=='P' || acesso=='G') {%>
                        <li><a href="/RespostaCerta/ControllerServlet?control=PagCadastrarQuestao">Cadastrar Questão</a></li>
                        <li><a href="/RespostaCerta/ControllerServlet?control=PagMinhasQuestoes">Minhas Questões</a></li>
                    <%if(acesso=='G') {%>
                        <li><a href="/RespostaCerta/ControllerServlet?control=PagGerenciarCadastro">Gerenciamento de Cadastros</a></li>           
                <%}}}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/RespostaCerta/ControllerServlet?control=Logout" style="color:white">Sair</a></li>
            </ul>
        <%  } else {    %>   
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#modalLogin" data-toggle="modal" style="color:white">Logar</a></li>
                <li><a href="/RespostaCerta/ControllerServlet?control=PagCadastrar" style="color:white">Cadastrar</a></li>
            </ul>
        <%  }   %>           
        <form class="navbar-form navbar-right" action="/RespostaCerta/ControllerServlet?control=Busca" method="POST">
            <select class="form-control" name="opcao">
                <option>Questão</option>
                <option>Módulo</option>
                <option>Disciplina</option>
            </select> 
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Digite sua pesquisa..." id="query" name="search" value="">   
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit"  style="background-color:gray; color:white">
                        <div class="glyphicon glyphicon-search" aria-hidden="true"></div>
                    </button>
                </div>
            </div>
        </form>     
    </div>
</nav>
        
<div id="modalLogin" class="modal fade" role="dialog">
    <div  class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Logar</h4>
            </div>
            <form id="fazerLogin" action="/RespostaCerta/ControllerServlet?control=Login" method="POST">    
                <div class="modal-body">
                    <div class="form-group">
                        <label for="emailUsuario">E-mail</label>
                        <input class="form-control" placeholder="Digite o seu e-mail" required type="email" name="emailUsuario" id="emailUsuario">
                    </div>
                    <div class="form-group">
                        <label for="senhaUsuario">Senha</label>
                        <input class="form-control" type="password" name="senhaUsuario" required id="senhaUsuario">
                    </div>
                    <%if(falhouLogin){%>
                        <div class="form-group" style="color:red;">
                            <p>E-mail ou senha incorreto</p>
                        </div>
                    <%}%>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="submit">Login</button>
                </div>
            </form>        
        </div>
    </div>
</div>        

<%if(falhouLogin){%>
    <script>$('#logar').click();</script>
<%}%>

<br><br><br>

