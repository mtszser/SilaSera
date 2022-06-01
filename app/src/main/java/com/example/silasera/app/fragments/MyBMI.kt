package com.example.silasera.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.silasera.R
import com.example.silasera.databinding.AppMyBmiBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.math.RoundingMode


class MyBMI : Fragment() {

    private lateinit var binding: AppMyBmiBinding
    private lateinit var dbReference: DatabaseReference

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
        dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
        dbReference.child(userUid)
    }

    private fun getValues() {

        binding.myBMIButtonCount.setOnClickListener {
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
                    binding.myBMIResult.text = "Twoje BMI wynosi: $rounded"
                    when {
                        rounded < 18.5 -> {
                            binding.myBMIResultDesc.setText(R.string.underweight)
                        }
                        rounded in 18.5..24.9 -> {
                            binding.myBMIResultDesc.setText(R.string.healthyweight)
                            binding.myBMIResultDesc.setTextColor(resources.getColor(R.color.goodBMI))
                        }
                        rounded in 25.0..30.0 -> {
                            binding.myBMIResultDesc.setText(R.string.overweight)
                            binding.myBMIResultDesc.setTextColor(resources.getColor(R.color.badBMI))
                        }
                        else -> {
                            binding.myBMIResultDesc.setText(R.string.obeseweight)
                        }
                    }
                }
            }
        }
    }


}