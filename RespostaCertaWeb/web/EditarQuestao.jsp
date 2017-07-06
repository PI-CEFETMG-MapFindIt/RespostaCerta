<%@page import="br.cefetmg.respostaCerta.model.domain.ClosedQuestion"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Subject"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Module"%>
<!DOCTYPE html>
<% List<Module> modulos = (List<Module>) request.getAttribute("modulos");
    List<Subject> dominios = (List<Subject>) request.getAttribute("dominios");
    Question q = (Question) request.getAttribute("questao");
%>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Cadastro de Questões</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/CadastroQuestao.css" rel="stylesheet">
        <link href="css/cropper.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Menu.jsp" %>
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h2>Editar Questão</h2>
                <form id="formCadastro" method="POST" action="/RespostaCerta/ControllerServlet?control=EditarQuestao">
                    <div class="form-group">
                        <label for="modulo"><h4>Modulo da Questão</h4></label>
                        <select id="modulo" name="modulo" class="form-control">
                            <% for (Module modulo : modulos) {%>
                            <option value="<%=modulo.getIdModulo()%>" <%if (q.getModulo().getIdModulo() == modulo.getIdModulo()) {%> selected <%}%>><%=modulo.getNomeModulo()%></option>
                            <% }%>
                        </select>
                        <br>
                        <button class="btn btn-default" type="button" data-toggle="modal" data-target="#modalModulo">Adicionar Modulo</button>
                    </div>   
                    <div class="form-group">
                        <label for="titulo"><h4>Titulo da Questão</h4></label><br>
                        <input type="text" class="form-control" name="titulo" id="titulo" placeholder="Digite o titulo da questão" value="<%=q.getTituloQuestao()%>">
                    </div>
                    <div class="form-group">
                        <label for="enunciado"><h4>Enunciado da Questão</h4></label>
                        <textarea class="form-control" rows="5" name="enunciado" id="enunciado" placeholder="Digite o enunciado da questão"><%=q.getEnunciadoQuestao()%></textarea>
                    </div>
                    <% if(q.getQuestPhoto()!=null){%>
                    <div class="form-group">
                        <img src="/RespostaCerta/ImageServlet?tipo=quest&id=<%=q.getIdQuestao()%>">
                    </div>    
                    <%}%>
                    <div class="form-group" height="800px">
                        <div class="input-group">
                            <span class="input-group-btn">
                                <span class="btn btn-default btn-file">
                                    Escolher Imagem... <input accept="image/*" type="file" id="imgInp">
                                </span>
                            </span>
                        </div>
                        <br>
                        <input type="hidden" id="blob" name="blob" value="">
                        <img id='novaImg'/>
                    </div>    
                    <div class="form-group">
                        <label><h4>Dificuldade da Questão</h4></label>
                        <div class="radio">
                            <label><input type="radio" id="facil" name="difQuestao" value="Facil" <%if (q.getIdtDificuldade() == 'F') {%>checked<% } %>>Fácil</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" id="moderada" name="difQuestao" value="Moderada" <%if (q.getIdtDificuldade() == 'M') {%>checked<% }%>>Moderada</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" id="dificl" name="difQuestao" value="Dificil" <%if (q.getIdtDificuldade() == 'D') {%>checked<% }%>>Difícil</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" id="desafio" name="difQuestao" value="Desafio" <%if (q.getIdtDificuldade() == 'X') {%>checked<% }%>>Desafio</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label><h4>Tipo da Questão</h4></label>
                        <div class="radio">
                            <label><input type="radio" id="aberta" name="tipoQuestao" value="Discursiva" <%if (q.isIdtQuestao()) {%>checked<% }%>>Discursiva</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" id="fechada" name="tipoQuestao" value="Objetiva" <%if (!q.isIdtQuestao()) {%>checked<% }%>>Objetiva</label>
                        </div>
                    </div>
                    <% if (!q.isIdtQuestao()) {
                            ClosedQuestion clQ = (ClosedQuestion) q;
                    %>
                    <div class="form-group" id = "divAlternativas"> <label> < h4 > Digite as alternativas e selecione a alternativa correta<h4></label
                        > <div 
                            class="col-md-12">
                            <br
                                > </div
                        > <div 
                            class="radio">
                            <label class="col-md-12"><input type = "radio" id = "op1" name = "altQuestao" value = "1" <%if (clQ.getCorreta() == 1) {%>selected<%}%>> <textarea 
                                    class="form-control" rows = "2" id = "alternativa1" name = "alternativa1" placeholder = "Digite a primeira alternativa" > <%= clQ.getAlt1()%> < / textarea > < / label
                                    > < / div
                                    > <div 
                                        class="col-md-12">
                                        <br
                                            > < / div
                                        > <div 
                                            class="radio">
                                            <label class="col-md-12"><input type = "radio" id = "op2" name = "altQuestao" value = "2" <%if (clQ.getCorreta() == 2) {%>selected<%}%> > <textarea 
                                                    class="form-control" rows = "2" id = "alternativa2" name = "alternativa2" placeholder = "Digite a segunda alternativa" > <%= clQ.getAlt2()%> < / textarea > < / label
                                                    > < / div
                                                    > <div 
                                                        class="col-md-12">
                                                        <br
                                                            > < / div
                                                        > <div 
                                                            class="radio">
                                                            <label class="col-md-12"><input type = "radio" id = "op3" name = "altQuestao" value = "3" <%if (clQ.getCorreta() == 3) {%>selected<%}%>> <textarea 
                                                                    class="form-control" rows = "2" id = "alternativa3" name = "alternativa3" placeholder = "Digite a terceira alternativa" > <%= clQ.getAlt3()%> < / textarea > < / label
                                                                    > < / div
                                                                    > <div 
                                                                        class="col-md-12">
                                                                        <br
                                                                            > < / div
                                                                        > <div 
                                                                            class="radio">
                                                                            <label class="col-md-12"><input type = "radio" id = "op4" name = "altQuestao" value = "4" <%if (clQ.getCorreta() == 4) {%>selected<%}%> > <textarea 
                                                                                    class="form-control" rows = "2" id = "alternativa4" name = "alternativa4" placeholder = "Digite a quarta alternativa" > <%= clQ.getAlt4()%> < / textarea > < / label
                                                                                    > < / div
                                                                                    > <div 
                                                                                        class="col-md-12">
                                                                                        <br
                                                                                            > < / div
                                                                                        > <div 
                                                                                            class="radio">
                                                                                            <label class="col-md-12"><input type = "radio" id = "op5" name = "altQuestao" value = "5" <%if (clQ.getCorreta() == 5) {%>selected<%}%>> 
                                                                                                <textarea class="form-control" rows = "2" id = "alternativa5" name = "alternativa5" placeholder = "Digite a quinta alternativa" > <%= clQ.getAlt5()%> </textarea> 
                                                                                            </label> 
                                                                                        </div> 
                                                                                        
                                                                                    </div>
                                                                                            <%
                                                                                            }%>
                                                                                    <input type="hidden" name="idtNovo" id="idtNovo" value="0"/>
                                                                                    <input type="hidden" name="disciplina" id="novaDisciplina"/>
                                                                                    <input type="hidden" name="modulo" id="novoModulo"/>
                                                                                    <input type="hidden" name="idDisciplina" id="novoModuloIdDisciplina"/>
                                                                                    <input type="hidden" name="idQuestao" value="<%=q.getIdQuestao()%>"/>
                                                                                    <div class="form-group">
                                                                                        <button class="btn btn-default">Atualizar</button>
                                                                                    </div>
                                                                                    </form>
                                                                                    <div id="modalModulo" class="modal fade" role="dialog">
                                                                                        <div class="modal-dialog">
                                                                                            <div class="modal-content">
                                                                                                <div class="modal-header">
                                                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                                                    <h4 class="modal-title">Adicionar Modulo</h4>
                                                                                                </div>
                                                                                                <div class="modal-body">
                                                                                                    <form id="adicionarDisciplina">
                                                                                                        <div class="form-group">
                                                                                                            <label for="nomeModulo">Nome do Novo Modulo</label>
                                                                                                            <input class="form-control" placeholder="Digite o nome do modulo" type="text" id="nomeModulo" name="nomeModulo">
                                                                                                            <div class="form-group">
                                                                                                                <label for="disciplina"><h4>Disciplina</h4></label>
                                                                                                                <select id="disciplina" name="disciplina" class="form-control">
                                                                                                                    <% for (Subject dominio : dominios) {%>
                                                                                                                    <option value="<%=dominio.getIdDominio()%>"><%=dominio.getNomeDominio()%></option>
                                                                                                                    <% }%>
                                                                                                                </select>
                                                                                                                <br>
                                                                                                                <button class="btn btn-default" type="button" data-toggle="modal" data-target="#modalDisciplina">Adicionar Disciplina</button>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                        <button type="button" class="btn btn-default" onClick="salvarModulo();">Salvar</button>
                                                                                                    </form>
                                                                                                </div>
                                                                                                <div class="modal-footer">
                                                                                                    <button type="button" id="fecharModulo" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div id="modalDisciplina" class="modal fade" style="top:60px; left:-15px" role="dialog">
                                                                                        <div class="modal-dialog">
                                                                                            <div class="modal-content">
                                                                                                <div class="modal-header">
                                                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                                                    <h4 class="modal-title">Adicionar Disciplina</h4>
                                                                                                </div>
                                                                                                <div class="modal-body">
                                                                                                    <form id="adicionarDisciplina">
                                                                                                        <div class="form-group">
                                                                                                            <label for="nomeDisciplina">Nome da nova Disciplina</label>
                                                                                                            <input class="form-control" placeholder="Digite o nome da disciplina" type="text" name="nomeDisciplina" id="nomeDisciplina">
                                                                                                        </div>
                                                                                                        <button type="button" class="btn btn-default" onclick="salvarDisciplina();">Salvar</button>
                                                                                                    </form>
                                                                                                </div>
                                                                                                <div class="modal-footer">
                                                                                                    <button type="button" id="fecharDisiciplina" class="btn btn-default" data-dismiss="modal">Fechar</button>
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
                                                                                    <script src="js/cropper.min.js"></script>

                                                                                    <script src="js/CadastroQuestao.js"></script>

                                                                                    </body>
                                                                                    </html>
