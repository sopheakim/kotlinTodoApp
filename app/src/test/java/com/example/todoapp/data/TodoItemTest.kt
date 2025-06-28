package com.example.todoapp.data

import org.junit.Assert.*
import org.junit.Test

class TodoItemTest {
    @Test
    fun `todo item creation with valid data`() {
        val todoItem = TodoItem(
            title = "Test Todo",
            description = "Test Description"
        )
        
        assertEquals("Test Todo", todoItem.title)
        assertEquals("Test Description", todoItem.description)
        assertFalse(todoItem.isCompleted)
    }

    @Test
    fun `todo item validation should pass with non-empty title`() {
        val todoItem = TodoItem(title = "Valid Title")
        assertTrue(todoItem.validate())
    }

    @Test
    fun `todo item validation should fail with empty title`() {
        val todoItem = TodoItem(title = "")
        assertFalse(todoItem.validate())
    }

    @Test
    fun `todo item can have optional description`() {
        val todoItem = TodoItem(title = "Test", description = "")
        assertEquals("", todoItem.description)
        assertTrue(todoItem.validate())
    }
}