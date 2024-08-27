<%-- 
    Document   : detalheMetodoFila
    Created on : Jul 25, 2024, 1:44:28 AM
    Author     : raulv
--%>

<%@page import="br.edu.ifpe.recife.models.entites.MetodoFila"%>
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
        <h1>Detalhe dos metodos</h1>
        ${(param.codigo eq null)?'':'<a href="mostrarMetodoFila.jsp">Mostrar Metodos de Fila</a></br>'}
               
        <table class="table">
            <tr>
                <td>Código</td>
                <td>${requestScope.metodo.codigo}</td>
            </tr>
            
            <tr>
                <td>Descrição Curta</td>
                <td>${requestScope.metodo.descricaoCurta}</td>
            </tr>
           
             <tr>
                <td>Descrição Longa</td>
                <td>${requestScope.metodo.descricaoLonga}</td>
            </tr>
           
        </table>
    </body>
</html>