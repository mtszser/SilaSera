package com.example.silasera.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.silasera.R
import java.math.RoundingMode
import kotlin.math.pow

class Bmi : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    val bmiFragment = inflater.inflate(R.layout.fragment_bmi, container, false)
    val yWeight = bmiFragment.findViewById<EditText>(R.id.bmi_weight)
    val yHeight = bmiFragment.findViewById<EditText>(R.id.bmi_height)
    val yourBMI = bmiFragment.findViewById<TextView>(R.id.bmi_your_bmi)
    val bmiSheet = bmiFragment.findViewById<TextView>(R.id.bmi_your_sheet)
    val bmiButton = bmiFragment.findViewById<Button>(R.id.bmi_button)

        bmiButton.setOnClickListener{
            if (yWeight.text.isNotEmpty() && yHeight.text.isNotEmpty()) {
                val weight = yWeight.text.toString().toDouble()
                val height = yHeight.text.toString().toDouble()

                if (weight < 0.0 || weight == 0.0 || height < 0.0 || height == 0.0)
                {
                    yourBMI.text = "Dane są nieprawidłowe."
                } else {

                    val result = weight / (height * height) * 10000
                    val rounded =
                        result.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toDouble()
                    yourBMI.text = "Twoje BMI wynosi: $rounded"
                    when {
                        rounded < 18.5 -> {
                            bmiSheet.setText(R.string.underweight)
                        }
                        rounded in 18.5..24.9 -> {
                            bmiSheet.setText(R.string.healthyweight)
                            bmiSheet.setTextColor(getResources().getColor(R.color.goodBMI))
                        }
                        rounded in 25.0..30.0 -> {
                            bmiSheet.setText(R.string.overweight)
                            bmiSheet.setTextColor(getResources().getColor(R.color.badBMI))
                        }
                        else -> {
                            bmiSheet.setText(R.string.obeseweight)
                        }
                    }
                }
            }

        }
        return bmiFragment
    }


}