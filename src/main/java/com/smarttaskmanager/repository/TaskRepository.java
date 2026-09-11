package com.smarttaskmanager.repository;

import com.smarttaskmanager.entity.Task;
import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.enums.TaskStatus;
import com.smarttaskmanager.enums.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

    List<Task> findByUserOrderByDueDateAsc(User user);

    List<Task> findByUserAndStatus(User user, TaskStatus status);

    Optional<Task> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, TaskStatus status);

    long countByUserAndDueDateBeforeAndStatus(
            User user,
            LocalDate date,
            TaskStatus status);

    List<Task> findTop5ByUserOrderByCreatedAtDesc(User user);

    List<Task> findByUserAndTitleContainingIgnoreCase(
            User user,
            String title);

    List<Task> findByUserAndPriority(
            User user,
            TaskPriority priority);

    List<Task> findByUserAndDueDate(
            User user,
            LocalDate dueDate);

    List<Task> findByUserAndStatusOrderByDueDateAsc(
            User user,
            TaskStatus status);
}