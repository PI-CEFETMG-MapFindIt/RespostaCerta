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
<<<<<<< HEAD
=======

        <link href="css/Home.css" rel="stylesheet">
        <link href="css/jquery.dataTables.min.css" rel="stylesheet">

>>>>>>> e554eaf525745eff4b9891ed333c901341395e14
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
                                <%Module m = it.next();%>
                                <td><a href="/RespostaCerta/ControllerServlet?control=QuestoesModulo&id=<%=m.getIdModulo()%>"><%=m.getNomeModulo()%></a></td>
                            </tr>
                          <% } %>
                        </tbody>
                    </table>
                        <br><br>
                </div>
            </div>
        </div>
<<<<<<< HEAD

    </body>
</html>
=======
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/Home.js"></script>
</body>
</html>
>>>>>>> e554eaf525745eff4b9891ed333c901341395e14
