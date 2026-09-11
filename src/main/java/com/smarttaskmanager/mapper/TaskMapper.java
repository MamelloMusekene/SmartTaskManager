package com.smarttaskmanager.mapper;

import com.smarttaskmanager.dto.TaskRequest;
import com.smarttaskmanager.entity.Task;
import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.enums.TaskStatus;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task toEntity(TaskRequest request, User user) {

        return Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .status(TaskStatus.PENDING)
                .user(user)
                .build();
    }
}