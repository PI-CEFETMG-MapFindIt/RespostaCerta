<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Resposta Certa</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/Home.css" rel="stylesheet">

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
                    <h3>
                        Topicos mais visitados
                    </h3>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="panel panel-default">

                                <div class="panel-body">
                                    Matematica
                                </div>
                                <div class="panel-body">
                                    Fisca
                                </div>
                                <div class="panel-body">
                                    Calculo
                                </div>
                                <div class="panel-body">
                                    Geometria
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="panel panel-default">

                                <div class="panel-body">
                                    Romantismo
                                </div>
                                <div class="panel-body">
                                    Guerra Fria
                                </div>
                                <div class="panel-body">
                                    Brasil republica
                                </div>
                                <div class="panel-body">
                                    Banco de Dados
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="panel panel-default">

                                <div class="panel-body">
                                    JAVA
                                </div>
                                <div class="panel-body">
                                    Portugues
                                </div>
                                <div class="panel-body">
                                    Deseign pattern
                                </div>
                                <div class="panel-body">
                                    Collections
                                </div>
                            </div>
                        </div>
                    </div>
                    <ul class="pagination">
                        <li>
                            <a href="#">Prev</a>
                        </li>
                        <li>
                            <a href="#">1</a>
                        </li>
                        <li>
                            <a href="#">2</a>
                        </li>
                        <li>
                            <a href="#">3</a>
                        </li>
                        <li>
                            <a href="#">4</a>
                        </li>
                        <li>
                            <a href="#">5</a>
                        </li>
                        <li>
                            <a href="#">Next</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/Home.js"></script>
</body>
</html>
