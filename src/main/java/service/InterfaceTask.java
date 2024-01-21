package service;

import java.util.List;
import metier.entities.Task;

public interface InterfaceTask {
    Task addTask(Task task);

    List<Task> getAllTasks();

    Task getTask(int taskId);

    Task updateTask(Task updateTaskId);

    void deleteTask(int taskId);
}

