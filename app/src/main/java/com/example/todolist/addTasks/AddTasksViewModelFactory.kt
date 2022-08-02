package com.example.todolist.addTasks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.database.TodoDatabaseDao
import com.example.todolist.taskTrack.TaskTrackViewModel


class AddTasksViewModelFactory(private val dataSource: TodoDatabaseDao,
                               private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskTrackViewModel::class.java)) {
            return TaskTrackViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
