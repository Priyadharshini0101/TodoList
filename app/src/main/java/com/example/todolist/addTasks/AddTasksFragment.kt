package com.example.todolist.addTasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.database.TodoDatabase
import com.example.todolist.databinding.FragmentAddTasksBinding
import com.example.todolist.taskTrack.TaskTrackViewModel
import com.example.todolist.taskTrack.TaskTrackViewModelFactory

class AddTasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentAddTasksBinding  = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_tasks, container, false)

        val application= requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getInstance(application).todoDatabaseDao

        val viewModelFactory = AddTasksViewModelFactory(dataSource,application)

        val addTasksViewModel = ViewModelProvider(this, viewModelFactory).get(AddTasksViewModel::class.java)

        binding.addTasksViewModel=addTasksViewModel

        binding.lifecycleOwner = this

        addTasksViewModel.inserted.observe(viewLifecycleOwner,Observer{
            if(it){
                Toast.makeText(application,"Your task is successfully Added",Toast.LENGTH_LONG).show()
            }
        })
        return binding.root
    }


}