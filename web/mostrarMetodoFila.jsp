<%-- 
    Document   : mostrarMetodoFila
    Created on : Jul 25, 2024, 1:47:05 AM
    Author     : raulv
--%>

<%@page import="java.util.List"%>
<%@page import="br.edu.ifpe.recife.models.repositories.FilaRepository"%>
<%@page import="br.edu.ifpe.recife.models.entites.MetodoFila"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tagsIF" uri="http://www.ifpe.edu.br/carrega" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

       
        <tagsIF:carrega entidade="metodoFila" var="metF" escopo="pagina"/>

        <h1>Metodos de Fila Cadastrados</h1>
        <h2>${cookie['mC'].value}</h2>

        <button class="btn btn-primary"
                data-bs-toggle="modal" data-bs-target="#modalCadastro">Cadastrar</button>
        <a href="index.html">Voltar ao Início</a>

        <%
            String mensagem = (String) session.getAttribute("msg");

            if (mensagem != null) {
        %>
        <small><%= mensagem%></small> 
        <%

                session.removeAttribute("msg");
            }
        %>

        <table class="table">
            <tr><td>Código</td><td>Descrição Curta</td><td>Descrição Longa</td><td>Operações</td></tr>

            <% for (MetodoFila mf : (List<MetodoFila>) pageContext.getAttribute("metF")) { %>

            <tr>
                <td><%= mf.getCodigo()%></td>
                <td><%= mf.getDescricaoCurta()%></td>
                <td><%= mf.getDescricaoLonga()%></td>
                <td>
                    <a href="MetodoFilaController?codigo=<%= mf.getCodigo()%>&op=detail">Detalhar</a>
                    <a href="MetodoFilaController?codigo=<%= mf.getCodigo()%>&op=edit">Alterar</a>
                    <a href="MetodoFilaController?codigo=<%= mf.getCodigo()%>&op=delete">Deletar</a>
                </td>
            </tr>

            <% } %>
        </table>

        <div class="modal fade" id="modalCadastro" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <%@include file="cadastroMetodoFila.jsp"%>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>