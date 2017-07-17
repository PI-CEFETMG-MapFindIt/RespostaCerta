<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<!DOCTYPE html>
<% List<Module> modulos = (List<Module>)request.getAttribute("modulos");
   List<Subject> disciplinas = (List<Subject>) request.getAttribute("disciplinas");
   List<User> profPendentes = (List<User>) request.getAttribute("profPendentes");%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Resposta Certa</title>

        <link href="css/GerenciarCadastros.css" rel="stylesheet">
    </head>

    <body>
        <%@include file="/Menu.jsp" %>
                <!--Aqui vem a parte interna-->
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <br>
                            <br>
                            <br>
                            <div id="exTab1" class="container">	
                                <ul  class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#3a" data-toggle="tab">Cadastro de Professores</a>
                                    </li>
                                    <li>
                                        <a  href="#1a" data-toggle="tab">Disciplinas</a>
                                    </li>
                                    <li>
                                        <a href="#2a" data-toggle="tab">Módulos</a>
                                    </li>     
                                </ul>
                                <br>
                                <div class="tab-content">
                                    <div class="tab-pane" id="1a">
                                        <ul class="list-group">
                                            <%  int cont=0;
                                                for(Subject d:disciplinas){%>
                                            <li class="list-group-item">
                                                <div class="flexContainer">
                                                    <div class="leftItem">
                                                        <h4 id="tituloDisciplina<%=cont%>"><b><%=d.getNomeDominio()%></b></h4>
                                                    </div>
                                                    <div class="rightItem">
                                                        <a href="/RespostaCerta/ControllerServlet?control=PagEditarDominio&id=<%=d.getIdDominio()%>" class="btn btn-default">
                                                            <span class="glyphicon glyphicon-pencil right"></span>
                                                        </a>
                                                        <button type="button" data-toggle="modal" data-target="#confirm<%=cont%>" class="btn btn-default">
                                                            <span class="glyphicon glyphicon-trash right"></span>
                                                        </button>
                                                    </div>
                                                </div>
                                                <div id="confirm<%=cont%>" class="modal fade">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-body">
                                                                <h4>Você tem certeza que deseja deletar a disciplina "<%=d.getNomeDominio()%>"? Isso irá remover todos os módulos, questões e respostas relacionados a essa disciplina</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a href="/RespostaCerta/ControllerServlet?control=ExcluirDominio&id=<%=d.getIdDominio()%>" class="btn btn-danger" id="delete<%=cont%>">Deletar</a>
                                                                <button type="button" data-dismiss="modal" class="btn">Cancelar</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <%cont++;
                                             }%>
                                        </ul>
                                    </div>
                                    <div class="tab-pane" id="2a">
                                        <ul class="list-group">
                                            <%cont=0;
                                            for(Module m:modulos){%>
                                                <li class="list-group-item">
                                                    <div class="flexContainer">
                                                        <div class="leftItem">
                                                            <h4 id="tituloModulo<%=cont%>"><b><%=m.getNomeModulo()%></b></h4>
                                                            <label for="tituloModulo<%=cont%>"><%=m.getDominio().getNomeDominio()%></label>
                                                        </div>
                                                        <div class="rightItem">
                                                            <a href="/RespostaCerta/ControllerServlet?control=PagEditarModulo&id=<%=m.getIdModulo()%>" class="btn btn-default">
                                                                <span class="glyphicon glyphicon-pencil right"></span>
                                                            </a>
                                                            <button type="button" data-toggle="modal" data-target="#confirmM<%=cont%>" class="btn btn-default">
                                                                <span class="glyphicon glyphicon-trash right"></span>
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div id="confirmM<%=cont%>" class="modal fade">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-body">
                                                                    <h4>Você tem certeza que deseja deletar o módulo "<%=m.getNomeModulo()%>"? Isso irá remover todas as questões e respostas relacionados a esse módulo</h4>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <a href="/RespostaCerta/ControllerServlet?control=ExcluirModulo&id=<%=m.getIdModulo()%>" class="btn btn-danger" id="deleteM<%=cont%>">Deletar</a>
                                                                    <button type="button" data-dismiss="modal" class="btn">Cancelar</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            <% cont++; }%>
                                        </ul>
                                    </div>
                                    <div class="tab-pane active" id="3a">
                                        <ul class="list-group">
                                            <%  cont=0;
                                                for(User prof:profPendentes){%>
                                            <li class="list-group-item">
                                                <div class="flexContainer">
                                                    <div class="leftItem">
                                                        <h4 id="tituloProf<%=cont%>"><b><%=prof.getNomeUsuario()%></b></h4>
                                                        <label for="tituloProf<%=cont%>"><%=prof.getLoginUsuario()%></label>
                                                    </div>
                                                    <div class="rightItem">
                                                        <a href="/RespostaCerta/ControllerServlet?control=AceitarProfessor&id=<%=prof.getIdUsuario()%>" class="btn btn-default">
                                                            <span class="glyphicon glyphicon-ok right"></span>
                                                        </a>
                                                        <a href="/RespostaCerta/ControllerServlet?control=RecusarProfessor&id=<%=prof.getIdUsuario()%>" class="btn btn-default">
                                                            <span class="glyphicon glyphicon-remove right"></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </li>
                                            <%cont++;}%>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        <script src="js/GerenciarCadastros.js"></script>
    </body>
</html>
