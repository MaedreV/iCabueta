package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.models.entites.Professor;
import br.edu.ifpe.recife.models.repositories.ProfessorRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfessorController", urlPatterns = { "/ProfessorController" })
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op != null) {
            if (op.equals("edit")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                Professor pEdit = ProfessorRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Editar Professor</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Professor</h1>");
                    out.println("<a href='ProfessorController'>Ver professores cadastrados</a><br/>");
                    out.println("<form method='post' action='ProfessorController'>");
                    out.println("Código: <input type='hidden' name='codigo' value='" + pEdit.getCodigo() + "'/>"
                            + pEdit.getCodigo() + "</br>");
                    out.println("Nome: <input type='text' name='nome' value='" + pEdit.getNome() + "'/></br>");
                    out.println("E-mail: <input type='text' name='email' value='" + pEdit.getEmail() + "'/></br>");

                    out.println("<input type='hidden' name='op' value='edit'/>");
                    out.println("<input type='submit' value='Editar'/>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                ProfessorRepository.delete(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Excluir Professor</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href='ProfessorController'>Ver professores cadastrados</a><br/>");
                    out.println("<h1>O Professor foi excluído com sucesso</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
         else if (op.equals("detail")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                Professor pDetail = ProfessorRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Detalhes do MétodoFila</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Detalhes do Professor</h1>");
                    out.println("<a href='ProfessorController'>listar professores</a><br/>");
                    out.println("<p>Código: " + pDetail.getCodigo() + "</p>");
                    out.println("<p>Nome: " + pDetail.getNome() + "</p>");
                    out.println("<p>Email: " + pDetail.getEmail() + "</p>");
                   
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            }
        } else {
            List<Professor> professores = ProfessorRepository.readAll();

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Professores Cadastrados</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Professores cadastrados</h1>");
                out.println("<a href='index.html'>Home</a><br/>");
                out.println("<table border=\"1\">");
                out.println(
                        "<tr><th>Código</th><th>Nome</th><th>E-mail</th><th>Operações</th></tr>");

                for (Professor pro : professores) {
                    out.println("<tr>");
                    out.println("<td>" + pro.getCodigo() + "</td>");
                    out.println("<td>" + pro.getNome() + "</td>");
                    out.println("<td>" + pro.getEmail() + "</td>");

                      out.println("<td><a href='ProfessorController?codigo=" + pro.getCodigo() + "&op=detail'>detalhar</a>"
                            + "     <a href='ProfessorController?codigo=" + pro.getCodigo() + "&op=edit'>editar</a>"
                            + " <a href='ProfessorController?codigo=" + pro.getCodigo() + "&op=delete'>deletar</a></td>");
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
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        String op = request.getParameter("op");

        if (op != null && op.equals("edit")) {
            Professor pro = ProfessorRepository.read(codigo);

            if (pro != null) {
                pro.setNome(nome);
                pro.setEmail(email);

                Professor proCadastrado = ProfessorRepository.read(codigo);
                pro.setSenha(proCadastrado.getSenha());

                ProfessorRepository.update(pro);

                response.setContentType("text/html;charset=UTF-8");

                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet Cadastro</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href='ProfessorController'>Ver professores cadastrados</a>");
                    out.println("<h1>O Professor " + nome + " foi atualizado com sucesso</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else {
            Professor pro = new Professor();
            pro.setCodigo(codigo);
            pro.setNome(nome);
            pro.setSenha(senha);
            pro.setEmail(email);

            ProfessorRepository.create(pro);

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Cadastro</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='index.html'>Home</a>");
                out.println("<h1>O Professor " + nome + " foi cadastrado com sucesso</h1>");
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
