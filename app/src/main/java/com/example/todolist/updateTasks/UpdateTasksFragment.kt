package com.example.todolist.updateTasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.todolist.R
import com.example.todolist.addTasks.AddTasksViewModel
import com.example.todolist.addTasks.AddTasksViewModelFactory
import com.example.todolist.database.TodoDatabase
import com.example.todolist.database.TodoList
import com.example.todolist.databinding.FragmentAddTasksBinding
import com.example.todolist.databinding.FragmentUpdateTasksBinding

class UpdateTasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentUpdateTasksBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_update_tasks, container, false)

        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.update_tasks)

        val args1: UpdateTasksFragmentArgs by navArgs()

        val application= requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getInstance(application).todoDatabaseDao

        val viewModelFactory = UpdateTasksViewModelFactory(dataSource,application,args1.todoId.toLong())

        val updateTasksViewModel = ViewModelProvider(this, viewModelFactory).get(UpdateTasksViewModel::class.java)

        binding.updateTasksViewModel=updateTasksViewModel
        binding.lifecycleOwner = this

        updateTasksViewModel.deleteTodoId.observe(viewLifecycleOwner, Observer {
            if(it==true){
                Toast.makeText(application,"The task is delete successfully",Toast.LENGTH_LONG).show()
                updateTasksViewModel.doneDelete()
            }
        })

        updateTasksViewModel.updateTodoId.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                Toast.makeText(application,it.toString(),Toast.LENGTH_LONG).show()
                updateTasksViewModel.doneUpdate()
            }
        })

        return binding.root
    }
}