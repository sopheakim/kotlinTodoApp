package com.todoapp.validation

import org.junit.Assert.*
import org.junit.Test

class TodoValidationTest {
    @Test
    fun `test valid title returns true`() {
        val validTitles = listOf(
            "Buy groceries", 
            "A", 
            "A".repeat(100)
        )
        validTitles.forEach { title ->
            assertTrue("Title '$title' should be valid", TodoValidation.isValidTitle(title))
        }
    }

    @Test
    fun `test invalid titles return false`() {
        val invalidTitles = listOf(
            "", 
            "   ", 
            null, 
            "A".repeat(101)
        )
        invalidTitles.forEach { title ->
            assertFalse("Title '${title ?: "null"}' should be invalid", TodoValidation.isValidTitle(title))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test validateTitle throws exception for invalid title`() {
        TodoValidation.validateTitle("")
    }

    @Test
    fun `test validateTitle does not throw for valid titles`() {
        val validTitles = listOf(
            "Valid Title", 
            "A", 
            "A".repeat(100)
        )
        validTitles.forEach { 
            TodoValidation.validateTitle(it) // Should not throw
        }
    }
}