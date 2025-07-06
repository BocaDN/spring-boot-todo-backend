package com.example.todo.dto;

import com.example.todo.model.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTodoRequestDto {

    @Schema(description = "Title of the todo item", example = "Buy groceries")
    @NotBlank
    private String title;

    @Schema(description = "Description of the todo item", example = "Milk, eggs, and bread")
    @NotBlank
    private String description;

    @Schema(description = "Status of the todo item", example = "TODO", allowableValues = {"TODO", "COMPLETED"})
    @NotNull
    private TodoStatus status;
}
