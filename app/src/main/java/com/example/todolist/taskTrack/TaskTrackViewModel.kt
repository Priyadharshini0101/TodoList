package com.example.todolist.taskTrack

import android.app.Application
import android.util.Log
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

    private var _deleteTodoId= MutableLiveData<Boolean>()
    val deleteTodoId: LiveData<Boolean>
        get()=_deleteTodoId

    private var _updateTodoId= MutableLiveData<String>()
    val updateTodoId: LiveData<String>
        get()=_updateTodoId


    fun navigateToAddTasks(){
        _navigateToAddTasks.value=true
    }

    fun doneNavigateToAddTasks(){
        _navigateToAddTasks.value=false
    }

    suspend fun delete(todoList: TodoList){
        withContext(Dispatchers.IO){
            database.delete(todoList)
        }
    }
    fun deleteTodo(todoList:TodoList){
        viewModelScope.launch {
            delete(todoList)
            _deleteTodoId.value=true
        }
    }

    suspend fun update(todoList: TodoList){
        withContext(Dispatchers.IO){
            database.update(todoList)
        }
    }
    fun addProgress(todoList:TodoList){
            viewModelScope.launch {
                val str=todoList.initialValue.trim()
                var initial =str.toInt()

                val str1=todoList.finalValue.trim()
                val final=str1.toInt()

                if(initial<=final) {
                    initial+=1
                    todoList.initialValue=initial.toString()
                    update(todoList)
                    _updateTodoId.value="Your progress is updated"
                }else{
                    _updateTodoId.value="You have completed the task"
                }

            }
    }

    fun minusProgress(todoList:TodoList){
        viewModelScope.launch {
            val str=todoList.initialValue.trim()
            var initial =str.toInt()

            val str1=todoList.finalValue.trim()
            val final=str1.toInt()

            if(initial>0) {
                initial-=1
                todoList.initialValue=initial.toString()
                update(todoList)
                _updateTodoId.value="Your progress is updated"
            }else{
                _updateTodoId.value="You have not done any task yet"
            }

        }
    }



}