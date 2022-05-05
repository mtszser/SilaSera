package com.example.silasera.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.silasera.R
import com.example.silasera.dataclass.GuestWorkout

class GuestWorkoutAdapter(
    private var guestWorkoutList: ArrayList<GuestWorkout>,
    private val onWorkoutClick: (GuestWorkout) -> Unit

    ): RecyclerView.Adapter<GuestWorkoutAdapter.MyWorkoutHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWorkoutHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.guest_woman_user, parent, false)
        return MyWorkoutHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyWorkoutHolder, position: Int) {
       val guestWomanWorkout = guestWorkoutList[position]
        holder.bindWorkoutData(guestWomanWorkout)
    }


    override fun getItemCount(): Int = guestWorkoutList.size

    inner class MyWorkoutHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindWorkoutData(guestWorkout: GuestWorkout) {
            with(itemView){

                val workoutName: TextView = itemView.findViewById(R.id.woman_tv_workoutName)
                val workoutDesc: TextView = itemView.findViewById(R.id.woman_tv_workoutDesc)

                workoutName.text = guestWorkout.wname
                workoutDesc.text = guestWorkout.desc

                setOnClickListener{
                    onWorkoutClick(guestWorkout)
                }
            }

        }


    }

}