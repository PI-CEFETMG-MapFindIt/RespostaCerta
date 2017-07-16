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
        <link href="css/Forum.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Menu.jsp"%>
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
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-md-6 text-right">
                                                <%=autor.getNomeUsuario()%>
                                            </div>
                                            <div class="col-md-6 text-left">
                                                <%=top.getDataPostagem()%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <h4>
                                                <div class="col-md-12 text-left">
                                                    <%=top.getTxtMensagem()%>
                                                </div>
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                            </a>            
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/Questao.js"></script>
    </body>
</html>
