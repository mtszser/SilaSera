package com.example.silasera.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silasera.R
import com.example.silasera.databinding.WeightPlannerBinding

class WeightPlanner : AppCompatActivity() {

    private lateinit var binding: WeightPlannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeightPlannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightLeftArrow.setOnClickListener{
            finish()
        }

    }

    override fun onBackPressed() {

    }
}