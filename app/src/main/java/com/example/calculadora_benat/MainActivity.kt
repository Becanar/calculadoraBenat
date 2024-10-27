package com.example.calculadora_benat

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora_benat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var primerNumero = 0.0
    private var segundoNumero = 0.0
    private var operacion: String? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        operacion = null

        binding.bt0.setOnClickListener(this)
        binding.bt1.setOnClickListener(this)
        binding.bt2.setOnClickListener(this)
        binding.bt3.setOnClickListener(this)
        binding.bt4.setOnClickListener(this)
        binding.bt5.setOnClickListener(this)
        binding.bt6.setOnClickListener(this)
        binding.bt7.setOnClickListener(this)
        binding.bt8.setOnClickListener(this)
        binding.bt9.setOnClickListener(this)

        binding.btPlus.setOnClickListener(this)
        binding.btMinus.setOnClickListener(this)
        binding.btMult.setOnClickListener(this)
        binding.btDiv.setOnClickListener(this)
        binding.btComa.setOnClickListener(this)
        binding.btClear.setOnClickListener(this)
        binding.btEqual.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.bt0 -> {
                onNumberPressed("0")
            }
            binding.bt1 -> {
                onNumberPressed("1")
            }
            binding.bt2 -> {
                onNumberPressed("2")
            }
            binding.bt3 -> {
                onNumberPressed("3")
            }
            binding.bt4 -> {
                onNumberPressed("4")
            }
            binding.bt5 -> {
                onNumberPressed("5")
            }
            binding.bt6 -> {
                onNumberPressed("6")
            }
            binding.bt7 -> {
                onNumberPressed("7")
            }
            binding.bt8 -> {
                onNumberPressed("8")
            }
            binding.bt9 -> {
                onNumberPressed("9")
            }
            binding.btComa -> {
                onNumberPressed(".")
            }
            binding.btPlus -> {
                onOperatorPressed("+")
            }
            binding.btMinus -> {
                onOperatorPressed("-")
            }
            binding.btMult -> {
                onOperatorPressed("*")
            }
            binding.btDiv -> {
                onOperatorPressed("/")
            }
            binding.btClear -> {
                onClearPressed()
            }
            binding.btEqual -> {
                onEqualPressed()
            }
        }
    }

    private fun onNumberPressed(numero: String) {
        renderScreen(numero)
        checkOperation()
    }

    private fun renderScreen(numero: String) {
        val result: String = if (binding.screen.text == "0" && numero != ",")
            numero
        else
            "${binding.screen.text}$numero"

        binding.screen.text = result
    }

    private fun checkOperation() {
        try {
            if (operacion == null) {
                primerNumero = binding.screen.text.toString().toDouble()
            } else {
                segundoNumero = binding.screen.text.toString().toDouble()
            }
        } catch (e: NumberFormatException) {
            binding.screen.text = ""
            primerNumero = 0.0
            segundoNumero = 0.0
        }
    }


    private fun onOperatorPressed(operacion: String) {
        this.operacion = operacion
        primerNumero = binding.screen.text.toString().toDouble()
        binding.screen.text = ""
    }

    private fun onEqualPressed() {
        val result = when (operacion) {
            "+" -> primerNumero + segundoNumero
            "-" -> primerNumero - segundoNumero
            "*" -> primerNumero * segundoNumero
            "/" -> primerNumero / segundoNumero
            else -> 0
        }

        operacion = null
        primerNumero = result.toDouble()

        try {
            binding.screen.text = if (result.toString().endsWith(".0")) {
                result.toString().replace(".0", "")
            } else {
                "%.2f".format(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun onClearPressed() {
        binding.screen.text = "0"
        primerNumero = 0.0
        segundoNumero = 0.0
    }

}
