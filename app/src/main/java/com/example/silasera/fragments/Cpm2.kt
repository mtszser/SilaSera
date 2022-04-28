package com.example.silasera.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.silasera.R


class Cpm2 : Fragment() {

    private var palNumber: Double = 1.2
    private var cpmResult: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_cpm2, container, false)

        //get Elements
        val ppm = v.findViewById<TextView>(R.id.cpm2_ppm)
        val palSpinner = v.findViewById<Spinner>(R.id.cpm2_pal)
        val cpmButton = v.findViewById<Button>(R.id.cpm2_button)
        val cpmResultView = v.findViewById<TextView>(R.id.cpm2_cpm_result)

        //fetch data
        val args = this.arguments
        val genderData = args?.getBoolean("gender")
        val ppmData = args?.getDouble("ppm")

        ppm.text = ppmData.toString()+ "kcal."

        //spinner data
        val palNumbers = resources.getStringArray(R.array.palNumbers)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_style, palNumbers)
        palSpinner.adapter = arrayAdapter
        palSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                Toast.makeText(context, "selected pal number is: " +palNumbers[position], Toast.LENGTH_SHORT).show()
                when(position) {
                    0 ->  palNumber = 1.2
                    1 -> palNumber = 1.4
                    2 -> palNumber = 1.6
                    3 -> palNumber = 1.8
                    4 -> palNumber = 2.0
                    5 -> palNumber = 2.4
                }
                Log.d("palnumber", "$palNumber")
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                palNumber = 1.2
            }

        }

        cpmButton.setOnClickListener{

            if (ppmData != null) {
                cpmResult = ppmData * palNumber
                cpmResultView.text = "Twoje CPM wynosi: " + cpmResult.toString() + "kcal"
            } else{
                Log.d("error", "there is no ppmData - $ppmData")
            }



        }

        return v
    }
}