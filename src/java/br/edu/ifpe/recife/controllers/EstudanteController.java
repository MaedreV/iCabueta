package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.models.entites.Estudante;
import br.edu.ifpe.recife.models.repositories.EstudanteRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
                request.setAttribute("estudante", eEdit);
                RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroEstudante.jsp");
                dispatcher.forward(request, response);
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                EstudanteRepository.delete(codigo);

            
            
            request.getSession().setAttribute("msg", "Estudante deletado com sucesso!");
            
            response.sendRedirect("mostrarEstudantes.jsp");
            
            return;
            }
       else if (op.equals("detail")) {
              int codigo = Integer.parseInt(request.getParameter("codigo"));
                Estudante eDetail = EstudanteRepository.read(codigo);

                request.setAttribute("estudante", eDetail);
                RequestDispatcher dispatcher = request.getRequestDispatcher("detalheEstudante.jsp");
                dispatcher.forward(request, response);
            }
        } else {
List<Estudante> estudantes = EstudanteRepository.readAll();
  request.setAttribute("estudantes", estudantes);
  RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarEstudantes.jsp");
  dispatcher.forward(request, response);
  }
    }

  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    int codigo = Integer.parseInt(request.getParameter("codigo"));
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String senha = request.getParameter("senha");
    String confirma = request.getParameter("confirm");
    int anoEntrada = Integer.parseInt(request.getParameter("anoEntrada"));
    String op = request.getParameter("op");

     if(op == null && !senha.equals(confirma)){
            
            HttpSession session = request.getSession();
            session.setAttribute("msg", "As senhas não estão iguais!");
            
            response.sendRedirect("mostrarEstudantes.jsp");
            
            return;
        }
     
      if (op == null) { 
        Estudante estExiste = EstudanteRepository.read(codigo);
        if (estExiste != null) {
            HttpSession session = request.getSession();
            session.setAttribute("msg", "Código de estudante já existe!");
            response.sendRedirect("mostrarEstudantes.jsp");
            return;
        }
    }
        
        Estudante e = new Estudante();
        
       
        e.setNome(nome);
        e.setCodigo(codigo);
        e.setEmail(email);
        e.setAnoEntrada(anoEntrada);
        
        HttpSession session = request.getSession();
        
        if(op == null){
            e.setSenha(senha);
            EstudanteRepository.create(e);
            session.setAttribute("msg", "Estudante cadastrado com sucesso!");
        }else{
            EstudanteRepository.update(e);
            session.setAttribute("msg", "Estudante alterado com sucesso!");
        }
        
        response.sendRedirect("mostrarEstudantes.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
