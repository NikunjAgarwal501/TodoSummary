package com.example.todo_summary.controller;

import com.example.todo_summary.payloads.ApiResponse;
import com.example.todo_summary.payloads.TodoDto;
import com.example.todo_summary.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public List<TodoDto> getTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/todos")
    public TodoDto addTodo (@RequestBody TodoDto todo){
        return todoService.addTodo(todo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(new ApiResponse("Todo Deleted Successfully", true), HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public TodoDto updateTodo(@PathVariable Integer id, @RequestBody TodoDto todoDto){
        return todoService.updateTodo(id, todoDto);
    }

}
