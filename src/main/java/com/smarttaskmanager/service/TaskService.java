package com.smarttaskmanager.service;

import com.smarttaskmanager.dto.TaskRequest;
import com.smarttaskmanager.entity.Task;
import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.enums.TaskPriority;
import com.smarttaskmanager.enums.TaskStatus;
import com.smarttaskmanager.dto.CalendarEvent;

import java.util.List;

public interface TaskService {

    Task createTask(TaskRequest request, User user);

    List<Task> getTasks(User user);

    Task getTask(Long id, User user);

    Task updateTask(Long id, TaskRequest request, User user);

    void deleteTask(Long id, User user);

    void updateStatus(Long id, TaskStatus status, User user);

    long getTotalTasks(User user);

    long getPendingTasks(User user);

    long getCompletedTasks(User user);

    long getOverdueTasks(User user);

    List<Task> getRecentTasks(User user);

    List<Task> searchTasks(User user, String keyword);

    List<Task> getTasksByPriority(
            User user,
            TaskPriority priority);

    List<Task> getTasksByStatus(
            User user,
            TaskStatus status);

    List<Task> getPendingTasksList(User user);

    List<Task> getInProgressTasks(User user);

    List<Task> getCompletedTasksList(User user);

    List<CalendarEvent> getCalendarEvents(User user);
}
