package com.example.silasera.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silasera.adapters.MyWorkoutAdapter
import com.example.silasera.databinding.AppMyWorkoutBinding
import com.example.silasera.dataclass.MyWorkoutData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MyWorkout : Fragment() {

    private lateinit var binding: AppMyWorkoutBinding
    private lateinit var dbReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AppMyWorkoutBinding.inflate(inflater, container, false)
        setDatabaseCards()


        return binding.root
    }

    private fun setDatabaseCards() {
        dbReference = FirebaseDatabase.getInstance().getReference("Workout").child("Man")
        dbReference.get().addOnSuccessListener {
            val exName = it.child("Upper").child("exName").value.toString()
            val exImage = it.child("Upper").child("exImage").value.toString()
            setWorkoutCards(createWorkoutCards(exName, exImage))
        }

    }

    private fun setWorkoutCards(workoutCards: List<MyWorkoutData>) {
        val recyclerView = binding.appWorkoutRV
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MyWorkoutAdapter(workoutCards){

        }

    }

    private fun createWorkoutCards(exName: String, exImage: String): List<MyWorkoutData> {
        val workoutCards = ArrayList<MyWorkoutData>()
        workoutCards.add(MyWorkoutData(exName, exImage))

        return workoutCards

    }
}