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
public class CalendarController {

    private final TaskService taskService;

    @GetMapping("/calendar")
    public String calendar(
            @AuthenticationPrincipal User user,
            Model model) {

        model.addAttribute(
                "calendarEvents",
                taskService.getCalendarEvents(user)
        );

        return "calendar";
    }
}