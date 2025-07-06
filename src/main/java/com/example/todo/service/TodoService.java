package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  @Autowired
  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> findAll() {
    return todoRepository.findAll();
  }

  public Optional<Todo> findById(String id) {
    return todoRepository.findById(id);
  }

  public Todo create(Todo todo) {
    todo.setCreatedAt(Instant.now());
    todo.setUpdatedAt(Instant.now());
    return todoRepository.save(todo);
  }

  public Todo update(String id, Todo updateTodo) {
    return todoRepository.findById(id)
        .map(todo -> {
          todo.setTitle(updateTodo.getTitle() != null ? updateTodo.getTitle() : todo.getTitle());
          todo.setDescription(
              updateTodo.getDescription() != null ? updateTodo.getDescription() : todo.getDescription());
          todo.setStatus(updateTodo.getStatus() != null ? updateTodo.getStatus() : todo.getStatus());
          todo.setUpdatedAt(Instant.now());
          return todoRepository.save(todo);
        })
        .orElse(null);
  }

  public Todo delete(String id) {
    Optional<Todo> todo = todoRepository.findById(id);
    todo.ifPresent(t -> todoRepository.deleteById(id));
    return todo.orElse(null);
  }
}
