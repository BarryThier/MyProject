package implement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import metier.entities.Task;
import service.InterfaceTask;

public class TaskServiceImpl implements InterfaceTask {
    private static final String FILE_PATH = "C:/Files/mytasks.txt"; // Le chemin du fichier de sauvegarde
    private List<Task> tasks = new ArrayList<Task>();

    public TaskServiceImpl() {
        // Chargez les tâches à partir du fichier lors de la création du service
        loadTasksFromFile();
    }

    @Override
    public Task addTask(Task task) {
        if (isDuplicateId(task.getId())) {
            System.out.println("Un identifiant similaire existe déjà. Veuillez saisir un nouvel identifiant.");
            // Vous pouvez ici renvoyer un code d'erreur ou jeter une exception pour gérer cela côté servlet.
            return null;
        }

        tasks.add(task);
        System.out.println("Tâche ajoutée avec succès: " + task.toString());

        // Sauvegarder les tâches dans le fichier
        saveTasksToFile();

        return task;
    }
    
    
    // Méthode pour vérifier si un identifiant existe déjà
    private boolean isDuplicateId(int taskId) {
        return tasks.stream().anyMatch(task -> task.getId() == taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Task getTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
            	saveTasksToFile();
                return task;
            }
        }
        return null;
    }

 // Modifiez la méthode updateTask dans TaskServiceImpl

    @Override
    public Task updateTask(Task taskToUpdate) {
        int index = getTaskIndexById(taskToUpdate.getId());

        if (index == -1) {
            System.out.println("La tâche avec l'identifiant " + taskToUpdate.getId() + " n'existe pas.");
            return null;
        }

        if (!isUniqueId(taskToUpdate.getId())) {
            System.out.println("Un identifiant similaire existe déjà. Veuillez saisir un nouvel identifiant.");
            return null;
        }

        tasks.set(index, taskToUpdate);
        System.out.println("Tâche mise à jour avec succès: " + taskToUpdate.toString());

        // Sauvegarder les tâches dans le fichier
        saveTasksToFile();

        return taskToUpdate;
    }

    // Méthode pour vérifier si un identifiant est unique lors de la modification
    private boolean isUniqueId(int taskId) {
        return tasks.stream().filter(task -> task.getId() == taskId).count() <= 1;
    }

    // Méthode pour obtenir l'index d'une tâche par son identifiant
    private int getTaskIndexById(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskId) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void deleteTask(int taskId) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == taskId) {
                iterator.remove();
                saveTasksToFile(); // Sauvegarde la liste mise à jour dans le fichier
                break;
            }
        }
    }

    // Méthode pour charger les tâches depuis le fichier
    private void loadTasksFromFile() {
        try {
            // Lire les lignes du fichier et créer les objets Task
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String description = parts[2].trim();
                String date = parts[3].trim();
                String author = parts[4].trim();
                Task task = new Task(id, title, description, date, author);
                tasks.add(task);
            }
        } catch (IOException e) {
            // Gérer les erreurs de lecture du fichier
            e.printStackTrace();
        }
    }


    // Méthode pour sauvegarder les tâches dans le fichier
    private void saveTasksToFile() {
        try {
            // Écrire les tâches dans le fichier
            List<String> lines = tasks.stream()
                    .map(task -> task.getId() + "," + task.getTitre() + "," + task.getDescription() + "," + task.getDate() + "," + task.getAuteur())
                    .collect(Collectors.toList());
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            // Gérer les erreurs d'écriture dans le fichier
            e.printStackTrace();
        }
    }
}
