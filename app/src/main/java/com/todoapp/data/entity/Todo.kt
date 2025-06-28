package com.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * Room database entity representing a Todo item.
 * 
 * @property id Unique identifier for the Todo item
 * @property title Title of the Todo item
 * @property description Detailed description of the Todo item
 * @property isCompleted Indicates whether the Todo item is completed
 * @property createdAt Timestamp of when the Todo item was created
 * @property dueDate Optional due date for the Todo item
 */
@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "due_date")
    val dueDate: LocalDateTime? = null
) {
    /**
     * Validates the Todo item properties.
     * 
     * @throws IllegalArgumentException if validation fails
     */
    init {
        require(title.isNotBlank()) { "Title cannot be blank" }
        require(title.length <= 100) { "Title cannot exceed 100 characters" }
        description?.let {
            require(it.length <= 500) { "Description cannot exceed 500 characters" }
        }
    }
}