package com.example.silasera.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.silasera.R

class Water : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val waterFragment = inflater.inflate(R.layout.fragment_water, container, false)

        val waterWeight = waterFragment.findViewById<EditText>(R.id.water_weight)
        val waterAge = waterFragment.findViewById<EditText>(R.id.water_age)
        val waterButton = waterFragment.findViewById<Button>(R.id.water_button)
        val waterResult = waterFragment.findViewById<TextView>(R.id.water_your_water_intake)

        waterButton.setOnClickListener{
            if (waterWeight.text.isNotEmpty() && waterAge.text.isNotEmpty())
            {
                val weight = waterWeight.text.toString().toDouble()
                val age = waterAge.text.toString().toDouble()
                var result: Double = 2000.0

                if (age <= 0.0 || age > 100 || weight <= 0.0 || weight >= 200.0) {
                    waterResult.text = "Dane są nieprawidłowe."
                } else {
                    when (age) {
                        in 0.1..17.9 -> {
                            waterResult.text = "Kalkulator przelicza zapotrzebowanie dla osób dorosłych."
                        } in 18.0..99.9 -> {
                            when (weight) {
                                in 0.1..40.0 -> {
                                    waterResult.text = "Twoja waga jest zbyt niska, sprawdź swoje BMI."
                                } in 40.1..49.9 -> {
                                    waterResult.text = "Twoje zapotrzebowanie na wodę wynosi minimum $result ml. " +
                                            "Co odpowiada około 8 szklankom wody."
                                } in 50.0..199.9 -> {
                                result = weight * 35
                                val waterGlass = result / 250
                                if (result < 2000.0 ){
                                    waterResult.text = "Twoje zapotrzebowanie na wodę wynosi minimum 2000 ml. " +
                                            "Co odpowiada 8 szklankom wody."
                                } else {
                                    waterResult.text = "Twoje zapotrzebowanie na wodę wynosi minimum $result ml " +
                                            "Co odpowiada około $waterGlass szklankom wody."
                                }
                                }
                            }
                        }
                    }
                }


//                when {
//                    weight <= 0.0 -> {
//                        waterResult.text = "Dane są nieprawidłowe"
//                    }
//                    weight > 200.0 -> {
//                        waterResult.text = "Skonsultuj się z lekarzem."
//                    }
//                    else -> {
//                        val result: Double
//                        val waterGlass: Double
//                        when (weight) {
//                            in 0.1..10.0 -> {
//                                result = weight * 100
//                                if (result <= 749.9) {
//                                    waterResult.text = "$result ml nie wystarczy do przeżycia! Pij minimum 750ml!"
//                                } else {
//                                    waterResult.text = "Norma spożycia wody dla nowonarodzonego dziecka wynosi 750ml!"
//                                }
//                            } in 10.1..20.0 -> {
//                            waterResult.text = "Twoje dzienne spożycie powinno wynosić minimum: 2000 ml." +
//                                    "Co przeklada się na około 8 szklanek wody."
//                            } in 20.1..199.9 -> {
//                            result = weight * 35
//                            waterGlass = result / 250
//                            if (result <= 2000.0){
//                            waterResult.text = "Twoje dzienne spożycie powinno wynosić minimum: 2000 ml." +
//                                    "Co przekłada się na około 8 szklanek wody."
//                            } else {
//                                waterResult.text = "Twoje dzienne spożycie wody powinno wynosić minimum: $result ml." +
//                                        "Co przekłada się na około $waterGlass szklanek wody."
//                            }
//                            }
//                        }
//                    }
//                }
            }
        }

        return waterFragment
    }


}