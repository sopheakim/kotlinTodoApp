package com.example.todoapp.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todoapp.data.model.TodoItem
import com.example.todoapp.data.database.TodoDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TodoItemDaoTest {
    private lateinit var todoItemDao: TodoItemDao
    private lateinit var database: TodoDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        todoItemDao = database.todoItemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTodoItem() = runBlocking {
        val todoItem = TodoItem(title = "Test Todo", description = "Test Description")
        val id = todoItemDao.insert(todoItem)
        val retrievedItem = todoItemDao.getById(id)
        
        assertNotNull(retrievedItem)
        assertEquals("Test Todo", retrievedItem?.title)
        assertEquals("Test Description", retrievedItem?.description)
    }

    @Test
    @Throws(Exception::class)
    fun updateTodoItem() = runBlocking {
        val todoItem = TodoItem(title = "Original Title")
        val id = todoItemDao.insert(todoItem)
        
        val updatedItem = todoItem.copy(id = id, title = "Updated Title")
        todoItemDao.update(updatedItem)
        
        val retrievedItem = todoItemDao.getById(id)
        assertEquals("Updated Title", retrievedItem?.title)
    }

    @Test
    @Throws(Exception::class)
    fun deleteTodoItem() = runBlocking {
        val todoItem = TodoItem(title = "Item to Delete")
        val id = todoItemDao.insert(todoItem)
        
        val retrievedItem = todoItemDao.getById(id)
        assertNotNull(retrievedItem)
        
        todoItemDao.delete(retrievedItem!!)
        val deletedItem = todoItemDao.getById(id)
        assertNull(deletedItem)
    }

    @Test
    @Throws(Exception::class)
    fun getAllTodoItems() = runBlocking {
        val todoItems = listOf(
            TodoItem(title = "Item 1"),
            TodoItem(title = "Item 2"),
            TodoItem(title = "Item 3")
        )
        todoItemDao.insertAll(todoItems)
        
        val allItems = todoItemDao.getAllTodoItems().first()
        assertEquals(3, allItems.size)
    }

    @Test
    @Throws(Exception::class)
    fun getCompletedTodoItems() = runBlocking {
        val todoItems = listOf(
            TodoItem(title = "Completed 1", isCompleted = true),
            TodoItem(title = "Completed 2", isCompleted = true),
            TodoItem(title = "Active", isCompleted = false)
        )
        todoItemDao.insertAll(todoItems)
        
        val completedItems = todoItemDao.getCompletedTodoItems().first()
        assertEquals(2, completedItems.size)
    }
}