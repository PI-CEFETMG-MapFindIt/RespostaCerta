<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<!DOCTYPE html>

<% List<Question> lista = (List<Question>) request.getAttribute("questoes");%>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Minhas Questões</title>

        <link href="css/MinhasQuestoes.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <h3>
                    <b>Minhas Questões</b>
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
                                <label for="tituloQuestao<%=id%>"><%=questao.getModulo().getNomeModulo()%></label>
                            </div>
                            <div class="rightItem">

                                <a href="/RespostaCerta/ControllerServlet?control=PagEditarQuestao&id=<%=questao.getIdQuestao()%>" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil right"></span>
                                </a>

                                <button type="button" data-toggle="modal" data-target="#confirm<%=id%>" class="btn btn-default">
                                    <span class="glyphicon glyphicon-trash right"></span>
                                </button>
                            </div>
                        </div>
                        <div id="confirm<%=id%>" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <h4>Você tem certeza que deseja deletar a questão <%=questao.getTituloQuestao()%>?</h4>
                                    </div>
                                    <div class="modal-footer">

                                        <a href="/RespostaCerta/ControllerServlet?control=ExcluirQuestao&id=<%=questao.getIdQuestao()%>" type="button" class="btn btn-danger" id="delete<%=id%>">
                                            Deletar
                                        </a>

                                        <button type="button" data-dismiss="modal" class="btn">Cancelar</button>
                                    </div>
                                </div>
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
