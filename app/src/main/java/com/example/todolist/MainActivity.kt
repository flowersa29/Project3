package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



     lateinit var todoAdapter: TodoAdapter
     lateinit var todoList: ArrayList<Todo>
     lateinit var todo: TodoAdapter
     var positionEditing = -1






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoList = ArrayList()

       var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
           result ->
           run {
               val data: Intent? = result.data
               val newText = data?.getStringExtra("newEditedText")
               Log.i(
                   "Aaron",
                   "data passed from newEditActivity: " + data?.getStringExtra("newlyEditedText")
               )

               if (newText != null) {
                   todoList[positionEditing] = Todo(newText, false)
                   todoAdapter.notifyDataSetChanged()
               }
           }
       }


        todoAdapter = TodoAdapter(mutableListOf(),
            object : TodoAdapter.MyOnClickListener {
            override fun onItemClicked(position: Int) {
                Log.i("Aaron", "Clicked on item in list: $position" )

            positionEditing = position
                Log.i("MainActivity", position.toString())
            val intent = Intent(this@MainActivity, NewEditActivity::class.java)
                 intent.putExtra("string", todoAdapter.getItemViewType(position).toString() )
            resultLauncher.launch(intent)

            }

        } )


        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)



        btnAddTodo.setOnClickListener {
            val todoTitle = editTextTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                editTextTodoTitle.text.clear()
            }
        }
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }





    }

