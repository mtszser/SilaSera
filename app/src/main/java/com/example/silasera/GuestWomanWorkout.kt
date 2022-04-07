package com.example.silasera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.*
import com.google.firebase.database.*

class GuestWomanWorkout : YouTubeBaseActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guest_woman_workout)


        userRecyclerView = findViewById(R.id.woman_RV)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userList = arrayListOf<User>()
        getUserData()
        getYouTubeData()

    }

    private fun getYouTubeData() {
        val ytView = findViewById<YouTubePlayerView>(R.id.yt_view)
        ytView.initialize(R.string.api_key_.toString(), object:YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                wasRestored: Boolean
            ) {
                if(player == null) return
                if(!wasRestored){
                    player?.cueVideo("dQw4w9WgXcQ")
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@GuestWomanWorkout, "YouTube Failed", Toast.LENGTH_LONG).show()

            }
        })
//        val ytView = findViewById<YouTubePlayerView>(R.id.yt_view)
//        ytView.initialize(R.string.api_key_.toString(), YouTubePlayer.OnInitializedListener)
    }


    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Users")
        dbref.addValueEventListener(object : ValueEventListener{

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