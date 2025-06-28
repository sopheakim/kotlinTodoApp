package com.todoapp.data.repository

import com.todoapp.data.model.Todo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TodoRepositoryTest {

    @Mock
    private lateinit var mockTodoDao: TodoDao

    private lateinit var todoRepository: TodoRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        todoRepository = TodoRepository(mockTodoDao)
    }

    @Test
    fun `addTodo should insert valid todo`() = runBlocking {
        val validTodo = Todo(title = "Test Todo")
        `when`(mockTodoDao.insertTodo(validTodo)).thenReturn(1L)

        val result = todoRepository.addTodo(validTodo)

        assertTrue(result.isSuccess)
        assertEquals(1L, result.getOrNull())
        verify(mockTodoDao).insertTodo(validTodo)
    }

    @Test
    fun `addTodo should fail for invalid todo`() = runBlocking {
        val invalidTodo = Todo(title = "")

        val result = todoRepository.addTodo(invalidTodo)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalArgumentException)
    }

    @Test
    fun `deleteTodo should successfully delete a todo`() = runBlocking {
        val todo = Todo(id = 1, title = "Test Todo")
        todoRepository.deleteTodo(todo)

        verify(mockTodoDao).deleteTodo(todo)
    }

    @Test
    fun `updateTodo should update valid todo`() = runBlocking {
        val validTodo = Todo(id = 1, title = "Updated Todo")
        todoRepository.updateTodo(validTodo)

        verify(mockTodoDao).updateTodo(any())
    }
}