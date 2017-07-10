<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%Question questao = ((Question) request.getAttribute("questao"));%>
<%String modulo = questao.getModulo().getNomeModulo();%>
<%String idtQ = (String) request.getAttribute("idtQ");%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title><%= questao%></title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/VisualizarQuestao.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <h3>
                    <b><%= modulo%></b>
                </h3>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <ul class="list-group">
                    <li class="list-group-item">
                        <div class="flexContainer">
                            <div class="leftItem">
                                <p><%= questao.getEnunciadoQuestao()%></p>
                            </div>
                        </div>
                        <div class="rightItem">
                                <a href="/RespostaCerta/ControllerServlet?control=ResponderQuestao<%= (idtQ + "&id=" + questao.getIdQuestao())%>" type="button" class="btn btn-default">Responder</a>
                                <a href="/RespostaCerta/ControllerServlet?control=ForumQuestao&id=<%= questao.getIdQuestao()%>" type="button" class="btn btn-default">Fórum</a>
                            </form>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <%= "</div></div>"%>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/Questao.js"></script>
    </body>
</html>