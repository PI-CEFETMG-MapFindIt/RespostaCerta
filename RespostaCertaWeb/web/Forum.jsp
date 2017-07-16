<%-- Author: Pedro Almeida --%>

<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Topic"%>
        
<!DOCTYPE html>
<% List<Topic> topico = (List<Topic>) request.getAttribute("topico");%>
<%Question questao = (Question) request.getAttribute("questao");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum: <%=questao.getTituloQuestao()%></title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
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
                            <a href="">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <h4>
                                                <div class="col-md-6 text-left">
                                                    <%=autor.getNomeUsuario()%>
                                                </div>
                                                <div class="col-md-6 text-right">
                                                    <%=top.getDataPostagem()%>
                                                </div>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12 text-left">
                                                <%=top.getTxtMensagem()%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>            
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        
        <%="</div></div>"%>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/Questao.js"></script>
    </body>
</html>
