package com.example.todolist.addTasks

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

class AddTasksViewModel(val database: TodoDatabaseDao, application: Application): AndroidViewModel(application) {
    private var _inserted= MutableLiveData<Boolean>()
    val inserted: LiveData<Boolean>
        get()=_inserted

    suspend fun insert(todo:TodoList){
      withContext(Dispatchers.IO){
          database.insert(todo)
      }
    }

    fun submitNewTask(id:Long,taskName:String,initialValue:String,finalValue:String){
        viewModelScope.launch {
            val todo=TodoList(id,taskName,initialValue,finalValue)
            insert(todo)
            _inserted.value=true

        }

    }

    fun insertedSuccessFully(){
        _inserted.value=false
    }


}