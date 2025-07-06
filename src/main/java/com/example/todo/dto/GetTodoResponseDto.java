package com.example.todo.dto;

import com.example.todo.model.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetTodoResponseDto {

    @Schema(description = "Unique identifier of the todo", example = "684fec069b744cd74cfa8cf3")
    @NotBlank
    @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "Invalid MongoDB ObjectId")
    private String id;

    @Schema(description = "Title of the todo", example = "Buy groceries")
    @NotBlank
    private String title;

    @Schema(description = "Description of the todo", example = "Buy milk, bread, and eggs from the store")
    @NotBlank
    private String description;

    @Schema(description = "Status of the todo", example = "TODO", allowableValues = {"TODO", "COMPLETED"})
    @NotNull
    private TodoStatus status;

    @Schema(description = "Creation date of the todo", example = "2023-10-10T12:00:00.000Z")
    @NotBlank
    private String createdAt;

    @Schema(description = "Last update date of the todo", example = "2023-10-11T12:00:00.000Z")
    @NotBlank
    private String updatedAt;
}
