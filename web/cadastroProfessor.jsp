<%-- 
    Document   : cadastroProfessor
    Created on : Jul 25, 2024, 12:41:00 AM
    Author     : raulv
--%>

<%@page import="br.edu.ifpe.recife.models.repositories.ProfessorRepository"%>
<%@page import="br.edu.ifpe.recife.models.entites.Professor"%>
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
        <h1>${(param['codigo'] eq null)?'Cadastro':'Alterar Dados'} do Professor</h1>
        ${(param.codigo eq null)?'':'<a href="mostrarProfessores.jsp">Mostrar Professores</a></br>'}
        
        <% 
            
            if(request.getParameter("codigo")!=null){
                Professor pro = ProfessorRepository
                    .read(Integer.parseInt(request
                            .getParameter("codigo")));
            
                request.setAttribute("professor", pro);
            }
        %>
        
        <form method="post" action="ProfessorController">
            
            ${(requestScope.professor ne null)?'<input type="hidden" name="op" value="1"/>':''} <%-- Verificar --%>
            
            <table class="table">
                
                <tr><td>Código</td><td><input class="form-control" type="${(requestScope.professor ne null)?'hidden':'text'}" name="codigo" ${(requestScope.professor ne null)?'value="'.concat(requestScope.professor.codigo).concat('"'):''}/>
                ${(requestScope.professor ne null)?requestScope.professor.codigo:''}</td></tr>
                
                <tr><td>Nome</td><td><input class="form-control" type="text" name="nome" value="${(requestScope.professor ne null)?requestScope.professor.nome:''}"/></td></tr>
                
                <tr><td>e-mail</td><td><input class="form-control" type="text" name="email" value="${(requestScope.professor ne null)?requestScope.professor.email:''}"/></td></tr>
                
                ${(requestScope.professor eq null)?'<tr><td>Senha</td><td><input type="password" class="form-control" name="senha"/></td></tr>':''}
                ${(requestScope.professor eq null)?'<tr><td>Confirmação Senha</td><td><input type="password" class="form-control" name="confirm"/></td></tr>':''}
                                   
            </table>
            <button type="submit" class="btn btn-primary">${(param['codigo'] eq null)?'Cadastrar':'Alterar'}</button>
        </form>
    </body>
</html>
