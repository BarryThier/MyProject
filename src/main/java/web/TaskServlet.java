package web;

import java.io.IOException;
import java.util.List;

import implement.TaskServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Task;
import service.InterfaceTask;

@WebServlet(name = "TaskServlet", urlPatterns = { "/tasks", "/addTask", "/searchTask", "/updateTask", "/deleteTask" })
public class TaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InterfaceTask taskService;

    public void init() throws ServletException {
        taskService = new TaskServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/tasks".equals(path)) {
            listTasks(request, response);
        } else if ("/addTask".equals(path)) {
            addTask(request, response);
        } else if ("/searchTask".equals(path)) {
            searchTask(request, response);
        } else if ("/updateTask".equals(path)) {
            updateTask(request, response);
        } else if ("/deleteTask".equals(path)) {
            deleteTask(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Task> tasks = taskService.getAllTasks();
        request.setAttribute("tasks", tasks);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/tasks.jsp");
        dispatcher.forward(request, response);
    }

    private void addTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les données du formulaire
        int id = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String auteur = request.getParameter("auteur");

        // Créer une nouvelle tâche
        Task newTask = new Task(id, titre, description, date, auteur);

        // Ajouter la tâche au service
        taskService.addTask(newTask);

        // Rediriger vers la liste des tâches
        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    private void searchTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer l'ID de la tâche à rechercher
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        // Rechercher la tâche
        Task task = taskService.getTask(taskId);

        // Ajouter la tâche aux attributs de la requête
        request.setAttribute("searchedTask", task);

        // Rediriger vers la page de résultats de recherche
        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchResults.jsp");
        dispatcher.forward(request, response);
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String auteur = request.getParameter("auteur");

        Task updatedTask = new Task(taskId, titre, description, date, auteur);
        taskService.updateTask(updatedTask);
        response.sendRedirect(request.getContextPath() + "/tasks");

       
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("id"));
        taskService.deleteTask(taskId);

        response.sendRedirect(request.getContextPath() + "/tasks");
    }
}
