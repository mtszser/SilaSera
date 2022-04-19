package com.example.silasera.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.silasera.R
import com.example.silasera.databinding.WeightPlannerBinding

class WeightPlanner : AppCompatActivity() {

    private lateinit var binding: WeightPlannerBinding
    private lateinit var NavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeightPlannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightLeftArrow.setOnClickListener{
            finish()
        }

        NavController = Navigation.findNavController(this, R.id.nav_host_fragment_cont)
        setupWithNavController(binding.bottomNavView, NavController)

    }

    override fun onBackPressed() {

    }
}