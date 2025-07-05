package com.todoapp.data.entity

import org.junit.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class TodoTest {

    @Test
    fun `create todo with valid data`() {
        val todo = Todo(
            title = "Test Todo",
            description = "Test description"
        )

        assertNotNull(todo)
        assertEquals("Test Todo", todo.title)
        assertEquals("Test description", todo.description)
        assertEquals(false, todo.isCompleted)
        assertNotNull(todo.createdAt)
    }

    @Test
    fun `create todo with all properties`() {
        val dueDate = LocalDateTime.now().plusDays(1)
        val todo = Todo(
            title = "Complete project",
            description = "Finish the todo app implementation",
            isCompleted = true,
            dueDate = dueDate
        )

        assertEquals("Complete project", todo.title)
        assertEquals("Finish the todo app implementation", todo.description)
        assertEquals(true, todo.isCompleted)
        assertEquals(dueDate, todo.dueDate)
    }

    @Test
    fun `fail to create todo with blank title`() {
        assertFailsWith<IllegalArgumentException> {
            Todo(title = "")
        }
    }

    @Test
    fun `fail to create todo with title exceeding 100 characters`() {
        val longTitle = "a".repeat(101)
        assertFailsWith<IllegalArgumentException> {
            Todo(title = longTitle)
        }
    }

    @Test
    fun `fail to create todo with description exceeding 500 characters`() {
        val longDescription = "a".repeat(501)
        assertFailsWith<IllegalArgumentException> {
            Todo(
                title = "Valid Title",
                description = longDescription
            )
        }
    }
}