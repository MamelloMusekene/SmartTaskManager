package com.smarttaskmanager.controller;

import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final TaskService taskService;

    @GetMapping("/dashboard")
    public String dashboard(

            @AuthenticationPrincipal User user,

            Model model) {

        model.addAttribute("user", user);

        model.addAttribute("totalTasks",
                taskService.getTotalTasks(user));

        model.addAttribute("pendingTasks",
                taskService.getPendingTasks(user));

        model.addAttribute("completedTasks",
                taskService.getCompletedTasks(user));

        long completed = taskService.getCompletedTasks(user);
        long pending = taskService.getPendingTasks(user);
        long total = taskService.getTotalTasks(user);

        double percentage = total == 0
                ? 0
                : (completed * 100.0) / total;

        model.addAttribute("completionRate", percentage);
        model.addAttribute("completedTasks", completed);
        model.addAttribute("pendingTasks", pending);

        model.addAttribute("overdueTasks",
                taskService.getOverdueTasks(user));

        model.addAttribute("recentTasks",
                taskService.getRecentTasks(user));

        return "dashboard";
    }

}
