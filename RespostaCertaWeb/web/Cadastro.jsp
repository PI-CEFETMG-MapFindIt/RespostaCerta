<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Cadastro</title>

        <meta name="description" content="Source code generated using layoutit.com">
        <meta name="author" content="LayoutIt!">
        <link href="css/cropper.min.css" rel="stylesheet">
        <link href="css/Cadastro.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-1">
                </div>
                <div class="col-md-6">
                    <br><br><br><br>
                    <form id="formCadastro" method="POST" action="/RespostaCerta/ControllerServlet?control=Cadastrar">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="primNome" id="primNome" class="form-control input-lg" placeholder="Primeiro nome" tabindex="1" required>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="ultimoNome" id="ultimoNome" class="form-control input-lg" placeholder="Ultimo nome" tabindex="2" required>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="email" id="emailCad" class="form-control input-lg" placeholder="E-mail" tabindex="3" required>
                        </div>
                        <div class="form-group">
                            <input type="email" name="emailConf" id="emailConf" class="form-control input-lg" placeholder="Confirmar E-mail" tabindex="4" required>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Senha" tabindex="5" required>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" required placeholder="Confirmar Senha" tabindex="6">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="datetimepicker1">Data de Nascimento</label>
                            <div class='input-group date' id='datetimepicker1'>
                                <input type='text' name="nascimento" id="dataNascimento"
                                       placeholder="__/__/____" required class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-default btn-file">
                                        Escolher Imagem de Perfil <input accept="image/*" type="file" id="imgInp">
                                    </span>
                                </span>
                            </div>
                            <br>
                            <input type="hidden" id="blob" name="blob">
                            <img id='novaImg'/>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <div class="radio">
                                        <label><input type="radio" name="tipo" id="prof" value="E">Professor</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <div class="radio">
                                        <label><input type="radio" id="aluno" name="tipo" value="A">Aluno</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 col-sm-9 col-md-9">
                                <div class="checkbox">
                                    <label><input type="checkbox" name="aceito" id="aceito" value="">Eu aceito os <a href="#" data-toggle="modal" data-target="#termosCondicoes">Termos e Condições</a></label>
                                </div>
                            </div>
                        </div>
                        <button type="button" onclick="validateCadastro();" class="btn btn-success"> Confirmar </button>
                        <button type="button" data-dismiss="modal" class="btn btn-default"> Cancelar </button>
                    </form>
                </div>
                <div class="col-md-1">
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </div>
<script src="js/jquery.mask.min.js"></script>
<script src="js/cropper.min.js"></script>
<script src="js/Menu.js"></script>
<script src="js/Cadastro.js"></script>
</body>
</html>
