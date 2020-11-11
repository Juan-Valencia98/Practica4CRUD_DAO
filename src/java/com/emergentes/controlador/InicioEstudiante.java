package com.emergentes.controlador;

import com.emergentes.dao.EstudianteDAOimpl;
import com.emergentes.modelo.Estudiante;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.emergentes.dao.EstudianteDAO;

@WebServlet(name = "InicioEstudiante", urlPatterns = {"/InicioEstudiante"})
public class InicioEstudiante extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           EstudianteDAO dao= new EstudianteDAOimpl();
           int id;
           Estudiante pr= new Estudiante();
           String action=(request.getParameter("actionestudiante")!=null)? request.getParameter("actionestudiante"):"view";
           switch (action){
                case "add":
                   request.setAttribute("estudiante",pr);
                   request.getRequestDispatcher("frmasivoest.jsp").forward(request, response);
                   break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pr=dao.getById(id);
                    System.out.println(pr);
                    request.setAttribute("estudiante",pr);
                    request.getRequestDispatcher("frmasivoest.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/InicioEstudiante");
                    break;
                default:
                    List<Estudiante> lista = dao.getAll();
                    request.setAttribute("estudiante",lista);
                    request.getRequestDispatcher("listadoest.jsp").forward(request, response);
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
       String nombre=request.getParameter("nombre");
       String apellidos=request.getParameter("apellidos");
       String correo =request.getParameter("correo");
       EstudianteDAO dao=new EstudianteDAOimpl();
       Estudiante pr = new Estudiante();
       pr.setId(id);
       pr.setNombre(nombre);
       pr.setApellidos(apellidos);
       pr.setCorreo(correo);
       if(id==0){
           try{
              
               dao.insert(pr);
               response.sendRedirect("InicioEstudiante");
           }catch(Exception ex){
               System.out.println("Error: "+ex.getMessage());
           }
       }else{
           try{
               
               dao.update(pr);
               response.sendRedirect("InicioEstudiante");
           }catch(Exception ex){
               System.out.println("Error: "+ex.getMessage());
           }
       }
    }

}
