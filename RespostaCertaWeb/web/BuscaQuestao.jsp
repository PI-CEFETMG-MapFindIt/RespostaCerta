<%-- 
    Document   : BuscaQuestao
    Created on : 09/07/2017, 12:40:47
    Author     : Adalbs
--%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%List <Question> lista=(List<Question>) request.getAttribute("questoes");%>
<html>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/BuscaQuestao.css" rel="stylesheet">
    <head>
        <title>Reseultados <%request.getAttribute("query");%></title>
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="row">
      		<div class="col-md-8 col-md-offset-2 text-center">
      			<h3>
      				<b>Resultados da Busca: <%request.getAttribute("query");%></b>
      			</h3>
      		</div>
      	</div>
        <br>
      	<div class="row">
            <div class="col-md-8 col-md-offset-2">
                <ul class="list-group" id="result">
                    <%int id = 0;
                        for (Question questao : lista) {%>
                            <li class="list-group-item">
                                <div class="flexContainer">
                                    <div class="leftItem">
                                        <h4 id="tituloQuestao<%=id%>"><a href="#"><b>Questão  <%=questao.getTituloQuestao()%></b></a></h4>
                                        <label for="tituloQuestao<%=id%>"><a href="#"><%=questao.getModulo()%></a></label>
                                    </div>
                                </div>
                            </li>
                        <%id++;
                            }
                        %>
                </ul>
            </div>
      	</div>
      </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/BuscaQuestao.js"></script>
  </body>
</html>

</html>
