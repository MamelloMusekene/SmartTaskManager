package com.smarttaskmanager.dto;

public record CalendarEvent(
        Long id,
        String title,
        String start,
        String url,
        String className
) {
}