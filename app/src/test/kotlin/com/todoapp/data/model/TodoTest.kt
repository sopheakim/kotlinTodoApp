package com.todoapp.data.model

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class TodoTest {

    @Test
    fun `create todo with default values`() {
        val todo = Todo(title = "Test Todo")
        
        assertNotNull(todo.id)
        assertEquals("Test Todo", todo.title)
        assertNull(todo.description)
        assertFalse(todo.isCompleted)
        assertEquals(TodoPriority.MEDIUM, todo.priority)
    }

    @Test
    fun `create todo with all properties`() {
        val dueDate = LocalDateTime.now().plusDays(1)
        val todo = Todo(
            title = "Complete Project",
            description = "Finish Android Todo App",
            dueDate = dueDate,
            isCompleted = true,
            priority = TodoPriority.HIGH
        )

        assertEquals("Complete Project", todo.title)
        assertEquals("Finish Android Todo App", todo.description)
        assertEquals(dueDate, todo.dueDate)
        assertTrue(todo.isCompleted)
        assertEquals(TodoPriority.HIGH, todo.priority)
    }

    @Test
    fun `check todo priority enum`() {
        val priorities = TodoPriority.values()
        assertEquals(3, priorities.size)
        assertTrue(priorities.contains(TodoPriority.LOW))
        assertTrue(priorities.contains(TodoPriority.MEDIUM))
        assertTrue(priorities.contains(TodoPriority.HIGH))
    }
}