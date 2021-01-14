package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {

        //get the cost of service, it is an *editable* then we need *toString*
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost==null || cost==0.0) {
            displayTip(0.0)
            return
        }
        //get the tip selected from the radio button

        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.0
        }
        // calculate the tip
        var tip = tipPercentage * cost

         // Round the tip y/n?
        if ( binding.roundUpSwitch.isChecked) {
             tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }

    private fun displayTip(tip : Double){
        // Pick the NumberFormat from java.text
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}