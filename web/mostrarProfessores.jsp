<%-- 
    Document   : mostrarProfessores
    Created on : Jul 25, 2024, 12:46:48 AM
    Author     : raulv
--%>

<%@page import="br.edu.ifpe.recife.models.repositories.ProfessorRepository"%>
<%@page import="br.edu.ifpe.recife.models.entites.Professor"%>

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

    
        <tagsIF:carrega entidade="professor" var="pro" escopo="pagina"/>

        <h1>Professores Cadastrados</h1>
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
            <tr><td>Código</td><td>Nome</td><td>e-mail</td><td>Operações</td></tr>

            <% for (Professor pro : (List<Professor>) pageContext.getAttribute("pro")) { %>

            <tr>
                <td><%= pro.getCodigo()%></td>
                <td><%= pro.getNome()%></td>
                <td><%= pro.getEmail()%></td>
                <td>
                    <a href="ProfessorController?codigo=<%= pro.getCodigo()%>&op=detail">Detalhar</a>
                    <a href="ProfessorController?codigo=<%= pro.getCodigo()%>&op=edit">Alterar</a>
                    <a href="ProfessorController?codigo=<%= pro.getCodigo()%>&op=delete">Deletar</a>
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
                        <%@include file="cadastroProfessor.jsp"%>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>