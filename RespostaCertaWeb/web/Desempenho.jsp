<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Resposta Certa</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
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
                                    <a href="#panel-712485" data-toggle="tab">Pontuação</a>
                                </li>
                                <li>
                                    <a href="#panel-254705" data-toggle="tab">Histórico</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="panel-712485">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h1 class="text-center">
                                                Pontuação
                                            </h1>
                                        </div>
                                    </div>
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>
                                                    Matéria
                                                </th>
                                                <th>
                                                    Módulo
                                                </th>
                                                <th>
                                                    Taxa de acerto
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="success">
                                                <td>
                                                    MatemÃ¡tica
                                                </td>
                                                <td>
                                                    Trigonometria
                                                </td>
                                                <td>
                                                    60% (12/20)
                                                </td>
                                            </tr>
                                            
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-md-12 text-center">
                                            <ul class="pagination">
                                                <li>
                                                    <a href="#"><</a>
                                                </li>
                                                <li>
                                                    <a href="#">1</a>
                                                </li>
												<li>
                                                    <a href="#">></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="panel-254705">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h1 class="text-center">
                                                Histórico
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
                                                    Matéria
                                                </th>
                                                <th>
                                                    Módulo
                                                </th>
                                                <th>
                                                    Avaliação
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="success">
                                                <td>
                                                    02/05/2017 - 14:30
                                                </td>
                                                <td>
                                                    FÃ­sica
                                                </td>
                                                <td>
                                                    Ondas sonoras
                                                </td>
                                                <td>
                                                    Certa
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-md-12 text-center">
                                            <ul class="pagination">
                                                <li>
                                                    <a href="#"><</a>
                                                </li>
                                                <li>
                                                    <a href="#">1</a>
                                                </li>
                                                <li>
                                                    <a href="#">></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/Desempenho.js"></script>
    </body>
</html>
