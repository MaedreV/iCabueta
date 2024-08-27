package br.edu.ifpe.recife.controllers;

import br.edu.ifpe.recife.models.entites.Cadeira;
import br.edu.ifpe.recife.models.repositories.CadeiraRepository;
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
                request.setAttribute("cadeira", cEdit);
                RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroCadeira.jsp");
                dispatcher.forward(request, response);
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                CadeiraRepository.delete(codigo);

             
            request.getSession().setAttribute("msg", "Cadeira deletada com sucesso!");
            
            response.sendRedirect("mostrarCadeiras.jsp");
            
            return;
            }
       else if (op.equals("detail")) {
              int codigo = Integer.parseInt(request.getParameter("codigo"));
                Cadeira cDetail = CadeiraRepository.read(codigo);

                request.setAttribute("cadeira", cDetail);
                RequestDispatcher dispatcher = request.getRequestDispatcher("detalheCadeira.jsp");
                dispatcher.forward(request, response);
            }
        } else {
List<Cadeira> cadeiras = CadeiraRepository.readAll();
  request.setAttribute("cadeiras", cadeiras);
  RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarCadeiras.jsp");
  dispatcher.forward(request, response);
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

        
        Cadeira cai = new Cadeira();
        
            cai.setCodigo(codigo);
            cai.setNome(nome);
            cai.setAno(ano);
            cai.setSemestre(semestre);
            cai.setDescricao(descricao);

        
        HttpSession session = request.getSession();
        
        
         if (op == null) { 
        Cadeira caiExiste = CadeiraRepository.read(codigo);
        if (caiExiste != null) {
            
            session.setAttribute("msg", "Código da cadeira já existe!");
            response.sendRedirect("mostrarCadeiras.jsp");
            return;
        }
    }
        
        
        if(op == null){
          
            CadeiraRepository.create(cai);
            session.setAttribute("msg", "Cadeira cadastrada com sucesso!");
        }else{
            CadeiraRepository.update(cai);
            session.setAttribute("msg", "Cadeira alterada com sucesso!");
        }
        
        response.sendRedirect("mostrarCadeiras.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
