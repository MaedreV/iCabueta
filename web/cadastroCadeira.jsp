

<%-- 
    Document   : cadastroCadeira
    Created on : Jul 24, 2024, 11:17:44 PM
    Author     : raulv
--%>

<%@page import="br.edu.ifpe.recife.models.entites.Cadeira"%>
<%@page import="br.edu.ifpe.recife.models.repositories.CadeiraRepository"%>
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
        <h1>${(param['codigo'] eq null)?'Cadastro':'Alterar Dados'} das Cadeiras</h1>
        ${(param.codigo eq null)?'':'<a href="mostrarCadeiras.jsp">Mostrar Cadeiras</a></br>'}
        
        <% 
            
            if(request.getParameter("codigo")!=null){
                Cadeira c = CadeiraRepository
                    .read(Integer.parseInt(request
                            .getParameter("codigo")));
            
                request.setAttribute("cadeira", c);
            }
        %>
        
        <form method="post" action="CadeiraController">
            
            ${(requestScope.cadeira ne null)?'<input type="hidden" name="op" value="1"/>':''}
            
            <table class="table">
                
                <tr><td>Código</td><td><input class="form-control" type="${(requestScope.cadeira ne null)?'hidden':'text'}" name="codigo" ${(requestScope.cadeira ne null)?'value="'.concat(requestScope.cadeira.codigo).concat('"'):''}/>
                 ${(requestScope.cadeira ne null)?requestScope.cadeira.codigo:''}</td></tr>
                
                <tr><td>Nome</td><td><input class="form-control" type="text" name="nome" value="${(requestScope.cadeira ne null)?requestScope.cadeira.nome:''}"/></td></tr>
                
                <tr><td>Ano</td><td><input class="form-control" type="text" name="ano" value="${(requestScope.cadeira ne null)?requestScope.cadeira.ano:''}"/></td></tr>
                
                <tr><td>Semestre</td><td><input class="form-control" type="text" name="semestre" value="${(requestScope.cadeira ne null)?requestScope.cadeira.semestre:''}"/></td></tr>
                
                <tr><td>Descrição</td><td><textarea class="form-control" name="descricao">${(requestScope.cadeira ne null)?requestScope.cadeira.descricao:''}</textarea></td></tr>

            </table>
            <button type="submit" class="btn btn-primary">${(param['codigo'] eq null)?'Cadastrar':'Alterar'}</button>
        </form>
    </body>
</html>
  