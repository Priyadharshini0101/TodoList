package com.example.todolist.addTasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todolist.R
import com.example.todolist.databinding.FragmentAddTasksBinding

class AddTasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentAddTasksBinding  = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_tasks, container, false)

        return binding.root
    }


}