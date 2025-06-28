package com.example.todoapp.data.dao

import androidx.room.*
import com.example.todoapp.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {
    /**
     * Inserts a new todo item into the database.
     * @param todoItem The todo item to insert
     * @return The ID of the newly inserted item
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoItem: TodoItem): Long

    /**
     * Inserts multiple todo items into the database.
     * @param todoItems The list of todo items to insert
     * @return List of inserted item IDs
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todoItems: List<TodoItem>): List<Long>

    /**
     * Updates an existing todo item.
     * @param todoItem The todo item with updated information
     */
    @Update
    suspend fun update(todoItem: TodoItem)

    /**
     * Deletes a specific todo item.
     * @param todoItem The todo item to delete
     */
    @Delete
    suspend fun delete(todoItem: TodoItem)

    /**
     * Retrieves a todo item by its ID.
     * @param id The ID of the todo item
     * @return The todo item or null if not found
     */
    @Query("SELECT * FROM todo_items WHERE id = :id")
    suspend fun getById(id: Long): TodoItem?

    /**
     * Retrieves all todo items, sorted by creation date.
     * @return A Flow of todo items
     */
    @Query("SELECT * FROM todo_items ORDER BY createdAt DESC")
    fun getAllTodoItems(): Flow<List<TodoItem>>

    /**
     * Retrieves completed todo items.
     * @return A Flow of completed todo items
     */
    @Query("SELECT * FROM todo_items WHERE isCompleted = 1 ORDER BY updatedAt DESC")
    fun getCompletedTodoItems(): Flow<List<TodoItem>>

    /**
     * Retrieves active (not completed) todo items.
     * @return A Flow of active todo items
     */
    @Query("SELECT * FROM todo_items WHERE isCompleted = 0 ORDER BY createdAt DESC")
    fun getActiveTodoItems(): Flow<List<TodoItem>>

    /**
     * Deletes all todo items from the database.
     */
    @Query("DELETE FROM todo_items")
    suspend fun deleteAll()

    /**
     * Counts the total number of todo items.
     * @return The total number of todo items
     */
    @Query("SELECT COUNT(*) FROM todo_items")
    suspend fun count(): Int
}