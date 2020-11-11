<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Estudiantes </h1>
        <p><a href="InicioEstudiante?actionestudiante=add">Nuevo</a></p>
        <table border="2">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Correo</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${estudiante}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nombre}</td>
                    <td>${item.apellidos}</td>
                    <td>${item.correo}</td>
                    <td><a href="InicioEstudiante?actionestudiante=edit&id=${item.id}">Editar</a></td>
                    <td><a href="InicioEstudiante?actionestudiante=delete&id=${item.id}" onclick="return(confirm('Estas Seguro'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
