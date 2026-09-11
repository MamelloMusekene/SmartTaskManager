package com.smarttaskmanager.service.impl;

import com.smarttaskmanager.dto.CalendarEvent;
import com.smarttaskmanager.dto.TaskRequest;
import com.smarttaskmanager.entity.Task;
import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.enums.TaskStatus;
import com.smarttaskmanager.enums.TaskPriority;
import com.smarttaskmanager.exception.TaskNotFoundException;
import com.smarttaskmanager.mapper.TaskMapper;
import com.smarttaskmanager.repository.TaskRepository;
import com.smarttaskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task createTask(TaskRequest request, User user) {

        Task task = TaskMapper.toEntity(request, user);

        return repository.save(task);
    }

    @Override
    public List<Task> getTasks(User user) {
        return repository.findByUserOrderByDueDateAsc(user);
    }

    @Override
    public Task getTask(Long id, User user) {

        return repository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new TaskNotFoundException(id));
    }

    @Override
    public Task updateTask(
            Long id,
            TaskRequest request,
            User user) {

        Task task = getTask(id, user);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        return repository.save(task);
    }

    @Override
    public void deleteTask(Long id, User user) {

        Task task = getTask(id, user);

        repository.delete(task);
    }

    @Override
    public long getTotalTasks(User user) {
        return repository.countByUser(user);
    }

    @Override
    public long getPendingTasks(User user) {
        return repository.countByUserAndStatus(user, TaskStatus.PENDING);
    }

    @Override
    public long getCompletedTasks(User user) {
        return repository.countByUserAndStatus(user, TaskStatus.COMPLETED);
    }

    @Override
    public long getOverdueTasks(User user) {
        return repository.countByUserAndDueDateBeforeAndStatus(
                user,
                LocalDate.now(),
                TaskStatus.PENDING
        );
    }

    @Override
    public List<Task> getRecentTasks(User user) {
        return repository.findTop5ByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public List<Task> searchTasks(
            User user,
            String keyword){

        return repository
                .findByUserAndTitleContainingIgnoreCase(
                        user,
                        keyword
                );

    }

    @Override
    public List<Task> getTasksByPriority(
            User user,
            TaskPriority priority){

        return repository.findByUserAndPriority(
                user,
                priority
        );

    }

    @Override
    public List<Task> getTasksByStatus(
            User user,
            TaskStatus status){

        return repository.findByUserAndStatus(
                user,
                status
        );

    }

    @Override
    public List<Task> getPendingTasksList(User user) {

        return repository.findByUserAndStatusOrderByDueDateAsc(
                user,
                TaskStatus.PENDING
        );
    }

    @Override
    public List<Task> getInProgressTasks(User user) {

        return repository.findByUserAndStatusOrderByDueDateAsc(
                user,
                TaskStatus.IN_PROGRESS
        );
    }

    @Override
    public List<Task> getCompletedTasksList(User user) {

        return repository.findByUserAndStatusOrderByDueDateAsc(
                user,
                TaskStatus.COMPLETED
        );
    }

    @Override
    public void updateStatus(
            Long id,
            TaskStatus status,
            User user) {

        Task task = getTask(id, user);

        task.setStatus(status);

        repository.save(task);
    }

    @Override
    public List<CalendarEvent> getCalendarEvents(User user) {

        LocalDate today = LocalDate.now();

        return repository.findByUser(user)
                .stream()
                .map(task -> {

                    String className;

                    if (task.getDueDate().isBefore(today)
                            && task.getStatus() != TaskStatus.COMPLETED) {

                        className = "overdue-task";

                    } else if (task.getDueDate().isEqual(today)
                            && task.getStatus() != TaskStatus.COMPLETED) {

                        className = "today-task";

                    } else {

                        className = "normal-task";
                    }

                    return new CalendarEvent(
                            task.getId(),
                            task.getTitle(),
                            task.getDueDate().toString(),
                            "/tasks/" + task.getId() + "/edit",
                            className
                    );

                })
                .toList();
    }

}