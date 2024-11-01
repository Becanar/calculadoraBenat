package com.example.calculadora_benat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora_benat.databinding.ActivityMainBinding

/**
 * MainActivity es la actividad principal de la calculadora que permite realizar operaciones básicas
 * como suma, resta, multiplicación y división. Implementa la interfaz View.OnClickListener para gestionar
 * los eventos de clic en los botones.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    /** Primer número en la operación. */
    private var primerNumero = 0.0

    /** Segundo número en la operación. */
    private var segundoNumero = 0.0

    /** Operación a realizar (+, -, *, /). */
    private var operacion: String? = null

    /** Enlace de la vista con los elementos definidos en el layout. */
    private lateinit var binding: ActivityMainBinding

    /**
     * Método onCreate que se llama al iniciar la actividad. Configura la vista
     * y asigna listeners a los botones de la calculadora.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        operacion = null

        // Asignación de listeners para los botones de números y operadores.
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

    /**
     * Método onClick que gestiona los clics en los botones y llama a los métodos correspondientes
     * según el botón presionado.
     *
     * @param view Vista del botón que se ha presionado.
     */
    override fun onClick(view: View?) {
        when (view) {
            binding.bt0 -> { onNumberPressed("0") }
            binding.bt1 -> { onNumberPressed("1") }
            binding.bt2 -> { onNumberPressed("2") }
            binding.bt3 -> { onNumberPressed("3") }
            binding.bt4 -> { onNumberPressed("4") }
            binding.bt5 -> { onNumberPressed("5") }
            binding.bt6 -> { onNumberPressed("6") }
            binding.bt7 -> { onNumberPressed("7") }
            binding.bt8 -> { onNumberPressed("8") }
            binding.bt9 -> { onNumberPressed("9") }
            binding.btComa -> { onNumberPressed(".") }
            binding.btPlus -> { onOperatorPressed("+") }
            binding.btMinus -> { onOperatorPressed("-") }
            binding.btMult -> { onOperatorPressed("*") }
            binding.btDiv -> { onOperatorPressed("/") }
            binding.btClear -> { onClearPressed() }
            binding.btEqual -> { onEqualPressed() }
        }
    }

    /**
     * Método que procesa la entrada de un número y actualiza la pantalla.
     *
     * @param numero Cadena que representa el número o punto decimal ingresado.
     */
    private fun onNumberPressed(numero: String) {
        renderScreen(numero)
        checkOperation()
    }

    /**
     * Actualiza la pantalla de la calculadora con el número ingresado.
     *
     * @param numero Número o símbolo de punto decimal a añadir a la pantalla.
     */
    private fun renderScreen(numero: String) {
        val result: String = if (binding.screen.text == "0" && numero != ",") numero
        else "${binding.screen.text}$numero"

        binding.screen.text = result
    }

    /**
     * Verifica y actualiza los valores de los números para la operación actual.
     * Si no se ha seleccionado una operación, actualiza el primer número.
     * Si se ha seleccionado una operación, actualiza el segundo número.
     */
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

    /**
     * Procesa la selección de un operador y prepara la pantalla para el siguiente número.
     *
     * @param operacion Cadena que representa la operación seleccionada (+, -, *, /).
     */
    private fun onOperatorPressed(operacion: String) {
        this.operacion = operacion
        primerNumero = binding.screen.text.toString().toDouble()
        binding.screen.text = ""
    }

    /**
     * Realiza el cálculo basado en los valores de primerNumero, segundoNumero y operacion,
     * y muestra el resultado en la pantalla.
     */
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

    /**
     * Limpia la pantalla y restablece todos los valores a cero.
     */
    private fun onClearPressed() {
        binding.screen.text = "0"
        primerNumero = 0.0
        segundoNumero = 0.0
    }
}
