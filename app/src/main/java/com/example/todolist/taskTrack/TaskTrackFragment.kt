package com.example.todolist.taskTrack


import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.TodoDatabase
import com.example.todolist.databinding.FragmentTaskTrackBinding


class TaskTrackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding:FragmentTaskTrackBinding =DataBindingUtil.inflate(inflater, R.layout.fragment_task_track, container, false)

        val application= requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getInstance(application).todoDatabaseDao

        val viewModelFactory = TaskTrackViewModelFactory(dataSource,application)

        val taskTrackViewModel = ViewModelProvider(this, viewModelFactory).get(TaskTrackViewModel::class.java)

        binding.taskTrackViewModel=taskTrackViewModel

        binding.lifecycleOwner = this
        val adapter=TaskTrackAdapter(TaskClickListener{ id->
//                       if(id.visibility== View.VISIBLE ){
//                           TransitionManager.beginDelayedTransition(id1, AutoTransition())
//                           id.setVisibility(View.GONE)
//                       }else{
//                           TransitionManager.beginDelayedTransition(id1, AutoTransition())
//                           id.setVisibility(View.VISIBLE)
//                       }
        },
        AddProgressListener{ initialValue ->

        },
        MinusProgressListener {initialValue ->

        },
        DeleteTaskListener {id->

        })

        binding.recyclerView.adapter=adapter

        taskTrackViewModel.todo.observe(viewLifecycleOwner, Observer {
            Log.d("Size",it.size.toString())
            it?.let {
                adapter.submitList(it)
               adapter.notifyDataSetChanged()
            }
        })


        taskTrackViewModel.navigateToAddTasks.observe(viewLifecycleOwner, Observer{
            if(it==true){
             this.findNavController().navigate(TaskTrackFragmentDirections.actionTaskTrackFragmentToAddTasksFragment())
                taskTrackViewModel.doneNavigateToAddTasks()
            }
        })

        return binding.root
    }

}