package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*



class TodoAdapter(private val todos: MutableList<Todo>, private val onClickListener: MyOnClickListener) : RecyclerView.Adapter<TodoAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val textview = itemView.findViewById<TextView>(R.id.tvTodoTitle)
//        init {
//            itemView.setOnClickListener {
//                println("printout to log")
//                val intent =Intent(itemView.context, EditActivity::class.java)
//                itemView.context.startActivity(intent)
//            }
//        }
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false )
        )
    }

    interface MyOnClickListener {
        fun onItemClicked(position: Int)
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curTodo = this.todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            CheckboxDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            CheckboxDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked

            }

        }

//        var resultLauncher = (holder.itemView.context as MainActivity)
//            .registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//                result -> if (result.resultCode == Activity.RESULT_OK) {
//                    val data: Intent? = result.data
//                    Log.i("Aaron", "data passed from newEditActivity:" + data?.getStringExtra("newlyEditedText"))
//                }
//            }


        //add onclickListener
        holder.itemView.setOnClickListener {
            onClickListener.onItemClicked(position)

        }

    }
//    class CustomViewHolder(private val view: View): RecyclerView.ViewHolder(view){
//        init {
//            view.setOnClickListener {
//                println("test")
//            }
//        }
//    }
    override fun getItemCount(): Int {
       return this.todos.size
    }


}
