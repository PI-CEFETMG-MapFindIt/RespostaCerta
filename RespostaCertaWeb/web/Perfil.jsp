<%--Author: Pedro Almeida, Vitor Rodarte, João Vitor--%>

<!DOCTYPE html>
<% User user = (User)(request.getAttribute("usuario"));%>
<% String firstName = user.getNomeUsuario().split(" ")[0];%> //'substring' gera erro com indice maior que string
<% String lastName = user.getNomeUsuario().split(" ")[user.getNomeUsuario().split(" ").length-1];%> //teve que usar split
<% String email = user.getLoginUsuario();%>
<% String msg = (String)request.getAttribute("mensagem");%>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Perfil</title>
    </head>
    <body>
        <%@include file="/Menu.jsp"%>
        <br><br><br>
        <div class="container-fluid">
            <h1 class="text-center">Perfil</h1>
            <div class="row" aling="center">
                <div class="col-md-3">
                    <div class="display">
                        <% if(user.getFotoUsuario()!=null){%>
                            <img src="/RespostaCerta/ImageServlet?tipo=user&id=<%=user.getIdUsuario()%>">
                        <%}%>
                        <button type="button" class="btn button" data-toggle="modal" href="#modalAlteraImagem">Alterar Imagem</button>	
                    </div>										
                </div>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Nome</h3> <div class="texto"><%=firstName%></div>
                        </div>
                        <div class="col-md-6">
                            <h3>Sobrenome</h3> <div class="texto"><%=lastName%></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" style="margin-top:30px;">
                            <h3>Email</h3> <div class="texto"><%=email%></div>
                        </div>
                    </div>
                    <br>
                    <h5><%=msg%></h5>
                    <div class="row" style="margin-top:50px;">
                        <div class="col-md-12">
                            <button type="button" class="btn button" id="btnDesempenho">Desempenho</button>
                            <button type="button" class="btn button" href="#modalAltDados" data-toggle="modal">Alterar dados</button>
                            <button type="button" class="btn button" href="#modalAltSenha" data-toggle="modal">Alterar senha</button>
                            <button type="button" class="btn button" href="#modalConfirmDel" data-toggle="modal">Deletar conta</button>
                        </div>	
                    </div>
                </div>
            </div>
        </div>
                    
        <!-- Modal Altera Dados -->            
        <div id="modalAltDados" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Alterar Dados</h4>
                    </div>
                    <div class="modal-body">
                        <form id="alteraDados" action="/RespostaCerta/ControllerServlet?control=AlteraDados" method="POST">
                            <div class="form-group">
                                <label for="alteraEmailUsuario">E-mail</label>
                                <input class="form-control" placeholder="<%=user.getLoginUsuario()%>" type="email" name="alteraEmailUsuario" id="alteraEmailUsuario">
                            </div>
                            <div class="form-group">
                                <label for="alteraNomeUsuario">Nome</label>
                                <input class="form-control" placeholder="<%=user.getNomeUsuario()%>" type="text" name="alteraNomeUsuario" id="alteraNomeUsuario">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" id="btnAlteraDados">Salvar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        
                            
        <!-- Modal Altera Senha -->
        <div id="modalAltSenha" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Alterar Senha</h4>
                    </div>
                    <div class="modal-body">
                        <form id="alteraSenha" action="/RespostaCerta/ControllerServlet?control=AlteraSenha" method="POST">
                            <div class="form-group">
                                <label for="senhaAtualUsuario">Senha Atual</label>
                                <input class="form-control" placeholder="Digite senha atual" type="password" name="senhaAtualUsuario" id="senhaAtualUsuario" required="required">
                            </div>
                            <div class="form-group">
                                <label for="novaSenhaUsuario">Nova Senha</label>
                                <input class="form-control" placeholder="Digite nova senha" type="password" name="novaSenhaUsuario" id="novaSenhaUsuario" required="required">
                            </div>
                            <div class="form-group">
                                <label for="repitaSenha">Repita a Senha</label>
                                <input class="form-control" placeholder="Repita a senha" type="password" name="repitaSenha" id="repitaSenha" required="required">
                            </div>
                            <div id="statusSenha"></div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" id="btnAlteraSenha" type="button">Salvar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Confirma exclusão -->
        <div id="modalConfirmDel" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Excluir Conta</h4>
                    </div>
                    <div class="modal-body">
                        <h4>Confirma a exclusão da conta?</h4>
                        <h6>Clicando em "Confirma" sua conta será excluida, e todos os seus dados serão perdidos.</h6>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" id="btnDeletaConta" type="button">Confirmar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Altera Imagem -->
        <div id="modalAlteraImagem" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Alterar Imagem</h4>
                    </div>
                    <div class="modal-body">
                        <form id="alteraImagem" action="/RespostaCerta/ControllerServlet?control=AlteraImagem" method="POST">
                            <div class="form-group" align="center">
                                <div class="input-group">
                                    <span class="input-group-btn">
                                        <span class="btn btn-default btn-file">
                                            Selecionar Imagem<input accept="image/*" type="file" id="imgInp" name="imgInp">
                                        </span>
                                    </span> 
                                </div>
                                <br>
                                <input type="hidden" id="blob" name="blob">
                                <img id='novaImg'/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" type="button" id="btnAlteraImagem">Confirmar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <link href="css/Perfil.css" rel="stylesheet">
        <script src="js/cropper.min.js"></script>
        <script src="js/Perfil.js"></script>
    </body>
</html>
