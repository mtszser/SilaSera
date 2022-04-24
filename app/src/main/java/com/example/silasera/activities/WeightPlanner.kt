package com.example.silasera.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.silasera.R
import com.example.silasera.databinding.FragmentBmiBinding
import com.example.silasera.databinding.WeightPlannerBinding

class WeightPlanner : AppCompatActivity() {

    private lateinit var binding: WeightPlannerBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeightPlannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_cont)
        setupWithNavController(binding.bottomNavView, navController)

//        val fragmentBmi = Fragment(R.layout.fragment_bmi)


    }

    override fun onBackPressed() {
        finish()

    }
}