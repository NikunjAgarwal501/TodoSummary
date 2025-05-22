package com.example.todo_summary.service;

import com.example.todo_summary.entity.Todo;
import com.example.todo_summary.exception.ResourceNotFoundException;
import com.example.todo_summary.payloads.TodoDto;
import com.example.todo_summary.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepo;
    @Autowired
    private ModelMapper modelMapper;
    public List<TodoDto> getAllTodos(){
        return todoRepo.findAll().stream().map((item)->modelMapper.map(item, TodoDto.class)).toList();
    }

    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        return modelMapper.map(todoRepo.save(todo), TodoDto.class);
    }

    public void deleteTodo(Integer id) {
        todoRepo.deleteById(id);
    }
    public TodoDto updateTodo(Integer id, TodoDto todoDto) {
        Todo existing = todoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo","todoId",id));
        existing.setTitle(todoDto.getTitle());
        return modelMapper.map(todoRepo.save(existing), TodoDto.class);
    }
}

