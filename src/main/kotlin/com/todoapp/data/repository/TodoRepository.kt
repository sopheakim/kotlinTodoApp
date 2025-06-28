package com.todoapp.data.repository

import com.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<Todo>> = todoDao.getAllTodos()
        .catch { emit(emptyList()) }

    fun getTodosByCompletionStatus(completed: Boolean): Flow<List<Todo>> = 
        todoDao.getTodosByCompletionStatus(completed)
            .catch { emit(emptyList()) }

    suspend fun getTodoById(id: Int): Todo? {
        return try {
            todoDao.getTodoById(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addTodo(todo: Todo): Result<Long> {
        return try {
            if (!todo.validate()) {
                Result.failure(IllegalArgumentException("Invalid todo item"))
            } else {
                val id = todoDao.insertTodo(todo)
                Result.success(id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateTodo(todo: Todo): Result<Unit> {
        return try {
            if (!todo.validate()) {
                Result.failure(IllegalArgumentException("Invalid todo item"))
            } else {
                todoDao.updateTodo(todo.copy(updatedAt = System.currentTimeMillis()))
                Result.success(Unit)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteTodo(todo: Todo): Result<Unit> {
        return try {
            todoDao.deleteTodo(todo)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteTodoById(todoId: Int): Result<Unit> {
        return try {
            todoDao.deleteTodoById(todoId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}