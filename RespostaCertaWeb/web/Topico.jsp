<%--Author: Pedro Almeida--%>


<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Topic"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.TopicAnswer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%Topic topico = (Topic) request.getAttribute("topico");%>
<%Question questao = (Question) request.getAttribute("questao");%>
<%List<TopicAnswer> resposta = (List<TopicAnswer>) request.getAttribute("resposta");%>
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
                        
                    </div>
                </div>
            </div>
        </div>        
        <script src="js/Questao.js"></script>
    </body>
</html>
