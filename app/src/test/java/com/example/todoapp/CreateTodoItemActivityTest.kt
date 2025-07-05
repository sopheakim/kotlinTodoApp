package com.example.todoapp

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todoapp.ui.CreateTodoItemActivity
import com.example.todoapp.ui.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf

@RunWith(AndroidJUnit4::class)
class CreateTodoItemActivityTest {

    @Test
    fun `test navigation to main activity after saving todo item`() {
        // Create an activity scenario
        val scenario = ActivityScenario.launch(CreateTodoItemActivity::class.java)

        scenario.onActivity { activity ->
            // Mock saving a todo item by directly calling private method
            val method = activity.javaClass.getDeclaredMethod("navigateToMainActivity")
            method.isAccessible = true
            method.invoke(activity)

            // Get the next started intent
            val shadowActivity = Robolectric.shadowOf(activity)
            val startedIntent = shadowActivity.nextStartedActivity

            // Verify intent navigation
            assertEquals(MainActivity::class.java.name, 
                         startedIntent.component?.className)
            
            // Verify intent flags
            assertEquals(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK, 
                startedIntent.flags
            )
        }
    }
}