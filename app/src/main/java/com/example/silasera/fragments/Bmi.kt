package com.example.silasera.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.silasera.R
import com.example.silasera.activities.WeightPlanner
import com.example.silasera.databinding.FragmentBmiBinding
import com.example.silasera.databinding.WeightPlannerBinding

class Bmi : Fragment() {

    private var gender = arrayListOf("Male", "Female")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bmiFragment = inflater.inflate(R.layout.fragment_bmi, container, false)

        val activity = activity as Context

        val spinner = bmiFragment.findViewById<Spinner>(R.id.spinnerBMI)
        val arrayAdapter = ArrayAdapter<String>(activity, R.layout.support_simple_spinner_dropdown_item, gender, )

        spinner.adapter = arrayAdapter
        return bmiFragment

    }



}