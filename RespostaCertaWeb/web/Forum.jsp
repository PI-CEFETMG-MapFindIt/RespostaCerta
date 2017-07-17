<%-- Author: Pedro Almeida --%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Topic"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<%List<Topic> topico = (List<Topic>) request.getAttribute("topico");%>
<%Question questao = (Question) request.getAttribute("questao");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum: <%=questao.getTituloQuestao()%></title>
    </head>
    <body>
        <%@include file="/Menu.jsp"%>
        <div class="container-fluid">    
            <div class="row">
                <div class="col-md-8 col-md-offset-2 text-center">
                    <h3>
                        <b><%=questao.getTituloQuestao()%></b>
                    </h3>
                    <div class="row">
                        <div class="col-md-12 text-left">
                            <% for (Topic top : topico) {%>
                                <%User autor = top.getAutor();%>
                                <p></p>
                                <a href="/RespostaCerta/ControllerServlet?control=TopicoQuestao&id=<%=top.getTopicoId()%>">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color: #7F7F7F">
                                            <div class="row" style="color: white">
                                                <div class="col-md-6 text-right">
                                                    <%=autor.getNomeUsuario()%>
                                                </div>
                                                <div class="col-md-6 text-left">
                                                    <%=top.getDataPostagem()%>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body text-left">
                                            <div class="row">
                                                <h4>
                                                    <div class="col-md-12">
                                                        <%=top.getTxtMensagem()%>
                                                    </div>
                                                </h4>
                                            </div>
                                            <div class="row text-center">
                                                <br>
                                                <div class="col-md-12">
                                                    <% if(top.getMsgPhoto()!=null){%>
                                                        <img src="temp">
                                                    <%}%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>            
                            <%}%>
                            <div class="text-center">
                                <a href="/RespostaCerta/ControllerServlet?control=ForumCriar&id=<%=questao.getIdQuestao()%>" class="btn btn-default" type="submit"
                                style="border-radius: 0px; background-color: #7F7F7F; 
                                color: white">Criar novo Tópico</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <link href="css/Forum.css" rel="stylesheet">
        <script src="js/Questao.js"></script>
    </body>
</html>
