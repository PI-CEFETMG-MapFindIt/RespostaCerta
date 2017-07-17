<%--Author:Vitor --%>

<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Subject disciplina = (Subject) request.getAttribute("disciplina");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Disciplina</title>
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <form action="/RespostaCerta/ControllerServlet?control=EditarDisciplina" method="POST">
            <div class="form-group-lg">
                <label for="nome">Nome da Disciplina</label><br>
                <input class="form-control" name="nome" id="nome" value="<%=disciplina.getNomeDominio()%>" placeholder="Digite o Nome da Disciplina"/>
            </div>
            <br>
            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Editar"/>
            </div>
            <input type="hidden" name="id" value="<%=disciplina.getIdDominio()%>"/>
        </form>
    </body>
</html>
