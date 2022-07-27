package com.example.silasera.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.silasera.R
import com.example.silasera.databinding.AppWorkoutCardsBinding
import com.example.silasera.dataclass.GuestCard
import com.example.silasera.dataclass.MyWorkoutData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class MyWorkoutAdapter(
    private var workoutCards: List<MyWorkoutData>,
    private val onWorkoutClick: (MyWorkoutData) -> Unit,



)  : RecyclerView.Adapter<MyWorkoutAdapter.MyWorkoutViewHolder>() {
    private lateinit var binding: AppWorkoutCardsBinding


    inner class MyWorkoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(workout: MyWorkoutData){
            with(itemView){
                val imageView = binding.appWorkoutImage
                Picasso.get().load(workout.workoutImage).into(imageView)
                val cardText = binding.appWorkoutName

//                binding.appWorkoutImage
//                image.setImageResource(workout.workoutImage)
                cardText.text = workout.workoutName

                setOnClickListener{
                    onWorkoutClick(workout)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWorkoutViewHolder = MyWorkoutViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.app_workout_cards, parent, false)
    )


    override fun onBindViewHolder(holder: MyWorkoutViewHolder, position: Int) {

        val workout = workoutCards[position]
        holder.bindData(workout)



    }

    override fun getItemCount(): Int = workoutCards.size




}