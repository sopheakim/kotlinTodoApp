package com.todoapp.validation

/**
 * Provides validation methods for Todo items
 */
object TodoValidation {
    /**
     * Validates the title of a todo item
     * 
     * @param title The title to validate
     * @return Boolean indicating if the title is valid
     */
    fun isValidTitle(title: String?): Boolean {
        // Check if title is null or empty after trimming whitespace
        return !title.isNullOrBlank()
    }

    /**
     * Throws an exception if the title is invalid
     * 
     * @param title The title to validate
     * @throws IllegalArgumentException if title is invalid
     */
    fun validateTitle(title: String?) {
        require(isValidTitle(title)) { "Todo item title cannot be empty" }
    }
}