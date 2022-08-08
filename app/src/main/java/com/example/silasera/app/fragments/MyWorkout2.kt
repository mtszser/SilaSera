package com.example.silasera.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silasera.R
import com.example.silasera.adapters.MyWorkout2Adapter
import com.example.silasera.databinding.AppMyWorkout2Binding
import com.example.silasera.dataclass.MyWorkout2Data
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MyWorkout2 : Fragment() {

    private lateinit var binding: AppMyWorkout2Binding
    private lateinit var dbReference: DatabaseReference
    private lateinit var profileUid: String
    private lateinit var gender: String
    private lateinit var exerciseList: ArrayList<MyWorkout2Data>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = AppMyWorkout2Binding.inflate(inflater, container, false)
        getData()
        setExerciseCardsRV(createCards())


        return binding.root
    }

    private fun setExerciseCardsRV(createCards: List<MyWorkout2Data>) {
        val recyclerView = binding.appWorkout2RV
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MyWorkout2Adapter(createCards){
            Toast.makeText(context, "Nie wstawiono, żadnego filmiku.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createCards(): List<MyWorkout2Data> {

        exerciseList = arrayListOf()
        exerciseList.add(MyWorkout2Data("Barbell Bench Press", "4 serie", "8-10 powtórzeń", " tempo: 2001", "przerwa: 2.5-3 min"))
        exerciseList.add(MyWorkout2Data("Lat Pulldowns", "4 serie", "10-12 powtórzeń", "tempo: 2001", "przerwa: 2 min"))
        exerciseList.add(MyWorkout2Data("Bodyweight Lunges", "3 serie", "20-24 powtórzeń", "tempo: 1001", "przerwa: 1.5-2 min"))
        exerciseList.add(MyWorkout2Data("Leg Curls", "3 serie", "12 powtórzeń", "tempo: 2001", "przerwa: 2 min"))
        exerciseList.add(MyWorkout2Data("DB Biceps Curls", "3 serie", "10-12 powtórzeń", "tempo: 2001", "przerwa: 2 min"))
        exerciseList.add(MyWorkout2Data("Cable Triceps Extension", "3 serie", "12 powtórzeń", "tempo: 2001", "przerwa: 2 min"))
        exerciseList.add(MyWorkout2Data("Plank", "3 serie", "12 powtórzeń", "tempo: 2001", "przerwa: 2-3 min"))

        return exerciseList
    }


    private fun getData() {
        val getBundle = arguments
        profileUid = getBundle!!.getString("profileUid").toString()
        gender = getBundle.getString("userGender").toString()
        val workoutName = getBundle.getString("workoutName").toString()
        showWorkout(workoutName)
    }

    private fun showWorkout(workoutName: String) {

    }

}