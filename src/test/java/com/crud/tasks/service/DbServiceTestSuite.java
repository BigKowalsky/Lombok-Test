package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class DbServiceTestSuite {
    @Autowired
    private DbService dbService;

    @Test
    public void shouldGetAllTasks() {
        //GIVEN
        Task task1 = new Task(1L, "test_1_title", "test_1_content");
        long taskId1 = dbService.saveTask(task1).getId();
        Task task2 = new Task(1L, "test_2_title", "test_2_content");
        long taskId2 = dbService.saveTask(task2).getId();
        //WHEN
        List<Task> tasks = dbService.getAllTasks();
        //THEN
        assertEquals(2, tasks.size());
        //CLEANUP
        dbService.deleteAll();

    }

    @Test
    public void shouldGetTaskById() throws TaskNotFoundException {
        //GIVEN
        Task task = new Task(1L, "test_title", "test_content");
        long taskId = dbService.saveTask(task).getId();
        //WHEN
        Task thisTask = dbService.getTask(taskId);
        Long thisTaskId = thisTask.getId();
        //THEN
        assertEquals(thisTaskId, thisTask.getId());
        assertEquals("test_title",thisTask.getTitle());
        assertEquals("test_content", thisTask.getContent());
        //CLEANUP
        dbService.deleteAll();
    }

    @Test
    public void shouldSaveTask() {
        //GIVEN
        Task task = new Task(1L, "test_title", "test_content");
        //WHEN
        long taskId = dbService.saveTask(task).getId();
        //THEN
        assertNotEquals(0, taskId);
        //CLEANUP
        dbService.deleteAll();
    }

    @Test
    public void shouldDeleteTask() throws TaskNotFoundException {
        //GIVEN
        Task task = new Task(1L, "test_title", "test_content");
        long taskId = dbService.saveTask(task).getId();
        //WHEN
        dbService.deleteTask(taskId);
        //THEN
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(taskId));
    }
}