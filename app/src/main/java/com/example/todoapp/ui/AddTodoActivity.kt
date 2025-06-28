package com.example.todoapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.example.todoapp.R
import com.example.todoapp.data.TodoItem

class AddTodoActivity : AppCompatActivity() {
    private lateinit var titleEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        // Initialize views
        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        saveButton = findViewById(R.id.saveButton)

        // Set up save button click listener
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

        // Create TodoItem (assuming TodoItem class exists)
        val todoItem = TodoItem(
            title = title,
            description = description,
            isCompleted = false
        )

        // TODO: Save to repository or database
        Toast.makeText(this, "Todo saved!", Toast.LENGTH_SHORT).show()
        finish() // Close the activity after saving
    }
}