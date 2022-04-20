package com.example.silasera.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.silasera.R
import com.example.silasera.activities.WeightPlanner
import com.example.silasera.databinding.FragmentBmiBinding
import com.example.silasera.databinding.WeightPlannerBinding
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt

class Bmi : Fragment() {

//    private lateinit var bmiButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    val bmiFragment = inflater.inflate(R.layout.fragment_bmi, container, false)
    val bmiMan = bmiFragment.findViewById<ImageView>(R.id.man_bmi_button)
    val bmiWoman = bmiFragment.findViewById<ImageView>(R.id.woman_bmi_button)
    val yWeight = bmiFragment.findViewById<EditText>(R.id.bmi_weight)
    val yHeight = bmiFragment.findViewById<EditText>(R.id.bmi_height)
//        var bmiBool = false
    val bmiButton = bmiFragment.findViewById<Button>(R.id.bmi_button)

//
//            bmiMan.setOnClickListener {
//                bmiBool = false
//            }
//            bmiWoman.setOnClickListener{
//                bmiBool = true
//            }

//            if (!bmiBool) {
//                bmiMan.setImageResource(R.drawable.man_focus)
//            } else if(bmiBool) {
//                bmiWoman.setImageResource(R.drawable.woman_focus)
//            }
//
        bmiButton.setOnClickListener{
            if (yWeight.text.isNotEmpty() && yHeight.text.isNotEmpty()) {
                val weight = yWeight.text.toString().toDouble()
                val height = yHeight.text.toString().toDouble()

                val result = weight / (height.pow(2)) * 10000
                val rounded = result.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toDouble()


                Toast.makeText(context, "Twoje BMI wynosi: $rounded", Toast.LENGTH_SHORT).show()
            }

        }
        return bmiFragment
    }


}