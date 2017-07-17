<%--Author:Vitor --%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<!DOCTYPE html>

<% List<Module> lista = (List<Module>) request.getAttribute("modulos");
   Subject sub = (Subject) request.getAttribute("disciplina");%>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Módulos de <%=sub.getNomeDominio()%></title>

        <link href="css/MinhasQuestoes.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <h3>
                    <b>Módulos de <%=sub.getNomeDominio()%></b>
                </h3>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <ul class="list-group">
                    <%int id = 0;
                        for (Module modulo : lista) {%>
                    <li class="list-group-item">
                        <div class="flexContainer">
                            <div class="leftItem">
                                <a href="/RespostaCerta/ControllerServlet?control=QuestoesModulo&id=<%=modulo.getIdModulo()%>" class="h4"><b><%=modulo.getNomeModulo()%></b></a><br>
                            </div>
                        </div>
                    </li>
                    <%id++;
                        }
                    %>
                </ul>
            </div>
        </div>
<script src="js/MinhasQuestoes.js"></script>
</body>
</html>
