<%-- 
    Document   : BuscaQuestao
    Created on : 16/07/2017, 20:29:00
    Author     : umcan
--%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%List <Subject> lista=(List<Subject>) request.getAttribute("disciplinas");%>
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
                        for (Subject dominio : lista) {%>
                            <li class="list-group-item">
                                <div class="flexContainer">
                                    <div class="leftItem">
                                        <a id="tituloDominio<%=id%>" class="h4" href="/RespostaCerta/ControllerServlet?control=ModulosDisciplina&id=<%=dominio.getIdDominio()%>"><b> <%=dominio.getNomeDominio()%></b></a><br>
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
