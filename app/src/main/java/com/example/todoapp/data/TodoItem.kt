package com.example.todoapp.data

data class TodoItem(
    val id: Long = 0, // Default 0 for new items
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false
) {
    // Validation method
    fun validate(): Boolean {
        return title.isNotBlank()
    }
}