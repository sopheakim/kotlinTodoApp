package com.todoapp.validation

import org.junit.Assert.*
import org.junit.Test

class TodoValidationTest {
    @Test
    fun `test valid title returns true`() {
        // Arrange
        val validTitle = "Buy groceries"
        
        // Act
        val result = TodoValidation.isValidTitle(validTitle)
        
        // Assert
        assertTrue("Valid title should return true", result)
    }

    @Test
    fun `test empty title returns false`() {
        // Arrange
        val emptyTitle = ""
        
        // Act
        val result = TodoValidation.isValidTitle(emptyTitle)
        
        // Assert
        assertFalse("Empty title should return false", result)
    }

    @Test
    fun `test null title returns false`() {
        // Arrange
        val nullTitle: String? = null
        
        // Act
        val result = TodoValidation.isValidTitle(nullTitle)
        
        // Assert
        assertFalse("Null title should return false", result)
    }

    @Test
    fun `test whitespace-only title returns false`() {
        // Arrange
        val whitespaceTitle = "   "
        
        // Act
        val result = TodoValidation.isValidTitle(whitespaceTitle)
        
        // Assert
        assertFalse("Whitespace-only title should return false", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test validateTitle throws exception for invalid title`() {
        // Act
        TodoValidation.validateTitle("")
    }

    @Test
    fun `test validateTitle does not throw for valid title`() {
        // Act
        TodoValidation.validateTitle("Valid Title")
        // Assert (no exception thrown)
    }
}