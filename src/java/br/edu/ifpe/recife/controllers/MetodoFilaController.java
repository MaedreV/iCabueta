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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "MetodoFilaController", urlPatterns = {"/MetodoFilaController"})
public class MetodoFilaController extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op != null) {
            if (op.equals("edit")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                MetodoFila mEdit = FilaRepository.read(codigo);
                request.setAttribute("metodo", mEdit);
                RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroMetodoFila.jsp");
                dispatcher.forward(request, response);
            } else if (op.equals("delete")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                FilaRepository.delete(codigo);

              
            
            request.getSession().setAttribute("msg", "Metodo de Fila deletado com sucesso!");
            
            response.sendRedirect("mostrarMetodoFila.jsp");
            
            return;
            }
       else if (op.equals("detail")) {
              int codigo = Integer.parseInt(request.getParameter("codigo"));
                MetodoFila mDetail = FilaRepository.read(codigo);

                request.setAttribute("metodo", mDetail);
                RequestDispatcher dispatcher = request.getRequestDispatcher("detalheMetodoFila.jsp");
                dispatcher.forward(request, response);
            }
        } else {
List<MetodoFila> metodos = FilaRepository.readAll();
  request.setAttribute("metodos", metodos);
  RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarMetodoFila.jsp");
  dispatcher.forward(request, response);
  }
    }

  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    int codigo = Integer.parseInt(request.getParameter("codigo"));
    String descricaoCurta = request.getParameter("descricaoCurta");
    String descricaoLonga = request.getParameter("descricaoLonga");
    
    String op = request.getParameter("op");

        
        MetodoFila mf = new MetodoFila();
        
            mf.setCodigo(codigo);
            mf.setDescricaoCurta(descricaoCurta);
            mf.setDescricaoLonga(descricaoLonga);

        
        HttpSession session = request.getSession();
        
        
         if (op == null) { 
        MetodoFila mfExiste = FilaRepository.read(codigo);
        if (mfExiste != null) {
            
            session.setAttribute("msg", "Código do método de fila já existe!");
            response.sendRedirect("mostrarMetodoFila.jsp");
            return;
        }
    }
        
        
        if(op == null){
          
            FilaRepository.create(mf);
            session.setAttribute("msg", "Método de Fila cadastrado com sucesso!");
        }else{
            FilaRepository.update(mf);
            session.setAttribute("msg", "Método Fila alterado com sucesso!");
        }
        
        response.sendRedirect("mostrarMetodoFila.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
