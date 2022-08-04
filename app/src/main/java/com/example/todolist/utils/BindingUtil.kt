package com.example.todolist.taskTrack

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.database.TodoList
import com.google.android.material.progressindicator.LinearProgressIndicator

@BindingAdapter("taskName")
fun TextView.setTaskName(item: String?){
    item?.let{
        text= item
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("progressValue")
fun TextView.setProgressValue(item:TodoList?){
    item?.let{
        text= item.initialValue + " / " +item.finalValue
    }
}

@BindingAdapter("progressBar")
fun LinearProgressIndicator.setProgressBarRange(item:TodoList?){
    item?.let{
        max = item.finalValue.toInt()
        progress = item.initialValue.toInt()
    }
}


