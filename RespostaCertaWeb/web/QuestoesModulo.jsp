<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<!DOCTYPE html>

<% List<Question> lista = (List<Question>) request.getAttribute("questoes");
   Module modulo = (Module) request.getAttribute("modulo");%>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Questões de <%=modulo.getNomeModulo()%></title>

        <link href="css/MinhasQuestoes.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <h3>
                    <b>Questões de <%=modulo.getNomeModulo()%></b>
                </h3>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <ul class="list-group">
                    <%int id = 0;
                        for (Question questao : lista) {%>
                    <li class="list-group-item">
                        <div class="flexContainer">
                            <div class="leftItem">
                                <a href="/RespostaCerta/ControllerServlet?control=Questao&id=<%=questao.getIdQuestao()%>" class="h4"><b><%=questao.getTituloQuestao()%></b></a><br>
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
