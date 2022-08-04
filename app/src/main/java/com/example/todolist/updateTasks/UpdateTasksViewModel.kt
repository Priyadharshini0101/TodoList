package com.example.todolist.updateTasks

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.database.TodoDatabaseDao
import com.example.todolist.database.TodoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateTasksViewModel(val database: TodoDatabaseDao, application: Application,private val todoId:Long): AndroidViewModel(application) {
     var id:Long
    private val todo = MediatorLiveData<TodoList>()
    fun getTodo() = todo
    init{
        id=todoId
        todo.addSource(database.getNightWithId(id), todo::setValue)
    }

    private var _deleteTodoId= MutableLiveData<Boolean>()
    val deleteTodoId: LiveData<Boolean>
        get()=_deleteTodoId

    private var _updateTodoId= MutableLiveData<String?>()
    val updateTodoId: LiveData<String?>
        get()=_updateTodoId

    suspend fun delete(todoList: TodoList){
        withContext(Dispatchers.IO){
            database.delete(todoList)
        }
    }

    fun deleteTodo(){
        viewModelScope.launch {
            val todoList=database.get(id) ?: return@launch
            delete(todoList)
            _deleteTodoId.value=true
        }
    }

    fun doneDelete(){
        _deleteTodoId.value=false
    }

    suspend fun update(todoList: TodoList){
        withContext(Dispatchers.IO){
            database.update(todoList)
        }
    }

    fun doneUpdate(){
        _updateTodoId.value=null
    }

    fun addProgress(){
        viewModelScope.launch {
            val todoList=database.get(id) ?: return@launch
            val str=todoList.initialValue.trim()
            var initial =str.toInt()

            val str1=todoList.finalValue.trim()
            val final=str1.toInt()

            if(initial<final) {
                initial+=1
                todoList.initialValue=initial.toString()
                update(todoList)
                _updateTodoId.value="Your progress is updated"
            }else{
                _updateTodoId.value="You have completed the task"
            }
        }
    }

    fun minusProgress(){
        viewModelScope.launch {
            val todoList=database.get(id) ?: return@launch
            val str=todoList.initialValue.trim()
            var initial =str.toInt()

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


