<%-- 
    Document   : mostrarEstudantes
    Created on : Jul 24, 2024, 12:49:04 AM
    Author     : raulv
--%>

<%@page import="java.util.List"%>
<%@page import="br.edu.ifpe.recife.models.entites.Estudante"%>
<%@page import="br.edu.ifpe.recife.models.repositories.EstudanteRepository"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tagsIF" uri="http://www.ifpe.edu.br/carrega" %> <!-- tagLib -->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <!--usandooooo a tag -->
       
        <tagsIF:carrega entidade="estudante" var="est" escopo="pagina"/>
    <%-- <p>${pageScope.est.size()}</p> mostrar o total de cadastrados--%>
        <h1>Estudantes Cadastrados</h1>
        ${(param.codigo eq null)?'':'<a href="index.html">Voltar ao Inicio</a></br>'}
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
            <tr><td>Código</td><td>Nome</td><td>e-mail</td><td>Ano de Entrada</td><td>Operações</td></tr>

             <!-- for com a tag -->
            <% for (Estudante e : (List<Estudante>) pageContext.getAttribute("est")) { %>

            <tr>
                <td><%= e.getCodigo()%></td>
                <td><%= e.getNome()%></td>
                <td><%= e.getEmail()%></td>
                <td><%= e.getAnoEntrada()%></td>
                <td>
                    <a href="EstudanteController?codigo=<%= e.getCodigo()%>&op=detail">Detalhar</a>
                    <a href="EstudanteController?codigo=<%= e.getCodigo()%>&op=edit">Alterar</a>
                    <a href="EstudanteController?codigo=<%= e.getCodigo()%>&op=delete">Deletar</a>
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
                        <%@include file="cadastroEstudante.jsp"%>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
