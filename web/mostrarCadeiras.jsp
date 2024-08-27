

<%-- 
    Document   : mostrarCadeiras
    Created on : Jul 24, 2024, 11:45:29 PM
    Author     : raulv
--%>



<%@page import="br.edu.ifpe.recife.models.repositories.CadeiraRepository"%>
<%@page import="br.edu.ifpe.recife.models.entites.Cadeira"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tagsIF" uri="http://www.ifpe.edu.br/carrega" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        
        <tagsIF:carrega entidade="cadeira" var="cad" escopo="pagina"/>

        <h1>Cadeiras Cadastradas</h1>
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
            <tr><td>Código</td><td>Nome</td><td>Ano</td><td>Semestres</td><td>Descrição</td><td>Operações</td></tr>

            <% for (Cadeira c : (List<Cadeira>) pageContext.getAttribute("cad")) { %>

            <tr>
                <td><%= c.getCodigo()%></td>
                <td><%= c.getNome()%></td>
                <td><%= c.getAno()%></td>
                <td><%= c.getSemestre()%></td>
                <td><%= c.getDescricao()%></td>
                <td>
                    <a href="CadeiraController?codigo=<%= c.getCodigo()%>&op=detail">Detalhar</a>
                    <a href="CadeiraController?codigo=<%= c.getCodigo()%>&op=edit">Alterar</a>
                    <a href="CadeiraController?codigo=<%= c.getCodigo()%>&op=delete">Deletar</a>
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
                        <%@include file="cadastroCadeira.jsp"%>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
