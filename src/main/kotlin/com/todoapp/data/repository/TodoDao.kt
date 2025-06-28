package com.todoapp.data.repository

import androidx.room.*
import com.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY createdAt DESC")
    fun getAllTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todos WHERE id = :todoId")
    suspend fun getTodoById(todoId: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo): Long

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("DELETE FROM todos WHERE id = :todoId")
    suspend fun deleteTodoById(todoId: Int)

    @Query("SELECT * FROM todos WHERE isCompleted = :completed")
    fun getTodosByCompletionStatus(completed: Boolean): Flow<List<Todo>>
}