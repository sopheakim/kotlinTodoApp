package com.example.todoapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {
    private lateinit var repository: TodoRepository
    private lateinit var viewModel: TodoViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock(TodoRepository::class.java)
        viewModel = TodoViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `deleteTodoItem successfully deletes item`() = runTest {
        // Arrange
        val todoItem = TodoItem(1, "Test Todo", false)

        // Act
        viewModel.deleteTodoItem(todoItem)

        // Assert
        verify(repository).delete(todoItem)
        assertEquals(
            TodoViewModel.DeletionState.Success, 
            viewModel.deletionState.value
        )
    }

    @Test
    fun `deleteTodoItem handles deletion error`() = runTest {
        // Arrange
        val todoItem = TodoItem(1, "Test Todo", false)
        val exception = RuntimeException("Deletion failed")
        `when`(repository.delete(todoItem)).thenThrow(exception)

        // Act
        viewModel.deleteTodoItem(todoItem)

        // Assert
        assertEquals(
            TodoViewModel.DeletionState.Error("Failed to delete todo item: ${exception.localizedMessage}"),
            viewModel.deletionState.value
        )
    }
}