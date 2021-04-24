package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

/**
 * Main Activity for calculating the tip
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    /**
     * Calculates the tip of the bill
     */
    private fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        // Ensuring the field is not empty
        if (cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }

        // Handling the selected radio button
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // Calculating the tip
        var tip = tipPercentage * cost
        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        // Formatting the tip to the users local currency
        displayTip(tip)

    }

    /**
     * Displays the tip in the local currency
     */
    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}