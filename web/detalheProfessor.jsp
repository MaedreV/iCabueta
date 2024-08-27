<%-- 
    Document   : detalheProfessor
    Created on : Jul 25, 2024, 12:45:52 AM
    Author     : raulv
--%>
<%@page import="br.edu.ifpe.recife.models.entites.Professor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    </head>
    <body>
        <h1>Detalhe do Professor</h1>
        ${(param.codigo eq null)?'':'<a href="mostrarProfessores.jsp">Mostrar Professores</a></br>'}
               
        <table class="table">
            <tr>
                <td>Código</td>
                <td>${requestScope.professor.codigo}</td>
            </tr>
            <tr>
                <td>Nome</td>
                <td>${requestScope.professor.nome}</td>
            </tr>
       
            <tr>
                <td>e-mail</td>
                <td>${requestScope.professor.email}</td>
            </tr>
         
           
           
        </table>
    </body>
</html>
