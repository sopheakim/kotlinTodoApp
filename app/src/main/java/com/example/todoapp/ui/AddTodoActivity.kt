package com.example.todoapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.example.todoapp.R
import com.example.todoapp.data.TodoItem
import com.example.todoapp.data.TodoRepository
import kotlinx.coroutines.launch

class AddTodoActivity : AppCompatActivity() {
    private lateinit var titleEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private lateinit var saveButton: Button
    private lateinit var todoRepository: TodoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        // Initialize views
        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        saveButton = findViewById(R.id.saveButton)

        // Initialize repository
        todoRepository = TodoRepository(application)

        // Set up save button click listener
        saveButton.setOnClickListener {
            saveTodoItem()
        }
    }

    private fun saveTodoItem() {
        val title = titleEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()

        // Enhanced validation
        if (title.isEmpty()) {
            titleEditText.error = "Title cannot be empty"
            return
        }

        if (title.length > 100) {
            titleEditText.error = "Title must be 100 characters or less"
            return
        }

        // Create TodoItem
        val todoItem = TodoItem(
            title = title,
            description = description,
            isCompleted = false
        )

        // Save asynchronously
        lifecycleScope.launch {
            try {
                todoRepository.insert(todoItem)
                Toast.makeText(this@AddTodoActivity, "Todo saved successfully!", Toast.LENGTH_SHORT).show()
                finish() // Close the activity after saving
            } catch (e: Exception) {
                Toast.makeText(this@AddTodoActivity, "Failed to save todo: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}