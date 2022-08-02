package com.example.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="todo_list_table")
data class TodoList(
    @PrimaryKey(autoGenerate = true)
    var todoId:Long= 0L ,

    @ColumnInfo(name = "task_name")
    var taskName:String = "" ,

    @ColumnInfo(name = "initial_value")
    var initialValue:String = "" ,

    @ColumnInfo(name = "final_value")
    var finalValue:String= ""
)
