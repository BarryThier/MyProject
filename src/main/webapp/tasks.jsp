<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des tâches</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .add-button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
	 <div class="add-button">
        <a href="${pageContext.request.contextPath}/addTask.jsp">Ajouter une nouvelle tâche</a>
    </div>
    <div>
    	<a href="${pageContext.request.contextPath}/searchForm.jsp">Rechercher une tâche</a>
    </div>
    <h2>Liste des tâches</h2>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Titre</th>
                <th>Description</th>
                <th>Date de création</th>
                <th>Auteur</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.id}</td>
                    <td>${not empty task.titre ? task.titre : 'N/A'}</td>
                    <td>${not empty task.description ? task.description : 'N/A'}</td>
                    <td>${not empty task.date ? task.date : 'N/A'}</td>
                    <td>${not empty task.auteur ? task.auteur : 'N/A'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/updateTask.jsp">Modifier</a>
                        <a href="${pageContext.request.contextPath}/deleteTask?id=${task.id}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
