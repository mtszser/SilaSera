package com.example.silasera.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.silasera.R
import com.example.silasera.databinding.AppMyBmiBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.math.RoundingMode


class MyBMI : Fragment() {

    private lateinit var binding: AppMyBmiBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var profileUid: String
    private lateinit var myBMIResultDesc: TextView
    private lateinit var myBMIPrevBMI: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AppMyBmiBinding.inflate(inflater, container, false)
        getValues()
        getPreviousBMI()
        return binding.root
    }

    private fun getPreviousBMI() {
        val getBundle = arguments
        val userUid = getBundle!!.getString("userUid").toString()
        profileUid = userUid
        dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
        dbReference.child("$userUid").get().addOnSuccessListener {
            myBMIPrevBMI = binding.myBMIPrevBMI
            val previousBMI = it.child("userBMI").value as Double
            if (previousBMI > 0.1) {
                myBMIPrevBMI.text = previousBMI.toString()
                when {
                    previousBMI < 18.5 -> {
                        myBMIPrevBMI.text = previousBMI.toString()
                    }
                    previousBMI in 18.5..24.9 -> {
                        myBMIPrevBMI.text = previousBMI.toString()
                        myBMIPrevBMI.setTextColor(resources.getColor(R.color.goodBMI))
                    }
                    previousBMI in 25.0..30.0 -> {
                        myBMIPrevBMI.text = previousBMI.toString()
                        myBMIPrevBMI.setTextColor(resources.getColor(R.color.badBMI))
                    }
                    else -> {
                        myBMIPrevBMI.text = previousBMI.toString()
                    }
                }
            } else {
                myBMIPrevBMI.text = "Brak danych o BMI, zapisz je by wyświetlić je później."
                myBMIPrevBMI.setTextColor(resources.getColor(R.color.badBMI))
            }

        }
    }

    private fun getValues() {

        binding.myBMIButtonCount.setOnClickListener {
            myBMIResultDesc = binding.myBMIResultDesc
            val heightText = binding.myBMIHeight.text.toString()
            val weightText = binding.myBmiWeight.text.toString()
            if (heightText.isNotEmpty() && weightText.isNotEmpty()) {
                val weight = weightText.toDouble()
                val height = heightText.toDouble()

                if (weight < 0.0 || weight == 0.0 || weight < 40 || weight > 150
                    || height < 0.0 || height == 0.0 || height > 250 || height < 130) {
                    binding.myBMIResult.text = "Dane są nieprawidłowe."
                    binding.myBMIResultDesc.text = ""
                } else {
                    val userHeight = binding.myBMIHeight.text.toString().toDouble()
                    val userWeight = binding.myBmiWeight.text.toString().toDouble()
                    var bmiResult = userWeight / (userHeight * userHeight) * 10000
                    val rounded =
                        bmiResult.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN).toDouble()
                    saveBMI(rounded)
                    binding.myBMIResult.text = "Twoje BMI wynosi: $rounded"
                    when {
                        rounded < 18.5 -> {
                            myBMIResultDesc.setText(R.string.underweight)
                        }
                        rounded in 18.5..24.9 -> {
                            myBMIResultDesc.setText(R.string.healthyweight)
                            myBMIResultDesc.setTextColor(resources.getColor(R.color.goodBMI))
                        }
                        rounded in 25.0..30.0 -> {
                            myBMIResultDesc.setText(R.string.overweight)
                            myBMIResultDesc.setTextColor(resources.getColor(R.color.badBMI))
                        }
                        else -> {
                           myBMIResultDesc.setText(R.string.obeseweight)
                        }
                    }
                }
            }
        }
    }

    private fun saveBMI(userBMI: Double) {
        binding.myBMIButtonSave.setOnClickListener {
            if (userBMI == 0.0) {
                myBMIResultDesc.setText("Żeby zapisać BMI, wpierw trzeba je policzyć.")
                myBMIResultDesc.setTextColor(resources.getColor(R.color.badBMI))
            } else {
                dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
                dbReference.child("$profileUid").child("userBMI").setValue(userBMI)
                myBMIResultDesc.setText("BMI zostało zapisane! ")
                myBMIResultDesc.setTextColor(resources.getColor(R.color.black))
            }
        }
    }
}