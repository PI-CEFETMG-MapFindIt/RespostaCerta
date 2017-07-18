<%-- Author: Pedro Almeida --%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Topic"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<%List<Topic> topico = (List<Topic>) request.getAttribute("topico");%>
<%Question questao = (Question) request.getAttribute("questao");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum: <%=questao.getTituloQuestao()%></title>
    </head>
    <body>
        <%@include file="/Menu.jsp"%>
        <div class="container-fluid">    
            <div class="row">
                <div class="col-md-8 col-md-offset-2 text-center">
                    <h3>
                        <b><%=questao.getTituloQuestao()%></b>
                    </h3>
                    <div class="row">
                        <div class="col-md-12 text-left">
                            <% for (Topic top : topico) {%>
                                <%User autor = top.getAutor();%>
                                <p></p>
                                <a href="/RespostaCerta/ControllerServlet?control=TopicoQuestao&id=<%=top.getTopicoId()%>">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color: #7F7F7F">
                                            <div class="row">
                                                <div class="col-md-5 text-right" style="color: white">
                                                    <%=autor.getNomeUsuario()%>
                                                </div>
                                                <div class="col-md-5 text-left" style="color: white">
                                                    <%=top.getDataPostagem()%>
                                                </div>
                                                    <% if(((Long)request.getSession().getAttribute("usuario")).equals(autor.getIdUsuario())){%>
                                                <div class="col-md-2 text-right">
                                                    <a href="/RespostaCerta/ControllerServlet?control=ExcluirTopico&id=<%=top.getTopicoId()%>" class="excluirTopico">Excluir Tópico</a>
                                                </div>
                                                <%}%>
                                            </div>
                                        </div>
                                        <div class="panel-body text-left">
                                            <div class="row">
                                                <h4>
                                                    <div class="col-md-12">
                                                        <%=top.getTxtMensagem()%>
                                                    </div>
                                                </h4>
                                            </div>
                                            <div class="row text-center">
                                                <br>
                                                <div class="col-md-12">
                                                    <% if(top.getMsgPhoto()!=null){%>
                                                        <img src="/RespostaCerta/ImageServlet?tipo=topic&id=<%=top.getTopicoId()%>">
                                                    <%}%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>            
                            <%}%>
                            <%  if (request.getSession().getAttribute("usuario") != null) { %>
                                <div class="text-center">
                                    <a href="#modalForum" data-toggle="modal" class="btn btn-default" type="submit"
                                    style="border-radius: 0px; background-color: #7F7F7F; 
                                    color: white">Criar novo Tópico</a>
                                </div>
                                <br>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%  if (request.getSession().getAttribute("usuario") != null) { %>                    
            <div id="modalForum" class="modal fade" role="dialog">
                <div  class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" style="background-color: #7F7F7F;">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <div class="text-center">    
                                <h4 class="modal-title" style="color:white">Mensagem</h4>
                            </div>
                        </div>
                        <br>
                        <form id="formTopico" action="/RespostaCerta/ControllerServlet?control=ForumCriar&id=<%=questao.getIdQuestao()%>" method="POST">    
                            <div class="modal-body text-center">
                                <div class="form-group">
                                    <textarea class="form-control" rows="5" name="mensagem" 
                                    style="resize: none"></textarea>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-btn">
                                            <span class="btn btn-default btn-file" style="border-radius: 0px; 
                                            background-color: black; color: white">Inserir Imagem 
                                                <input accept="image/*" type="file" id="imgInp">
                                            </span>
                                        </span>
                                    </div>
                                    <br>
                                    <input type="hidden" id="blob" name="blob">
                                    <img id='novaImg'/>
                                </div> 
                            </div>
                            <div class="modal-footer" style="background-color: #7F7F7F;">  
                                <div class="text-center">    
                                    <button class="btn btn-default" type="button" onclick="setTimeout(function(){$('#formTopico').submit();}, 1000)"
                                    style="border-radius: 0px; background-color: black; 
                                    color: white">Inserir</button>
                                    <button class="btn btn-default" data-dismiss="modal"
                                    style="border-radius: 0px; background-color: black; 
                                    color: white">Cancelar</button>
                                </div>
                            </div>
                        </form>        
                    </div>
                </div>
            </div>
        <% } %>    
        <link href="css/Forum.css" rel="stylesheet">
        <script src="js/Forum.js"></script>
    </body>
</html>
