package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.models.entites.Professor;
import br.edu.ifpe.recife.models.repositories.ProfessorRepository;
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
                request.setAttribute("professor", pEdit);
                RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProfessor.jsp");
                dispatcher.forward(request, response);
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                ProfessorRepository.delete(codigo);

              
            
            request.getSession().setAttribute("msg", "Professor deletado com sucesso!");
            
            response.sendRedirect("mostrarProfessores.jsp");
            
            return;
            }
       else if (op.equals("detail")) {
              int codigo = Integer.parseInt(request.getParameter("codigo"));
                Professor pDetail = ProfessorRepository.read(codigo);

                request.setAttribute("professor", pDetail);
                RequestDispatcher dispatcher = request.getRequestDispatcher("detalheProfessor.jsp");
                dispatcher.forward(request, response);
            }
        } else {
List<Professor> professores = ProfessorRepository.readAll();
  request.setAttribute("professores", professores);
  RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarProfessores.jsp");
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
    String op = request.getParameter("op");

    
    
    
    
     if(op == null && !senha.equals(confirma)){
            
            HttpSession session = request.getSession();
            session.setAttribute("msg", "As senhas não estão iguais!");
            
            response.sendRedirect("mostrarProfessores.jsp");
            
            return;
        }
        
     // verificar se o professor já existe
    if (op == null) { 
        Professor proExiste = ProfessorRepository.read(codigo);
        if (proExiste != null) {
            HttpSession session = request.getSession();
            session.setAttribute("msg", "Código de professor já existe!");
            response.sendRedirect("mostrarProfessores.jsp");
            return;
        }
    }
        Professor pro = new Professor();
        
       
        pro.setNome(nome);
        pro.setCodigo(codigo);
        pro.setEmail(email);
       
        
        HttpSession session = request.getSession();
        
        if(op == null){
            pro.setSenha(senha);
            ProfessorRepository.create(pro);
            session.setAttribute("msg", "Professosr cadastrado com sucesso!");
        }else{
            ProfessorRepository.update(pro);
            session.setAttribute("msg", "Professor alterado com sucesso!");
        }
        
        response.sendRedirect("mostrarProfessores.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
