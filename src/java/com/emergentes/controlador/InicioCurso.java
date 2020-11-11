package com.emergentes.controlador;

import com.emergentes.dao.CursoDAO;
import com.emergentes.dao.CursoDAOimpl;
import com.emergentes.modelo.Curso;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InicioCurso", urlPatterns = {"/InicioCurso"})
public class InicioCurso extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           CursoDAO dao= new CursoDAOimpl();
           int id;
           Curso pr= new Curso();
           String action=(request.getParameter("actioncurso")!=null)? request.getParameter("actioncurso"):"view";
           switch (action){
                case "add":
                   request.setAttribute("curso",pr);
                   request.getRequestDispatcher("frmasivocurso.jsp").forward(request, response);
                   break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pr=dao.getById(id);
                    System.out.println(pr);
                    request.setAttribute("curso",pr);
                    request.getRequestDispatcher("frmasivocurso.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/InicioCurso");
                    break;
                default:
                    List<Curso> lista = dao.getAll();
                    request.setAttribute("curso",lista);
                    request.getRequestDispatcher("listadocurso.jsp").forward(request, response);
                    break;
           }
       } catch (Exception ex) {
            System.out.println("Error: "+ ex.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       String descripcion=request.getParameter("descripcion");
       CursoDAO dao=new CursoDAOimpl();
       Curso pr = new Curso();
       pr.setId(id);
       pr.setDescripcion(descripcion);
       if(id==0){
           try{
              
               dao.insert(pr);
               response.sendRedirect("InicioCurso");
           }catch(Exception ex){
               System.out.println("Error: "+ex.getMessage());
           }
       }else{
           try{
               dao.update(pr);
               response.sendRedirect("InicioCurso");
           }catch(Exception ex){
               System.out.println("Error: "+ex.getMessage());
           }
       }
    }

}
