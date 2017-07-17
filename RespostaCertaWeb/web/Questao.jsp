<%--Author: Pedro Almeida & Pedro Otávio--%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%Question questao = ((Question) request.getAttribute("questao"));%>
<%String idtQ = (String) request.getAttribute("idtQ");%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=questao.getTituloQuestao()%></title>
        <link href="css/Questao.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="container-fluid">    
            <div class="row">
                <div class="col-md-8 col-md-offset-2 text-center">
                    <h3>
                        <b><%=questao.getTituloQuestao()%></b>
                    </h3>
                    <small>
                        <a href="/RespostaCerta/ControllerServlet?control=QuestoesModulo&id=<%=questao.getModulo().getIdModulo()%>"><%=questao.getModulo().getNomeModulo()%></a>
                        <span>/</span>
                        <a href="/RespostaCerta/ControllerServlet?control=ModulosDisciplina&id=<%=questao.getModulo().getDominio().getIdDominio()%>"><%=questao.getModulo().getDominio().getNomeDominio()%></a>
                    </small>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <% if(questao.getQuestPhoto()!=null){%>
                            <div class="text-center">
                                <img src="/RespostaCerta/ImageServlet?tipo=quest&id=<%=questao.getIdQuestao()%>">
                            </div>
                            <%}%>
                            <p><%= questao.getEnunciadoQuestao()%></p>
                        </div>
                        <div class="row" style="padding-bottom: 15px">
                            <div class="col-md-4 col-md-offset-4">    
                                <div class="btn-group btn-group-justified" role="group">
                                    <a href="/RespostaCerta/ControllerServlet?control=ResponderQuestao<%=(idtQ + "&id=" + questao.getIdQuestao())%>" class="btn btn-default">Responder</a>
                                    <a href="/RespostaCerta/ControllerServlet?control=ForumQuestao&id=<%= questao.getIdQuestao()%>" class="btn btn-default">Fórum</a>
                                </div>
                            </div>   
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>