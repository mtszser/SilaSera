package com.example.silasera.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silasera.R
import com.example.silasera.adapters.UserAdapter
import com.example.silasera.databinding.GuestWomanWorkoutBinding
import com.example.silasera.dataclass.User
import com.google.android.youtube.player.*
import com.google.firebase.database.*

// żeby korzystać z google api dodaj " : YouTubeBaseActivity() zamiast AppCompatActivity()
class GuestWomanWorkout : AppCompatActivity() {

    private lateinit var binding: GuestWomanWorkoutBinding
    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GuestWomanWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_womanWorkout) as NavHostFragment
//        navController = navHostFragment.navController

    }



}