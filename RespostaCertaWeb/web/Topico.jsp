<%--Author: Pedro Almeida--%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Topic"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.Question"%>
<%@page import="br.cefetmg.respostaCerta.model.domain.TopicAnswer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%Topic topico = (Topic) request.getAttribute("topico");%>
<%Question questao = (Question) request.getAttribute("questao");%>
<%List<TopicAnswer> resposta = (List<TopicAnswer>) request.getAttribute("respostas");%>
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
                            <%User autor = topico.getAutor();%>
                            <p></p>
                            <div class="panel panel-default">
                                <div class="panel-heading" style="background-color: #7F7F7F">
                                    <div class="row" style="color: white">
                                        <div class="col-md-6 text-right">
                                            <%=autor.getNomeUsuario()%>
                                        </div>
                                        <div class="col-md-6 text-left">
                                            <%=topico.getDataPostagem()%>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body text-left">
                                    <div class="row">
                                        <h4>
                                            <div class="col-md-12">
                                                <%=topico.getTxtMensagem()%>
                                            </div>
                                        </h4>
                                    </div>
                                    <div class="row text-center">
                                        <br>
                                        <div class="col-md-12">
                                            <% if(topico.getMsgPhoto()!=null){%>
                                                <img src="/RespostaCerta/ImageServlet?tipo=topic&id=<%=topico.getTopicoId()%>">
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                  
                        <div class="row">
                            <div class="col-md-12 text-left">
                            <%if(resposta!=null) {%>    
                                <% for (TopicAnswer res : resposta) {%>
                                <%User autorRes = res.getAutor();%>
                                <p></p>
                                <div class="panel panel-default">
                                    <div class="panel-heading" style="background-color: black">
                                        <div class="row" style="color: white">
                                            <div class="col-md-6 text-right">
                                                <%=autorRes.getNomeUsuario()%>
                                            </div>
                                            <div class="col-md-6 text-left">
                                                <%=res.getDataResposta()%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body text-left">
                                        <div class="row">
                                            <h4>
                                                <div class="col-md-12">
                                                    <%=res.getTxtMensagem()%>
                                                </div>
                                            </h4>
                                        </div>
                                    </div>
                                </div>            
                        <%}}%>
                            </div>
                        </div>                                   
                        <br>                 
                        <%  if (request.getSession().getAttribute("usuario") != null) { %>
                            <div class="text-center">
                                <a href="#modalForum" data-toggle="modal" class="btn btn-default" type="submit"
                                style="border-radius: 0px; background-color: black; 
                                color: white">Responder Tópico</a>
                            </div>
                            <br>
                        <% } %>
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
                        <form action="/RespostaCerta/ControllerServlet?control=TopicoCriar&id=<%=topico.getTopicoId()%>" method="POST">    
                            <div class="modal-body text-center">
                                <div class="form-group">
                                    <textarea class="form-control" rows="5" name="mensagem" 
                                    style="resize: none"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer" style="background-color: #7F7F7F;">  
                                <div class="text-center">    
                                    <button class="btn btn-default" type="submit"
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
    </body>
</html>
