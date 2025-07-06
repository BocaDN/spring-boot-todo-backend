package com.example.todo.dto;

import com.example.todo.model.TodoStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateTodoResponseDto {

    @Schema(description = "MongoDB ObjectId of the todo item", example = "507f1f77bcf86cd799439011")
    @NotBlank
    @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "Invalid MongoDB ObjectId")
    @JsonProperty("_id")
    private String id;

    @Schema(description = "Title of the todo item")
    @NotBlank
    private String title;

    @Schema(description = "Description of the todo item")
    @NotBlank
    private String description;

    @Schema(description = "Status of the todo item", allowableValues = {"TODO", "COMPLETED"})
    @NotNull
    private TodoStatus status;

    @Schema(description = "Timestamp when the todo was created")
    @NotBlank
    private String createdAt;

    @Schema(description = "Timestamp when the todo was last updated")
    @NotBlank
    private String updatedAt;
}
