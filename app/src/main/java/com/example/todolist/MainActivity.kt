package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    private lateinit var todoAdapter: TodoAdapter


//        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val recyclerView = findViewById<RecyclerView>(R.id.rvTodoItems)
//
//        var task: MutableList<String>
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = TodoAdapter(mutableList)
        val editActivity = EditActivity()

        todoAdapter = TodoAdapter(mutableListOf(), )


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

