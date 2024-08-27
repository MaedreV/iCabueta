<%-- 
    Document   : cadastroMetodoFila
    Created on : Jul 25, 2024, 1:31:59 AM
    Author     : raulv
--%>

<%@page import="br.edu.ifpe.recife.models.repositories.FilaRepository"%>
<%@page import="br.edu.ifpe.recife.models.entites.MetodoFila"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${(param['codigo'] eq null)?'Cadastro':'Alterar Dados'} dos Metodos de Fila</h1>
        ${(param.codigo eq null)?'':'<a href="mostrarMetodos.jsp">Mostrar Cadeiras</a></br>'}
        
        <% 
            
            if(request.getParameter("codigo")!=null){
                MetodoFila mf = FilaRepository
                    .read(Integer.parseInt(request
                            .getParameter("codigo")));
                request.setAttribute("metodo", mf);
            }
        %>
        
        <form method="post" action="MetodoFilaController">
            
            ${(requestScope.metodo ne null)?'<input type="hidden" name="op" value="1"/>':''}
            
            <table class="table">
                
                <tr><td>Código</td><td><input class="form-control" type="${(requestScope.metodo ne null)?'hidden':'text'}" name="codigo" ${(requestScope.metodo ne null)?'value="'.concat(requestScope.metodo.codigo).concat('"'):''}/>
            ${(requestScope.metodo ne null)?requestScope.metodo.codigo:''}</td></tr>
                <tr><td>Descrição Curta</td><td><textarea class="form-control" name="descricaoCurta">${(requestScope.metodo ne null)?requestScope.metodo.descricaoCurta:''}</textarea></td></tr>
                <tr><td>Descrição Longa</td><td><textarea class="form-control" name="descricaoLonga">${(requestScope.metodo ne null)?requestScope.metodo.descricaoLonga:''}</textarea></td></tr>

            </table>
            <button type="submit" class="btn btn-primary">${(param['codigo'] eq null)?'Cadastrar':'Alterar'}</button>
        </form>
    </body>
</html>

 