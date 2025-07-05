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
        assertTrue(todoItem.createdAt > 0)
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
    fun `todo item validation should fail with whitespace-only title`() {
        val todoItem = TodoItem(title = "   ")
        assertFalse(todoItem.validate())
    }

    @Test
    fun `todo item validation should fail with very long title`() {
        val longTitle = "A".repeat(101)
        val todoItem = TodoItem(title = longTitle)
        assertFalse(todoItem.validate())
    }

    @Test
    fun `todo item can have optional description`() {
        val todoItem = TodoItem(title = "Test", description = "")
        assertEquals("", todoItem.description)
        assertTrue(todoItem.validate())
    }

    @Test
    fun `todo item should have creation timestamp`() {
        val todoItem = TodoItem(title = "Test")
        assertTrue(todoItem.createdAt > 0)
    }
}