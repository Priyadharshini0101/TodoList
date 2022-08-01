package com.example.todolist.taskTrack

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskTrackViewModel(application: Application): ViewModel() {
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