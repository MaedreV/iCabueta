package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.models.entites.Estudante;
import br.edu.ifpe.recife.models.repositories.EstudanteRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstudanteController", urlPatterns = {"/EstudanteController"})
public class EstudanteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op != null) {
            if (op.equals("edit")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                Estudante eEdit = EstudanteRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Editar Estudante</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Estudante</h1>");
                    out.println("<a href='EstudanteController'>Ver estudantes cadastrados</a><br/>");
                    out.println("<form method='post' action='EstudanteController'>");
                    out.println("Código: <input type='hidden' name='codigo' value='" + eEdit.getCodigo() + "'/>" + eEdit.getCodigo() + "</br>");
                    out.println("Nome: <input type='text' name='nome' value='" + eEdit.getNome() + "'/></br>");
                    out.println("E-mail: <input type='text' name='email' value='" + eEdit.getEmail() + "'/></br>");
                    out.println("Ano de entrada: <input type='text' name='anoEntrada' value='" + eEdit.getAnoEntrada()+ "'/></br>");
                    out.println("<input type='hidden' name='op' value='edit'/>");
                    out.println("<input type='submit' value='Editar'/>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                EstudanteRepository.delete(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Excluir Estudante</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<a href='EstudanteController'>Ver estudantes cadastrados</a><br/>");
                    out.println("<h1>O Estudante foi excluído com sucesso</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
       else if (op.equals("detail")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                Estudante eDetail = EstudanteRepository.read(codigo);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Detalhes do Estudante</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Detalhes do estudante</h1>");
                    out.println("<a href='EstudanteController'>listar estudantes</a><br/>");
                    out.println("<p>Código: " + eDetail.getCodigo() + "</p>");
                    out.println("<p>Nome: " + eDetail.getNome() + "</p>");
                    out.println("<p>Email: " + eDetail.getEmail() + "</p>");
                    out.println("<p>Ano de entrada: " + eDetail.getAnoEntrada() + "</p>");

                   
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            }
        } else {
            List<Estudante> estudantes = EstudanteRepository.readAll();

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Estudantes Cadastrados</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Estudantes cadastrados</h1>");
                out.println("<a href='index.html'>Home</a><br/>");
                out.println("<table border=\"1\">");
                out.println("<tr><th>Código</th><th>Nome</th><th>E-mail</th><th>Ano de Entrada</th><th>Operações</th></tr>");

                for (Estudante est : estudantes) {
                    out.println("<tr>");
                    out.println("<td>" + est.getCodigo() + "</td>");
                    out.println("<td>" + est.getNome() + "</td>");
                    out.println("<td>" + est.getEmail() + "</td>");
                    out.println("<td>" + est.getAnoEntrada() + "</td>");
                    out.println("<td><a href='EstudanteController?codigo=" + est.getCodigo() + "&op=detail'>detalhar</a>"
                            + "     <a href='EstudanteController?codigo=" + est.getCodigo() + "&op=edit'>editar</a>"
                            + " <a href='EstudanteController?codigo=" + est.getCodigo() + "&op=delete'>deletar</a></td>");
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
    int anoEntrada = Integer.parseInt(request.getParameter("anoEntrada"));
    String op = request.getParameter("op");

    if (op != null && op.equals("edit")) {
        Estudante est = EstudanteRepository.read(codigo);

        if (est != null) {
            est.setNome(nome);
            est.setEmail(email);
            est.setAnoEntrada(anoEntrada);
            
            
            Estudante estCadastrado = EstudanteRepository.read(codigo);
        est.setSenha(estCadastrado.getSenha());
            
            
            EstudanteRepository.update(est);

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Cadastro</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<a href='EstudanteController'>Ver estudantes cadastrados</a>");
                out.println("<h1>O Estudante " + nome + " foi atualizado com sucesso</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    } else {
        Estudante est = new Estudante();
        est.setCodigo(codigo);
        est.setNome(nome);
        est.setSenha(senha);
        est.setEmail(email);
        est.setAnoEntrada(anoEntrada);
        EstudanteRepository.create(est);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cadastro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<h1>O Estudante " + nome + " foi cadastrado com sucesso</h1>");
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
