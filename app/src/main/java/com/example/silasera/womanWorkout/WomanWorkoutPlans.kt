package com.example.silasera.womanWorkout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silasera.R
import com.example.silasera.adapters.UserAdapter
import com.example.silasera.dataclass.User
import com.google.firebase.database.*


class WomanWorkoutPlans : Fragment() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val wMP = inflater.inflate(R.layout.woman_workout_plans, container, false)

        userRecyclerView = wMP.findViewById(R.id.woman_RV)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)

        userList = arrayListOf()
        getUserData()

        return wMP
    }

    private fun getUserData() {
        dbReference = FirebaseDatabase.getInstance().getReference("Users")
        dbReference.child("Users").child("Justyna").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
        dbReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(User::class.java)
                        userList.add(user!!)

                    }
                    userRecyclerView.adapter = UserAdapter(userList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}