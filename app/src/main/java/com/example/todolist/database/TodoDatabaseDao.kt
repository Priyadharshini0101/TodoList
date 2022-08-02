package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDatabaseDao {
    @Insert
    suspend fun insert(todo:TodoList)

    @Update
    suspend fun update(night: TodoList)

    @Query("SELECT * from todo_list_table WHERE todoId=:key")
    suspend fun get(key: Long):TodoList?

    @Query("DELETE FROM todo_list_table WHERE todoId=:key")
    suspend fun delete(key:Long) :TodoList?

    @Query("SELECT * from todo_list_table")
    fun getAllTodo():LiveData<List<TodoList>>
}


