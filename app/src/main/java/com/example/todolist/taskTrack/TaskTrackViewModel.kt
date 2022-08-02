package com.example.todolist.taskTrack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.database.TodoDatabaseDao

class TaskTrackViewModel(val database: TodoDatabaseDao, application: Application): AndroidViewModel(application) {

    val todo=database.getAllTodo()

    private var _navigateToAddTasks= MutableLiveData<Boolean>()
    val navigateToAddTasks: LiveData<Boolean>
        get()=_navigateToAddTasks

    fun navigateToAddTasks(){
        _navigateToAddTasks.value=true
    }

    fun doneNavigateToAddTasks(){
        _navigateToAddTasks.value=false
    }

}