/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.models.entites.Cadeira;
import br.edu.ifpe.recife.models.repositories.CadeiraRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raulv
 */
@WebServlet(name = "CadeiraController", urlPatterns = {"/CadeiraController"})
public class CadeiraController extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op != null) {
            if (op.equals("edit")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                Cadeira cEdit = CadeiraRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Editar Cadeira</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Cadeira</h1>");
                    out.println("<a href='CadeiraController'>Ver cadeiras cadatrasdas</a><br/>");
                    out.println("<form method='post' action='CadeiraController'>");
                    out.println("Código: <input type='hidden' name='codigo' value='" + cEdit.getCodigo() + "'/>" + cEdit.getCodigo() + "</br>");
                    out.println("Nome: <input type='text' name='nome' value='" + cEdit.getNome() + "'/></br>");
                    out.println("Semestre: <input type='text' name='semestre' value='" + cEdit.getSemestre()+ "'/></br>");
                    out.println("Ano: <input type='text' name='ano' value='" + cEdit.getAno()+ "'/></br>");
                     out.println("Descrição: <textarea name='descricao'>" + cEdit.getDescricao() + "</textarea></br>");
                    out.println("<input type='hidden' name='op' value='edit'/>");
                    out.println("<input type='submit' value='Editar'/>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                CadeiraRepository.delete(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Excluir Cadeira</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href='CadeiraController'>Ver cadeiras cadastradas</a><br/>");
                    out.println("<h1>A Cadeira foi excluída com sucesso</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        else if (op.equals("detail")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                Cadeira cDetail = CadeiraRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Detalhes das Cadeiras</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Detalhes da cadeira</h1>");
                    out.println("<a href='CadeiraController'>listar cadeiras</a><br/>");
                    out.println("<p>Código: " + cDetail.getCodigo() + "</p>");
                    out.println("<p>Nome: " + cDetail.getNome() + "</p>");
                    out.println("<p>Ano: " + cDetail.getAno() + "</p>");
                    out.println("<p>Semestre: " + cDetail.getSemestre() + "</p>");
                    out.println("<p>Descrição: " + cDetail.getDescricao() + "</p>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            }
        } else {
            List<Cadeira> cadeiras = CadeiraRepository.readAll();

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Cadeiras Cadastradas</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Cadeiras cadastradas</h1>");
                out.println("<a href='index.html'>Home</a><br/>");
                out.println("<table border=\"1\">");
                out.println("<tr><th>Código</th><th>Nome</th><th>Ano</th><th>Semestre</th><th>Descrição</th><th>Operações</th></tr>");

                for (Cadeira cai : cadeiras) {
                    out.println("<tr>");
                    out.println("<td>" + cai.getCodigo() + "</td>");
                    out.println("<td>" + cai.getNome() + "</td>");
                    out.println("<td>" + cai.getAno() + "</td>");
                    out.println("<td>" + cai.getSemestre() + "</td>");
                    out.println("<td>" + cai.getDescricao() + "</td>");
                    out.println("<td><a href='CadeiraController?codigo=" + cai.getCodigo() + "&op=detail'>detalhar</a>"
                            + "     <a href='CadeiraController?codigo=" + cai.getCodigo() + "&op=edit'>editar</a>"
                            + " <a href='CadeiraController?codigo=" + cai.getCodigo() + "&op=delete'>deletar</a></td>");
                }

                out.println("</body>");
                out.println("</html>");
            }
        }
    }

  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    int codigo = Integer.parseInt(request.getParameter("codigo"));
    String nome = request.getParameter("nome");
    int ano = Integer.parseInt(request.getParameter("ano"));
    int semestre = Integer.parseInt(request.getParameter("semestre"));
    String descricao = request.getParameter("descricao");
    
    String op = request.getParameter("op");

    if (op != null && op.equals("edit")) {
        Cadeira cai = CadeiraRepository.read(codigo);

        if (cai != null) {
           // cai.setCodigo(codigo);
            cai.setNome(nome);
            cai.setAno(ano);
            cai.setSemestre(semestre);
            cai.setDescricao(descricao);
            
            CadeiraRepository.update(cai);

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Cadastro</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='CadeiraController'>Listar Cadeiras</a>");
                out.println("<h1>A Cadeira " + nome + " foi atualizada com sucesso</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    } else {
        Cadeira cai = new Cadeira();
        cai.setCodigo(codigo);
        cai.setNome(nome);
        cai.setAno(ano);
        cai.setSemestre(semestre);
        cai.setDescricao(descricao);
        CadeiraRepository.create(cai);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cadastro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<h1>A cadeira " + nome + " foi cadastrada com sucesso</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
