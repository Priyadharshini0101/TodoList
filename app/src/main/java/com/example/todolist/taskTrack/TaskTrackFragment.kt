package com.example.todolist.taskTrack

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.databinding.FragmentTaskTrackBinding

class TaskTrackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentTaskTrackBinding =DataBindingUtil.inflate(inflater,R.layout.fragment_task_track, container, false)
        val application= requireNotNull(this.activity).application

        val viewModelFactory = TaskTrackViewModelFactory(application)

        val taskTrackViewModel =
            ViewModelProvider(this, viewModelFactory).get(TaskTrackViewModel::class.java)
        binding.taskTrackViewModel=taskTrackViewModel

        binding.lifecycleOwner = this

        taskTrackViewModel.navigateToAddTasks.observe(viewLifecycleOwner, Observer{ it ->
            if(it){
             this.findNavController().navigate(TaskTrackFragmentDirections.actionTaskTrackFragmentToAddTasksFragment())
                taskTrackViewModel.doneNavigateToAddTasks()
            }
        })

        return binding.root
    }

}