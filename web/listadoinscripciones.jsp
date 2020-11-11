<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inscripciones </h1>
        <p><a href="InicioInscripcion?actioninscripciones=add">Nuevo</a></p>
        <table border="2">
            <tr>
                <th>Id</th>
                <th>Curso</th>
                <th>Estudiante</th>
                <th>Nota Final</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${inscripciones}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.curso}</td>
                    <td>${item.estudiante}</td>
                    <td>${item.nota_final}</td>
                    <td><a href="InicioInscripcion?actioninscripciones=edit&id=${item.id}">Editar</a></td>
                    <td><a href="InicioInscripcion?actioninscripciones=delete&id=${item.id}" onclick="return(confirm('Estas Seguro'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
