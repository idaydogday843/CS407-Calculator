package com.cs407.calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val num1 =  findViewById<EditText>(R.id.textField1)
        val num2 = findViewById<EditText>(R.id.textField2)
        val button1 = findViewById<Button>(R.id.addButton)
        val button2 = findViewById<Button>(R.id.minusButton)
        val button3 = findViewById<Button>(R.id.timeButton)
        val button4 = findViewById<Button>(R.id.divideButton)
        val userInput1 = num1.text.toString()
        val userInput2 = num2.text.toString()
        button1.setOnClickListener {
            calculate(num1, num2, "add")
        }
        button2.setOnClickListener {
            calculate(num1, num2, "minus")
        }
        button3.setOnClickListener {
            calculate(num1, num2, "time")
        }
        button4.setOnClickListener {
            calculate(num1, num2, "divide")
        }



    }

    private fun calculate(text1: EditText, text2: EditText, operation: String) {
        val num1 = text1.text.toString().toIntOrNull()
        val num2 = text2.text.toString().toIntOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        if (operation == "divide" && num2 == 0) {
            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "add" -> num1 + num2
            "minus" -> num1 - num2
            "time" -> num1 * num2
            "divide" -> num1 / num2
            else -> 0
        }

        // Create an Intent to pass the result to the second activity
        val intent = Intent(this, ResultTab::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }
}