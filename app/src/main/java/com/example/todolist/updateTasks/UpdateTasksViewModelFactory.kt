package com.example.todolist.updateTasks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.database.TodoDatabaseDao

class UpdateTasksViewModelFactory(private val dataSource: TodoDatabaseDao, private val application: Application,private val todoId:Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateTasksViewModel::class.java)) {
            return UpdateTasksViewModel(dataSource,application,todoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
