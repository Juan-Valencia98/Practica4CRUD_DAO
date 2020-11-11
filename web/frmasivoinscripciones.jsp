<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>  
        <h1>
            <c:if test="${inscripciones.id == 0}" >Nuevo</c:if>
            <c:if test="${inscripciones.id !=0}" >Editar</c:if>
            Inscripciones
        </h1>
        <form action="InicioInscripcion" method="post">
            <input type="hidden" name="id" value="${inscripciones.id}"/>
            <label>Curso:</label>
            <select name='curso'>
                <c:forEach var="item" items="${curso}">
                    <option value='${item.descripcion}'>${item.id}</option>
                </c:forEach>
            </select>
            <br>
            <label>Estudiante:</label>
            <select name='estudiante'>
                <c:forEach var="item" items="${estudiante}">
                    <option value='${item.nombre} ${item.apellidos}'>${item.nombre} ${item.apellidos}</option>
                </c:forEach>
            </select>
            <br>
            <label>Nota Final:</label>
            <input type="text" name="nota_final" value="${inscripciones.nota_final}"/>
            <br>
            <label><input type="submit" value="Enviar"/></label>
               
        </form>
    </body>
</html>
