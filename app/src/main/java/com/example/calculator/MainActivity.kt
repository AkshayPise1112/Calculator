package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

//   val tvInput = findViewById<TextView>(R.id.tvInput)

    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onDigit(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun removeAfterDot(value: String) : String{
        var result = value
        if(result.contains(".0")){
            result = result.substring(0, result.length - 2)
        }
        return result
    }

    fun onEqual(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric){

            var tvValue = tvInput.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-";
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeAfterDot((one.toDouble() + two.toDouble()).toString())
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeAfterDot((one.toDouble() * two.toDouble()).toString())
                }

                if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("+") || value.contains("*") || value.contains("-")
        }

    }
}