package com.example.todo.controller;

import com.example.todo.dto.*;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Todos", description = "Todo API")
@RestController
@RequestMapping("/todo")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @Operation(summary = "Get all todos")
  @ApiResponse(responseCode = "200", description = "List of all todos")
  @GetMapping
  public ResponseEntity<List<GetTodoResponseDto>> getAllTodos() {
    List<Todo> todos = todoService.findAll();
    List<GetTodoResponseDto> dtos = todos.stream()
        .map(this::toGetTodoResponseDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @Operation(summary = "Get a todo by ID")
  @ApiResponse(responseCode = "200", description = "Todo item")
  @GetMapping("/{id}")
  public ResponseEntity<GetTodoResponseDto> getTodoById(
      @Parameter(description = "Todo ID") @PathVariable String id) {
    return todoService.findById(id)
        .map(todo -> ResponseEntity.ok(toGetTodoResponseDto(todo)))
        .orElse(ResponseEntity.notFound().build());
  }

  @Operation(summary = "Create a new todo")
  @ApiResponse(responseCode = "201", description = "Created todo")
  @PostMapping
  public ResponseEntity<CreateTodoResponseDto> createTodo(
      @RequestBody CreateTodoRequestDto createTodoDto) {
    Todo todoEntity = toTodoEntity(createTodoDto);
    Todo created = todoService.create(todoEntity);
    return new ResponseEntity<>(toCreateTodoResponseDto(created), HttpStatus.CREATED);
  }

  @Operation(summary = "Update an existing todo")
  @ApiResponse(responseCode = "200", description = "Updated todo")
  @PutMapping("/{id}")
  public ResponseEntity<UpdateTodoResponseDto> updateTodo(
      @Parameter(description = "Todo ID") @PathVariable String id,
      @RequestBody UpdateTodoRequestDto updateTodoDto) {
    Todo updateEntity = toTodoEntity(updateTodoDto);
    Todo updated = todoService.update(id, updateEntity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toUpdateTodoResponseDto(updated));
  }

  @Operation(summary = "Delete a todo by ID")
  @ApiResponse(responseCode = "200", description = "Todo deleted successfully")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(
      @Parameter(description = "Todo ID") @PathVariable String id) {
    Todo deleted = todoService.delete(id);
    if (deleted == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().build();
  }

  // --- Mappers ---

  private Todo toTodoEntity(CreateTodoRequestDto dto) {
    Todo todo = new Todo();
    todo.setTitle(dto.getTitle());
    todo.setDescription(dto.getDescription());
    todo.setStatus(dto.getStatus());
    // createdAt and updatedAt set in service
    return todo;
  }

  private Todo toTodoEntity(UpdateTodoRequestDto dto) {
    Todo todo = new Todo();
    todo.setTitle(dto.getTitle());
    todo.setDescription(dto.getDescription());
    todo.setStatus(dto.getStatus());
    return todo;
  }

  private GetTodoResponseDto toGetTodoResponseDto(Todo todo) {
    GetTodoResponseDto dto = new GetTodoResponseDto();
    dto.setId(todo.getId());
    dto.setTitle(todo.getTitle());
    dto.setDescription(todo.getDescription());
    dto.setStatus(todo.getStatus());
    dto.setCreatedAt(todo.getCreatedAt() != null ? todo.getCreatedAt().toString() : null);
    dto.setUpdatedAt(todo.getUpdatedAt() != null ? todo.getUpdatedAt().toString() : null);
    return dto;
  }

  private CreateTodoResponseDto toCreateTodoResponseDto(Todo todo) {
    CreateTodoResponseDto dto = new CreateTodoResponseDto();
    dto.setId(todo.getId());
    dto.setTitle(todo.getTitle());
    dto.setDescription(todo.getDescription());
    dto.setStatus(todo.getStatus());
    dto.setCreatedAt(todo.getCreatedAt() != null ? todo.getCreatedAt().toString() : null);
    dto.setUpdatedAt(todo.getUpdatedAt() != null ? todo.getUpdatedAt().toString() : null);
    return dto;
  }

  private UpdateTodoResponseDto toUpdateTodoResponseDto(Todo todo) {
    UpdateTodoResponseDto dto = new UpdateTodoResponseDto();
    dto.setId(todo.getId());
    dto.setTitle(todo.getTitle());
    dto.setDescription(todo.getDescription());
    dto.setStatus(todo.getStatus());
    dto.setCreatedAt(todo.getCreatedAt() != null ? todo.getCreatedAt().toString() : null);
    dto.setUpdatedAt(todo.getUpdatedAt() != null ? todo.getUpdatedAt().toString() : null);
    return dto;
  }
}
