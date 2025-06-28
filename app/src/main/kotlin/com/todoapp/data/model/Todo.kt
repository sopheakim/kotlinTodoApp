package com.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

/**
 * Represents a Todo item with comprehensive properties.
 *
 * @property id Unique identifier for the todo item
 * @property title Title/description of the todo item
 * @property description Detailed description of the todo item (optional)
 * @property createdAt Timestamp of when the todo item was created
 * @property updatedAt Timestamp of the last update to the todo item
 * @property dueDate Optional due date for the todo item
 * @property isCompleted Flag indicating whether the todo item is completed
 * @property priority Priority level of the todo item
 */
@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val dueDate: LocalDateTime? = null,
    val isCompleted: Boolean = false,
    val priority: TodoPriority = TodoPriority.MEDIUM
)

/**
 * Enum representing the priority levels for a Todo item.
 */
enum class TodoPriority {
    LOW,
    MEDIUM,
    HIGH
}