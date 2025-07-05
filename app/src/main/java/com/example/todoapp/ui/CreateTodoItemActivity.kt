package com.example.todoapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todoapp.R
import com.example.todoapp.data.TodoItem
import com.example.todoapp.data.TodoRepository
import kotlinx.coroutines.launch

class CreateTodoItemActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var todoRepository: TodoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo_item)

        // Initialize views
        titleEditText = findViewById(R.id.edit_text_todo_title)
        descriptionEditText = findViewById(R.id.edit_text_todo_description)
        saveButton = findViewById(R.id.button_save_todo)

        // Initialize repository (assumes dependency injection or manual initialization)
        todoRepository = TodoRepository(application)

        saveButton.setOnClickListener {
            saveTodoItem()
        }
    }

    private fun saveTodoItem() {
        val title = titleEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()

        // Basic validation
        if (title.isEmpty()) {
            titleEditText.error = "Title cannot be empty"
            return
        }

        // Create todo item
        val todoItem = TodoItem(
            title = title,
            description = description,
            isCompleted = false
        )

        // Save todo item using coroutines
        lifecycleScope.launch {
            todoRepository.insert(todoItem)
            // Navigate back to main activity
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        // Clear the back stack so user can't navigate back to create todo screen
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        // Finish current activity
        finish()
    }
}