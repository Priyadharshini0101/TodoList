package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

//kapt "org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0"

@Dao
interface TodoDatabaseDao {
    @Insert
    suspend fun insert(todo:TodoList)

    @Update
    suspend fun update(todo: TodoList)

    @Delete
    suspend fun delete(todo:TodoList)

    @Query("SELECT * from todo_list_table WHERE todoId = :key")
    suspend fun get(key: Long): TodoList?


    @Query("SELECT * from todo_list_table")
    fun getAllTodo():LiveData<List<TodoList>>

    @Query("SELECT * from todo_list_table WHERE todoId = :key")
    fun getNightWithId(key: Long): LiveData<TodoList>
}


