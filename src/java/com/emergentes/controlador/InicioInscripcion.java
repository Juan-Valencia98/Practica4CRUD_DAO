package com.emergentes.controlador;

import com.emergentes.dao.CursoDAO;
import com.emergentes.dao.CursoDAOimpl;
import com.emergentes.dao.EstudianteDAO;
import com.emergentes.dao.EstudianteDAOimpl;
import com.emergentes.dao.InscripcionesDAO;
import com.emergentes.dao.InscripcionesDAOimpl;
import com.emergentes.modelo.Curso;
import com.emergentes.modelo.Estudiante;
import com.emergentes.modelo.Inscripciones;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "InicioInscripcion", urlPatterns = {"/InicioInscripcion"})
public class InicioInscripcion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           InscripcionesDAO dao= new InscripcionesDAOimpl();
           CursoDAO daocurso = new CursoDAOimpl();
           EstudianteDAO daoest = new EstudianteDAOimpl();
           int id;
           Inscripciones pr= new Inscripciones();
           String action=(request.getParameter("actioninscripciones")!=null)? request.getParameter("actioninscripciones"):"view";
           List<Curso> lis = daocurso.getAll();
           request.setAttribute("curso",lis);
           List<Estudiante> list = daoest.getAll();
           request.setAttribute("estudiante", list);
           switch (action){
                case "add":                   
                   request.setAttribute("inscripciones",pr);
                   request.getRequestDispatcher("frmasivoinscripciones.jsp").forward(request, response);
                   break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pr=dao.getById(id);
                    System.out.println(pr);
                    request.setAttribute("inscripciones",pr);
                    request.getRequestDispatcher("frmasivoinscripciones.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/InicioInscripcion");
                    break;
                default:
                    List<Inscripciones> lista = dao.getAll();
                    request.setAttribute("inscripciones",lista);
                    request.getRequestDispatcher("listadoinscripciones.jsp").forward(request, response);
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
       String curso= request.getParameter("curso");
       String estudiante=request.getParameter("estudiante");
       int nota_final=Integer.parseInt(request.getParameter("nota_final"));
       InscripcionesDAO dao=new InscripcionesDAOimpl();
       Inscripciones pr = new Inscripciones();
       pr.setId(id);
       pr.setCurso(curso);
       pr.setEstudiante(estudiante);
       pr.setNota_final(nota_final);
       if(id==0){
           try{
               dao.insert(pr);
               response.sendRedirect("InicioInscripcion");
           }catch(Exception ex){
               System.out.println("Error: "+ex.getMessage());
           }
       }else{
           try{
               dao.update(pr);
               response.sendRedirect("InicioInscripcion");
           }catch(Exception ex){
               System.out.println("Error: "+ex.getMessage());
           }
       }
    }

}

