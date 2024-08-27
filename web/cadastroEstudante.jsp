<%-- 
    Document   : cadastroEstudante
    Created on : Jul 24, 2024, 12:13:38 AM
    Author     : raulv
--%>

<%@page import="br.edu.ifpe.recife.models.entites.Estudante"%>
<%@page import="br.edu.ifpe.recife.models.repositories.EstudanteRepository"%>
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
        <h1>${(param['codigo'] eq null)?'Cadastro':'Alterar Dados'} de Estudante</h1>
        ${(param.codigo eq null)?'':'<a href="mostrarEstudantes.jsp">Mostrar Estudantes</a></br>'}
        
        <% 
            
            if(request.getParameter("codigo")!=null){
                Estudante e = EstudanteRepository
                    .read(Integer.parseInt(request
                            .getParameter("codigo")));
                request.setAttribute("estudante", e);
            }
        %>
        
        <form method="post" action="EstudanteController">
            
            ${(requestScope.estudante ne null)?'<input type="hidden" name="op" value="1"/>':''}
            
            <table class="table">
                
                <tr><td>Código</td><td><input class="form-control" type="${(requestScope.estudante ne null)?'hidden':'text'}" name="codigo" ${(requestScope.estudante ne null)?'value="'.concat(requestScope.estudante.codigo).concat('"'):''}/>
                            ${(requestScope.estudante ne null)?requestScope.estudante.codigo:''}</td></tr>
                
                <tr><td>Nome</td><td><input class="form-control" type="text" name="nome" value="${(requestScope.estudante ne null)?requestScope.estudante.nome:''}"/></td></tr>
                
                <tr><td>e-mail</td><td><input class="form-control" type="text" name="email" value="${(requestScope.estudante ne null)?requestScope.estudante.email:''}"/></td></tr>   
                
                ${(requestScope.estudante eq null)?'<tr><td>Senha</td><td><input type="password" class="form-control" name="senha"/></td></tr>':''}
                ${(requestScope.estudante eq null)?'<tr><td>Confirmação Senha</td><td><input type="password" class="form-control" name="confirm"/></td></tr>':''} 
                
                <tr><td>Ano de entrada</td><td><input class="form-control" type="text" name="anoEntrada" value="${(requestScope.estudante ne null)?requestScope.estudante.anoEntrada:''}"/></td></tr>
                
            </table>
            <button type="submit" class="btn btn-primary">${(param['codigo'] eq null)?'Cadastrar':'Alterar'}</button>
        </form>
    </body>
</html>
