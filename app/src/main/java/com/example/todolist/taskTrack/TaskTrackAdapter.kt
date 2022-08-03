package com.example.todolist.taskTrack

import android.transition.Visibility
import com.example.todolist.database.TodoList
import com.example.todolist.databinding.ListItemsBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskTrackAdapter(val clickListener1:TaskClickListener,val clickListener2:AddProgressListener,val clickListener3:MinusProgressListener,val clickListener4:DeleteTaskListener): ListAdapter<TodoList, TaskTrackAdapter.ViewHolder>(TaskTrackDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener1,clickListener2,clickListener3,clickListener4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoList,clickListener1: TaskClickListener,clickListener2: AddProgressListener,clickListener3: MinusProgressListener,clickListener4: DeleteTaskListener) {
            binding.data=item
            binding.taskClicKListener=clickListener1
            binding.addProgressListener=clickListener2
            binding.minusProgressListener=clickListener3
            binding.deleteTaskListener=clickListener4
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

class TaskClickListener(val clickListener: (view: ViewGroup,view1:ViewGroup) -> Unit){
    fun onClick(view: ViewGroup,view1: ViewGroup)=clickListener(view,view1)
}

class AddProgressListener(val clickListener: (todoList:TodoList) -> Unit){
    fun onClick(todo: TodoList)=clickListener(todo)
}

class MinusProgressListener(val clickListener: (todoList:TodoList) -> Unit){
    fun onClick(todo: TodoList)=clickListener(todo)
}

class DeleteTaskListener(val clickListener: (todoList:TodoList) -> Unit){
    fun onClick(todo: TodoList)=clickListener(todo)
}
class TaskTrackDiffCallback : DiffUtil.ItemCallback<TodoList>() {
    override fun areItemsTheSame(oldItem: TodoList, newItem: TodoList): Boolean {
        return oldItem.todoId == newItem.todoId
    }

    override fun areContentsTheSame(oldItem: TodoList, newItem: TodoList): Boolean {
        return oldItem== newItem
    }
}
