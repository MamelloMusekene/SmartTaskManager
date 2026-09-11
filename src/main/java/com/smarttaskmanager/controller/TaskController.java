package com.smarttaskmanager.controller;

import com.smarttaskmanager.dto.TaskRequest;
import com.smarttaskmanager.entity.Task;
import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.enums.TaskStatus;
import com.smarttaskmanager.enums.TaskPriority;
import com.smarttaskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // List all tasks
    @GetMapping
    public String listTasks(
            @AuthenticationPrincipal User user,
            Model model) {

        model.addAttribute(
                "tasks",
                taskService.getTasks(user)
        );

        return "tasks";
    }

    // Show create task form
    @GetMapping("/new")
    public String newTask(Model model) {

        model.addAttribute(
                "taskRequest",
                new TaskRequest()
        );

        return "task-form";
    }

    // Show edit task form
    @GetMapping("/{id}/edit")
    public String editTask(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            Model model) {

        Task task = taskService.getTask(id, user);

        TaskRequest request = new TaskRequest();

        request.setTitle(task.getTitle());
        request.setDescription(task.getDescription());
        request.setPriority(task.getPriority());
        request.setDueDate(task.getDueDate());

        model.addAttribute("taskRequest", request);
        model.addAttribute("taskId", id);

        return "task-form";
    }

    // Delete task
    @PostMapping("/{id}/delete")
    public String deleteTask(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        taskService.deleteTask(id, user);

        return "redirect:/tasks";
    }

    // Search tasks
    @GetMapping("/search")
    public String search(
            @RequestParam String keyword,
            @AuthenticationPrincipal User user,
            Model model) {

        model.addAttribute(
                "tasks",
                taskService.searchTasks(user, keyword)
        );

        return "tasks";
    }

    // Filter tasks by priority
    @GetMapping("/filter/{priority}")
    public String filterByPriority(
            @PathVariable String priority,
            @AuthenticationPrincipal User user,
            Model model) {

        TaskPriority taskPriority =
                TaskPriority.valueOf(priority.toUpperCase());

        model.addAttribute(
                "tasks",
                taskService.getTasksByPriority(
                        user,
                        taskPriority
                )
        );

        return "tasks";
    }

    // Kanban board
    @GetMapping("/board")
    public String board(
            @AuthenticationPrincipal User user,
            Model model) {

        model.addAttribute(
                "pendingTasks",
                taskService.getPendingTasksList(user)
        );

        model.addAttribute(
                "progressTasks",
                taskService.getInProgressTasks(user)
        );

        model.addAttribute(
                "completedTasks",
                taskService.getCompletedTasksList(user)
        );

        return "board";
    }

    // Create task
    @PostMapping
    public String createTask(
            @Valid
            @ModelAttribute TaskRequest taskRequest,
            BindingResult result,
            @AuthenticationPrincipal User user) {

        if (result.hasErrors()) {
            return "task-form";
        }

        taskService.createTask(taskRequest, user);

        return "redirect:/tasks";
    }

    // Update task
    @PostMapping("/{id}")
    public String updateTask(
            @PathVariable Long id,
            @Valid
            @ModelAttribute TaskRequest request,
            BindingResult result,
            @AuthenticationPrincipal User user) {

        if (result.hasErrors()) {
            return "task-form";
        }

        taskService.updateTask(
                id,
                request,
                user
        );

        return "redirect:/tasks";
    }

    // Update task status
    @PostMapping("/{id}/status")
    @ResponseBody
    public void updateStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus status,
            @AuthenticationPrincipal User user) {

        taskService.updateStatus(
                id,
                status,
                user
        );
    }
}