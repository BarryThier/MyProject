<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier une tâche  </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333333;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333333;
        }

        input, textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #dddddd;
            border-radius: 4px;
        }

        button {
            background-color: #4caf50;
            color: #ffffff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <form action="${pageContext.request.contextPath}/updateTask" method="post">
        <h2>Modifiez une tâche</h2>
        <div>
            <label for="id">Id:</label>
            <input type="number" id="id" name="id" required>
        </div>
        <div>
            <label for="titre">Titre:</label>
            <input type="text" id="titre" name="titre" required>
        </div>
        <div>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
        </div>
        <div>
            <label for="date">Date de création:</label>
            <input type="date" id="date" name="date" required>
        </div>
        <div>
            <label for="auteur">Auteur:</label>
            <input type="text" id="auteur" name="auteur" placeholder="Veuillez saisir le nom de l'auteur" required>
        </div>
        <div>
            <button type="submit">Mettre à jour</button>
        </div>
    </form>
</body>
</html>
