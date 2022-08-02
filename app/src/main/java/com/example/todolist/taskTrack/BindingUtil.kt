package com.example.todolist.taskTrack

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.database.TodoList
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlin.math.max

//Other way of binding the data
//To bind the data with the function so that item takes the text value
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
        max = Integer.valueOf(item.finalValue)
        progress=Integer.valueOf(item.initialValue)
    }
}


