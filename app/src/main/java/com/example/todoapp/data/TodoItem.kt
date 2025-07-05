package com.example.todoapp.data

data class TodoItem(
    val id: Long = 0, // Unique identifier, defaults to 0 for new items
    val title: String,
    val description: String = "", // Optional description with default empty string
    val isCompleted: Boolean = false, // Default state is not completed
    val createdAt: Long = System.currentTimeMillis() // Timestamp of creation
) {
    /**
     * Validates the todo item's core attributes
     * 
     * @return Boolean indicating if the todo item is valid
     */
    fun validate(): Boolean {
        return title.trim().isNotBlank() && title.length <= 100 // Added length constraint
    }
}