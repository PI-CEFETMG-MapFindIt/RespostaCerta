<%--Author:Adalberto & Vitor --%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%List <Question> lista=(List<Question>) request.getAttribute("questoes");%>
<html>
    <link href="css/BuscaQuestao.css" rel="stylesheet">
    <head>
        <title>Resultados <%=request.getAttribute("query")%></title>
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="row">
      		<div class="col-md-8 col-md-offset-2 text-center">
      			<h3>
      				<b>Resultados da Busca: <%=request.getAttribute("query")%></b>
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
                                        <a class="h4" href="/RespostaCerta/ControllerServlet?control=Questao&id=<%=questao.getIdQuestao()%>"><b><%=questao.getTituloQuestao()%></b></a><br>
                                        <label for="tituloQuestao<%=id%>"><a class="h5" href="/RespostaCerta/ControllerServlet?control=QuestoesModulo&id=<%=questao.getModulo().getIdModulo()%>"><%=questao.getModulo().getNomeModulo()%></a></label>
                                    </div>
                                </div>
                            </li>
                        <%id++;
                            }
                        %>
                </ul>
            </div>
      	</div>
    <script src="js/BuscaQuestao.js"></script>
  </body>
</html>

</html>
