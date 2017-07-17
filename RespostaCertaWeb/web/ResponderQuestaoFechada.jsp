<%-- 
    Document   : ResponderQuestaoFechada
    Created on : 09/07/2017, 17:01:48
    Author     : Adalbs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.ClosedQuestion"%>

<!DOCTYPE html>
<% ClosedQuestion question = (ClosedQuestion) request.getAttribute("question");%>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><%=question.getTituloQuestao()%></title>
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
                <%=question.getEnunciadoQuestao()%>
            </div>
        </div>
        <br>
        <form id="respostaQuestao" action="/RespostaCerta/ControllerServlet?control=EnviarRespostaFechada&id=<%=question.getIdQuestao()%>" method="GET">
            <div class="row">
                <div class="col-md-8 col-md-offset-2 text-justify">
                    <div class="radio">
                        <label>
                            <input type="radio" name="option" id="option1" value="1">>
                                <%=question.getAlt1()%>
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="option" id="option2" value="2">
                                <%=question.getAlt2()%>
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="option" id="option3" value="3">
                                <%=question.getAlt3()%>
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="option" id="option4" value="4">
                                <%=question.getAlt4()%>	
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="option" id="option5" value="5">
                                <%=question.getAlt5()%>
                        </label>
                    </div>
                    
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" align="center" id="titulo">
                    <input type="submit" class="btn btn-sucess" style="background-color: #555555; color:white; width:7vw">
                </div>
            </div>
        </form>
    <script src="js/ResponderQuestao.js"></script>
</body>
</html>
