<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.security.MessageDigest"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<% List<Module> topicos = (List<Module>)request.getAttribute("topicos");%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Resposta Certa</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/Home.css" rel="stylesheet">
        <link href="css/jquery.dataTables.min.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <img alt="Logo do site" src="imgs\teste.jpg">
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h2>
                        Resposta Certa
                    </h2>

                    <p>A escolha correta na hora de estudar para concursos e vestibulares.</p><br>

                    <p>Neste site você encontrará quiz estudantis sobre as mais diversas áreas do conhecimento, tornando essa a ferramenta ideial para seus estudos. Caso deseje, todos as questões tem diretos autorais livre, sinta-se a vontade para usa-la em suas provas.</p>

                    <p>
                        <a class="btn" href="#">Saiba mais sobre nós</a>
                    </p>
                    <table id="topicos" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>Topicos mais visitados</th>                               
                            </tr>
                        </thead>
                        <tfoot style="display:none">
                            <tr>
                                <th>Topicos mais visitados</th>     
                            </tr>
                        </tfoot>
                        <tbody>
                          <% Iterator<Module> it = topicos.iterator();%>
                            <% while(it.hasNext()){ %>
                            <tr>
                                <td><a href="#"><%=it.next().getNomeModulo()%></a></td>
                            </tr>
                          <% } %>
                        </tbody>
                    </table>
                        <br><br>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/Home.js"></script>
</body>
</html>
