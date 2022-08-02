package com.example.todolist.taskTrack

import com.example.todolist.database.TodoList
import com.example.todolist.databinding.ListItemsBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskTrackAdapter: ListAdapter<TodoList, TaskTrackAdapter.ViewHolder>(TaskTrackDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoList) {
            binding.data=item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class TaskClickListener(val clickListener: (todoId:Long) -> Unit){
    fun onClick(model: TodoList)=clickListener(model.todoId)
}

class AddProgressListener(val clickListener: (todoId:Long) -> Unit){
    fun onClick(model: TodoList)=clickListener(model.todoId)
}

class TListener(val clickListener: (todoId:Long) -> Unit){
    fun onClick(model: TodoList)=clickListener(model.todoId)
}

class DeleteTaskListener(val clickListener: (todoId:Long) -> Unit){
    fun onClick(model: TodoList)=clickListener(model.todoId)
}
class TaskTrackDiffCallback : DiffUtil.ItemCallback<TodoList>() {
    override fun areItemsTheSame(oldItem: TodoList, newItem: TodoList): Boolean {
        return oldItem.todoId == newItem.todoId
    }

    override fun areContentsTheSame(oldItem: TodoList, newItem: TodoList): Boolean {
        return oldItem== newItem
    }
}
