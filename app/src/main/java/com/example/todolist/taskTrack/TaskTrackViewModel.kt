package com.example.todolist.taskTrack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.TodoDatabaseDao
import com.example.todolist.database.TodoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskTrackViewModel(val database: TodoDatabaseDao, application: Application): AndroidViewModel(application) {

    val todo=database.getAllTodo()

    private var _navigateToAddTasks= MutableLiveData<Boolean>()
    val navigateToAddTasks: LiveData<Boolean>
        get()=_navigateToAddTasks

    private var _navigateToUpdateTasks= MutableLiveData<Long>()
    val navigateToUpdateTasks: LiveData<Long>
        get()=_navigateToUpdateTasks



    fun navigateToAddTasks(){
        _navigateToAddTasks.value=true
    }

    fun doneNavigateToAddTasks(){
        _navigateToAddTasks.value=false
    }

    fun navigateToUpdateTasks(todoId:Long){
     _navigateToUpdateTasks.value=todoId
    }

    fun doneNavigateToUpdateTasks(){
        _navigateToUpdateTasks.value=0L
    }

}