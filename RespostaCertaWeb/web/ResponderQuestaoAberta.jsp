<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>

<!DOCTYPE html>
<%Question question = ((Question) request.getAttribute("questao"));%>
<html lang="en">
    <head>
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">

    	<title><%=question.getTituloQuestao()%></title>

    	<link href="css/bootstrap.min.css" rel="stylesheet">
    	<link href="css/ResponderQuestao.css" rel="stylesheet">
    </head>

    <body>
        <%@include file="/Menu.jsp" %>%>
        <!--Aqui vem a parte interna-->
        <div class="row">
            <br>
            <div class="col-md-12" align="center" id="titulo">
                <h3><b><%=question.getTituloQuestao()%></b></h3>
            </div>
            <br>
        </div>
        <div class="row">
            <br>
            <div class="col-md-8 col-md-offset-2 text-justify" style="border-style:groove; padding: 15px" id="conteudo">
                <% if(question.getQuestPhoto()!=null){%>
                        <div class="center">
                            <img src="/RespostaCerta/ImageServlet?tipo=quest&id=<%=question.getIdQuestao()%>">
                        </div>
                <%}%>
                <p><%= question.getEnunciadoQuestao()%></p>
            </div>
        </div>
        <br>
        <form id="respostaQuestaoAberta" action="/RespostaCerta/ControllerServlet?control=EnviarRespostaAberta&id=<%=question.getIdQuestao()%>" method="POST">
                <div class="form-group col-md-10">
                    <label for="enunciado"><h4>Resposta:</h4></label>
                    <textarea class="form-control" rows="5" name="respostaAberta" id="respostaAberta" placeholder="Digite a resposta..."></textarea>
                </div>
            <div class="row">
                <div class="col-md-12" align="center" id="titulo">
                    <input type="submit" class="btn btn-sucess">
                </div>
            </div>
        </form>
    	<script src="js/jquery.min.js"></script>
    	<script src="js/bootstrap.min.js"></script>
    	<script src="js/ResponderQuestao.js"></script>
    </body>
</html>

