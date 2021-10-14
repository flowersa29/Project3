package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewEditActivity : AppCompatActivity() {

    companion object {
        val EDIT_TEXT = "edit_text"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newedit)

        val dataPassedToUs = intent.getStringExtra("string").toString()
        Log.i("Aaron", "data:$dataPassedToUs")

        findViewById<EditText>(R.id.newEditText).setText(dataPassedToUs)

        findViewById<Button>(R.id.editButton).setOnClickListener {
            val newText = findViewById<EditText>(R.id.newEditText).text.toString()
            val newIntent = Intent()

            newIntent.putExtra("newlyEditedText", newText)
            setResult(RESULT_OK, newIntent)

            finish()
        }

    }
}