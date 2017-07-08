<%-- 
    Document   : EditarMódulo
    Created on : 08/07/2017, 11:17:12
    Author     : umcan
--%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Module modulo = (Module) request.getAttribute("modulo");
   List<Subject> dominios = (List<Subject>) request.getAttribute("disciplinas");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Modulo</title>
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <form action="/RespostaCerta/ControllerServlet?control=EditarModulo" method="POST">
                <div class="form-group">
                    <label for="nomeModulo">Nome do Modulo</label>
                    <input class="form-control" placeholder="Digite o nome do modulo" type="text" id="nomeModulo" name="nomeModulo" value="<%=modulo.getNomeModulo()%>">
                    <div class="form-group">
                        <label for="disciplina"><h4>Disciplina</h4></label>
                        <select id="disciplina" name="disciplina" class="form-control">
                            <% for (Subject dominio : dominios) {%>
                                <option value="<%=dominio.getIdDominio()%>" <%if(dominio.getIdDominio()==modulo.getDominio().getIdDominio()){%>selected<%}%>><%=dominio.getNomeDominio()%></option>
                            <% }%>
                        </select>
                        <br>
                        <button class="btn btn-default" type="button" data-toggle="modal" data-target="#modalDisciplina">Adicionar Disciplina</button>
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" value="Editar" class="btn btn-default"/>
                </div>
                <input type="hidden" id="idtNovo" value="0" name="idtNovo"/>
                <input type="hidden" name="idModulo" value="<%=modulo.getIdModulo()%>"/>
                <input type="hidden" id="novaDisciplina" name="novaDisciplina"/>
            </form>
        <div id="modalDisciplina" class="modal fade" style="top:60px; left:-15px" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Adicionar Disciplina</h4>
                    </div>
                    <div class="modal-body">
                        <form id="adicionarDisciplina">
                            <div class="form-group">
                                <label for="nomeDisciplina">Nome da nova Disciplina</label>
                                <input class="form-control" placeholder="Digite o nome da disciplina" type="text" name="nomeDisciplina" id="nomeDisciplina">
                            </div>
                            <button type="button" class="btn btn-default" onclick="salvarDisciplina();">Salvar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="fecharDisiciplina" class="btn btn-default" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
        </div>
        </div>
        <script src="js/EditarModulo.js"></script>
    </body>
</html>
