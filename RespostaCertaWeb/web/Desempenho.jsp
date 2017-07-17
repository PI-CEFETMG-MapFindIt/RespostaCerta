<%--Author:Vitor --%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.ClosedAnswer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<!DOCTYPE html>
<% Double erros = (Double) request.getAttribute("erros"); 
   Map<Module, Double> errosModulo = (Map<Module, Double>) request.getAttribute("errosModulo");
   Map<Subject, Double> errosDominio = (Map<Subject, Double>) request.getAttribute("errosDominio");
   List<ClosedAnswer> respostas = (List<ClosedAnswer>) request.getAttribute("respostas");
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Resposta Certa</title>

        <link href="css/Desempenho.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-fluid">
            <%@include file="/Menu.jsp" %>
                <!--Aqui vem a parte interna-->
                <div class="row">
                    <div class="col-md-12">
                        <div class="tabbable" id="tabs-835946">
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a href="#panel-712485" data-toggle="tab">Pontua��o</a>
                                </li>
                                <li>
                                    <a href="#panel-254705" data-toggle="tab">Hist�rico</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="panel-712485">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h1 class="text-center">
                                                Pontua��o
                                            </h1>
                                        </div>
                                    </div>
                                    <div class="row">
                                       <div class="col-md-12">
                                            <h2 align="center">Porcentagem de Erros e Acertos</h2>
                                            <div id="container" >
                                                <canvas id="pie-chart" style="height:30vh; width:40vw;" ></canvas>
                                            </div>
                                            <br>
                                            <h2 align="center">Porcentagem de Erros por M�dulo</h2>
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>
                                                            M�dulo
                                                        </th>
                                                        <th>
                                                            Disciplina
                                                        </th>
                                                        <th>
                                                            Taxa de acerto
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <%  Iterator<Map.Entry<Module, Double>> it = errosModulo.entrySet().iterator();
                                                    while (it.hasNext()) {
                                                        Map.Entry<Module, Double> entry = it.next();
                                                    %>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <%= entry.getKey().getNomeModulo() %>
                                                            </td>
                                                            <td>
                                                                <%= entry.getKey().getDominio().getNomeDominio()%>
                                                            </td>
                                                            <td>
                                                                <%= entry.getValue() %>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                <% } %>
                                            </table>
                                            <h2 align="center">Porcentagem de Erros por Disciplina</h2>
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>
                                                            Disciplina
                                                        </th>
                                                        <th>
                                                            Taxa de acerto
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <%  Iterator<Map.Entry<Subject, Double>> itDisc = errosDominio.entrySet().iterator();
                                                    while (itDisc.hasNext()) {
                                                        Map.Entry<Subject, Double> entry = itDisc.next();
                                                    %>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <%= entry.getKey().getNomeDominio() %>
                                                            </td>
                                                            <td>
                                                                <%= entry.getValue() %>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                <% } %>
                                            </table>
                                       </div>
                                    </div>
                                    
                                </div>
                                <div class="tab-pane" id="panel-254705">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h1 class="text-center">
                                                Hist�rico
                                            </h1>
                                        </div>
                                    </div>
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>
                                                    Data
                                                </th>
                                                <th>
                                                    Quest�o
                                                </th>
                                                <th>
                                                    M�dulo
                                                </th>
                                                <th>
                                                    Disciplina
                                                </th>
                                                <th>
                                                    Avalia��o
                                                </th>
                                            </tr>
                                        </thead>
                                        <% for(ClosedAnswer answ: respostas){%>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <%=answ.getDataResposta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%>
                                                    </td>
                                                    <td>
                                                        <%=answ.getQuestao().getTituloQuestao()%>
                                                    </td>
                                                    <td>
                                                        <%=answ.getQuestao().getModulo().getNomeModulo()%>
                                                    </td>
                                                    <td>
                                                        <%=answ.getQuestao().getModulo().getDominio().getNomeDominio()%>
                                                    </td>
                                                    <td>
                                                        <%=answ.isCorreta()?"Certa":"Errada"%>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        <%}%>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        <script src="js/Chart.min.js"></script>
        <script src="js/Desempenho.js"></script>
        <script>
            $(document).ready(function(){
               new Chart(document.getElementById("pie-chart"), {
                type: 'pie',
                data: {
                  labels: ["Erros", "Acertos"],
                  datasets: [{
                    label: "Porcentagem de Erros e Acertos",
                    backgroundColor: ["#FF0000", "#00FF00"],
                    data: [<%=erros%>, <%=100-erros%>]
                  }]
                },
                options: {
                  title: {
                    display: false
                  },
                  responsive: true,
                }
            });
            });
        </script>
    </body>
</html>
