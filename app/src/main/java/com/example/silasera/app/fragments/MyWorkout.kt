package com.example.silasera.app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.size
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silasera.R
import com.example.silasera.adapters.MyWorkoutAdapter
import com.example.silasera.databinding.AppMyWorkoutBinding
import com.example.silasera.dataclass.MyClients
import com.example.silasera.dataclass.MyWorkoutData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log

class MyWorkout : Fragment() {

    private lateinit var binding: AppMyWorkoutBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var profileUid: String
    private lateinit var workout2: ArrayList<MyWorkoutData>
    private var clickedWorkout = 0
    private lateinit var gender: String
    private var workout = "Man"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AppMyWorkoutBinding.inflate(inflater, container, false)
        getUserUid()



        return binding.root
    }

    private fun getUserUid() {
        val getBundle = arguments
        val userUid = getBundle!!.getString("userUid").toString()
        profileUid = userUid
        getGender()

    }

    private fun getGender() {
        dbReference = FirebaseDatabase.getInstance().getReference("UserProfile")
        dbReference.child(profileUid).get().addOnSuccessListener {
            gender = it.child("userGender").value.toString()
            Log.i("gender", gender)
            setDatabaseCards()
        }


    }

//    .addOnFailureListener {
//        Toast.makeText(context, "Błąd wczytywania danych." +
//                " Być może nie masz połączenia z internetem.", Toast.LENGTH_SHORT).show()
//    }


    private fun setDatabaseCards() {
        // other workouts
        workout2 = arrayListOf()

        if (gender == "M"){
            workout = "Man"
        } else if (gender == "K"){
            workout = "Woman"
        }

        dbReference = FirebaseDatabase.getInstance().getReference("Workout").child("$workout")
            .child("Workout")
        dbReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val names = snapshot.children.toMutableList()
                for(el in names){
                    workout2.add(MyWorkoutData(el.child("exName").value.toString(), el.child("exImg").value.toString()))
                }

                setWorkoutCards(createWorkoutCards(workout2))
            }




            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

    private fun createWorkoutCards(workout2: ArrayList<MyWorkoutData>): List<MyWorkoutData> {
        val workoutCards = ArrayList<MyWorkoutData>()
        for (el in workout2){
            workoutCards.add(el)
        }
        return  workoutCards
    }



    private fun setWorkoutCards(workoutCards: List<MyWorkoutData>) {
        val recyclerView = binding.appWorkoutRV
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MyWorkoutAdapter(workoutCards){
            showWorkout()
            }

        }

    private fun showWorkout() {

        val workoutFragment = MyWorkout2()
//        val bundle = Bundle()
//        bundle.putString("workoutName", workoutName)
//        bundle.putString("userGender", gender)
//        workoutFragment.arguments = bundle
        val transaction: FragmentTransaction =  requireFragmentManager().beginTransaction()
        transaction.replace(R.id.app_frame_layout, workoutFragment)
        transaction.commit()

    }


}