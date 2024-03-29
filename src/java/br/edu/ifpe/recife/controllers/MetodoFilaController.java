/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.models.entites.MetodoFila;
import br.edu.ifpe.recife.models.repositories.FilaRepository;
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
@WebServlet(name = "MetodoFilaController", urlPatterns = {"/MetodoFilaController"})
public class MetodoFilaController extends HttpServlet {
@Override
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op != null) {
            if (op.equals("edit")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                MetodoFila mfEdit = FilaRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Editar Relato</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Relato</h1>");
                    out.println("<a href='MetodoFilaController'>Ver relatos cadastrados</a><br/>");
                    out.println("<form method='post' action='MetodoFilaController'>");
                    out.println("Código: <input type='hidden' name='codigo' value='" + mfEdit.getCodigo() + "'/>" + mfEdit.getCodigo() + "</br>");
                    out.println("Descrição curta: <textarea name='descricaoCurta'>" + mfEdit.getDescricaoCurta() + "</textarea></br>");
                    out.println("Descrição longa: <textarea name='descricaoLonga'>" + mfEdit.getDescricaoLonga()+ "</textarea></br>");
                    out.println("<input type='hidden' name='op' value='edit'/>");
                    out.println("<input type='submit' value='Editar'/>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                FilaRepository.delete(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Excluir relato</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href='MetodoFilaController'>Ver métodos de fila cadastrados</a><br/>");
                    out.println("<h1>O método de fila foi excluído com sucesso</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else if (op.equals("detail")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                MetodoFila mfDetail = FilaRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Detalhes do MétodoFila</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Detalhes do MétodoFila</h1>");
                    out.println("<a href='MetodoFilaController'>Listar Métodos</a><br/>");
                    out.println("<p>Código: " + mfDetail.getCodigo() + "</p>");
                    out.println("<p>Descrição Curta: " + mfDetail.getDescricaoCurta() + "</p>");
                    out.println("<p>Descrição Longa: " + mfDetail.getDescricaoLonga() + "</p>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            }
        } else {
            List<MetodoFila> filas = FilaRepository.readAll();

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Métodos Cadastrados</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Métodos cadastrados</h1>");
                out.println("<a href='index.html'>Home</a><br/>");
                out.println("<table border=\"1\">");
                out.println("<tr><th>Código</th><th>Descrição Curta</th><th>Operações</th></tr>");

                for (MetodoFila mfi : filas) {
                    out.println("<tr>");
                    out.println("<td>" + mfi.getCodigo() + "</td>");
                    out.println("<td>" + mfi.getDescricaoCurta() + "</td>");
                    out.println("<td><a href='MetodoFilaController?codigo=" + mfi.getCodigo() + "&op=detail'>detalhar</a>"
                            + "     <a href='MetodoFilaController?codigo=" + mfi.getCodigo() + "&op=edit'>editar</a>"
                            + " <a href='MetodoFilaController?codigo=" + mfi.getCodigo() + "&op=delete'>deletar</a></td>");
                    out.println("</tr>");
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
    String descricaoCurta = request.getParameter("descricaoCurta");
    String descricaoLonga = request.getParameter("descricaoLonga");
    
    String op = request.getParameter("op");

    if (op != null && op.equals("edit")) {
        MetodoFila mfi = FilaRepository.read(codigo);

        if (mfi != null) {
          
          
            mfi.setDescricaoCurta(descricaoCurta);
            mfi.setDescricaoLonga(descricaoLonga);
            

            
            
            FilaRepository.update(mfi);

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Cadastro</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='MetodoFilaController'>listar métodos</a>");
                out.println("<h1>O MétodoFila  foi atualizado com sucesso</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    } else {
        MetodoFila mfi = new MetodoFila();
        mfi.setCodigo(codigo);
        mfi.setDescricaoCurta(descricaoCurta);
        mfi.setDescricaoLonga(descricaoLonga);
        FilaRepository.create(mfi);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cadastro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<h1>O Método de Fila foi cadastrado com sucesso</h1>");
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
