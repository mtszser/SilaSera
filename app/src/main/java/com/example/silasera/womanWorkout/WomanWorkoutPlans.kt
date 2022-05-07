package com.example.silasera.womanWorkout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silasera.R
import com.example.silasera.adapters.GuestWorkoutAdapter
import com.example.silasera.adapters.UserAdapter
import com.example.silasera.dataclass.GuestWorkout
import com.example.silasera.dataclass.User
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.firebase.database.*


class WomanWorkoutPlans : Fragment(), YouTubePlayer.Provider {

    private lateinit var dbReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var guestWorkoutList: ArrayList<GuestWorkout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val wMP = inflater.inflate(R.layout.woman_workout_plans, container, false)



        userRecyclerView = wMP.findViewById(R.id.woman_RV)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)

        guestWorkoutList = arrayListOf()
        getUserData()

        return wMP
    }


    private fun getUserData() {
        dbReference = FirebaseDatabase.getInstance().getReference("GuestWW")
        dbReference.child("Upper").child("Upper").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
        dbReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){

                        val guestWorkout = userSnapshot.getValue(GuestWorkout::class.java)
                        guestWorkoutList.add(guestWorkout!!)

                    }
                    userRecyclerView.adapter = GuestWorkoutAdapter(guestWorkoutList){
                        when(it.wname){
                            "Cardio" -> {
                                val cardioFragment = WomanWorkoutCardio()
                                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                                transaction.replace(R.id.nav_host_fragment_womanWorkout, cardioFragment)
                                transaction.commit()
                            }
                            "FBW" -> {
                                val fbwFragment = WomanWorkoutFbw()
                                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                                transaction.replace(R.id.nav_host_fragment_womanWorkout, fbwFragment)
                                transaction.commit()
                            }
                            "Lower" -> {
                                val lowerFragment = WomanWorkoutLower()
                                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                                transaction.replace(R.id.nav_host_fragment_womanWorkout, lowerFragment)
                                transaction.commit()
                            }
                            "Upper" -> {
                                val upperFragment = WomanWorkoutUpper()
                                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                                transaction.replace(R.id.nav_host_fragment_womanWorkout, upperFragment)
                                transaction.commit()
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun initialize(p0: String?, p1: YouTubePlayer.OnInitializedListener?) {
        TODO("Not yet implemented")
    }

}