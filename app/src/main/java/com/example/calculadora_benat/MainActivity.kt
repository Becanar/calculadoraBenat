package com.example.calculadora_benat

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation: String? = null

    private lateinit var bt0: Button
    private lateinit var bt1: Button
    private lateinit var bt2: Button
    private lateinit var bt3: Button
    private lateinit var bt4: Button
    private lateinit var bt5: Button
    private lateinit var bt6: Button
    private lateinit var bt7: Button
    private lateinit var bt8: Button
    private lateinit var bt9: Button
    private lateinit var btComa: Button
    private lateinit var btPlus: Button
    private lateinit var btMinus: Button
    private lateinit var btMult: Button
    private lateinit var btDiv: Button
    private lateinit var btEqual: Button
    private lateinit var btClear: Button
    private lateinit var screen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt0 = findViewById(R.id.bt0)
        bt1 = findViewById(R.id.bt1)
        bt2 = findViewById(R.id.bt2)
        bt3 = findViewById(R.id.bt3)
        bt4 = findViewById(R.id.bt4)
        bt5 = findViewById(R.id.bt5)
        bt6 = findViewById(R.id.bt6)
        bt7 = findViewById(R.id.bt7)
        bt8 = findViewById(R.id.bt8)
        bt9 = findViewById(R.id.bt9)
        btComa = findViewById(R.id.btComa)
        btPlus = findViewById(R.id.btPlus)
        btMinus = findViewById(R.id.btMinus)
        btMult = findViewById(R.id.btMult)
        btDiv = findViewById(R.id.btDiv)
        btEqual = findViewById(R.id.btEqual)
        btClear = findViewById(R.id.btClear)
        screen = findViewById(R.id.screen)

        operation = null

        bt0.setOnClickListener(this)
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
        bt6.setOnClickListener(this)
        bt7.setOnClickListener(this)
        bt8.setOnClickListener(this)
        bt9.setOnClickListener(this)
        btComa.setOnClickListener(this)
        btPlus.setOnClickListener(this)
        btMinus.setOnClickListener(this)
        btMult.setOnClickListener(this)
        btDiv.setOnClickListener(this)
        btEqual.setOnClickListener(this)
        btClear.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            bt0 -> onNumberPressed("0")
            bt1 -> onNumberPressed("1")
            bt2 -> onNumberPressed("2")
            bt3 -> onNumberPressed("3")
            bt4 -> onNumberPressed("4")
            bt5 -> onNumberPressed("5")
            bt6 -> onNumberPressed("6")
            bt7 -> onNumberPressed("7")
            bt8 -> onNumberPressed("8")
            bt9 -> onNumberPressed("9")
            btComa -> onNumberPressed(",")
            btPlus -> ""
            btMinus -> ""
            btMult -> ""
            btDiv -> ""
            btEqual -> ""
            btClear -> ""
        }
    }

    private fun onNumberPressed(number: String) {
        renderScreen(number)
    }

    private fun renderScreen(number: String) {
        val result: String = if (screen.text == "0" && number != ",") number else "${screen.text}$number"
        screen.text = result
    }
}
