package com.example.silasera.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
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

//    private lateinit var dbReference: DatabaseReference
//    private lateinit var userRecyclerView: RecyclerView
//    private lateinit var userList: ArrayList<User>
    private lateinit var binding: GuestWomanWorkoutBinding
//    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GuestWomanWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        userRecyclerView = findViewById(R.id.woman_RV)
//        userRecyclerView.layoutManager = LinearLayoutManager(this)
//        userRecyclerView.setHasFixedSize(true)

//        userList = arrayListOf()
//        getUserData()
//        getYouTubeData()

    }

    override fun onBackPressed() {
      finish()
    }

//    private fun getYouTubeData() {
//        val ytView = findViewById<YouTubePlayerView>(R.id.cardio_yt_player)
//        ytView.initialize(R.string.api_key_.toString(), object:YouTubePlayer.OnInitializedListener{
//            override fun onInitializationSuccess(
//                provider: YouTubePlayer.Provider?,
//                player: YouTubePlayer?,
//                wasRestored: Boolean
//            ) {
//                if(player == null) {
//                    return
//                }
//                if(!wasRestored){
//                    player.cueVideo("dQw4w9WgXcQ")
//                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
//                }
//
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(this@GuestWomanWorkout, "YouTube Failed", Toast.LENGTH_LONG).show()
//
//            }
//        })
//    }


//    private fun getUserData() {
//
//        dbReference = FirebaseDatabase.getInstance().getReference("Users")
//        dbReference.child("Users").child("Justyna").get().addOnSuccessListener {
//            Log.i("firebase", "Got value ${it.value}")
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }
//        dbReference.addValueEventListener(object : ValueEventListener{
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()){
//                    for (userSnapshot in snapshot.children){
//
//                        val user = userSnapshot.getValue(User::class.java)
//                        userList.add(user!!)
//
//                    }
//                    userRecyclerView.adapter = UserAdapter(userList)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//    }


}