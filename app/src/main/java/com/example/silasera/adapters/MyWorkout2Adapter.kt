package com.example.silasera.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.silasera.R
import com.example.silasera.databinding.AppWorkout2CardsBinding
import com.example.silasera.dataclass.MyWorkout2Data

class MyWorkout2Adapter(

    private var exerciseCards: List<MyWorkout2Data>,
    private val onExerciseClick: (MyWorkout2Data) -> Unit,

): RecyclerView.Adapter<MyWorkout2Adapter.MyWorkout2ViewHolder>() {

    inner class MyWorkout2ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var binding = AppWorkout2CardsBinding.bind(view)

        fun bindData(exercise: MyWorkout2Data) {
            with(itemView) {
                val exName = binding.appWorkout2ExName
                exName.text = exercise.exName
                val exSets = binding.appWorkout2Sets
                exSets.text = exercise.exSets
                val exReps = binding.appWorkout2Reps
                exReps.text = exercise.exReps
                val exTempo = binding.appWorkout2Tempo
                exTempo.text = exercise.exTempo
                val exRest = binding.appWorkout2Rest
                exRest.text = exercise.exRest

                setOnClickListener{
                    onExerciseClick(exercise)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWorkout2ViewHolder = MyWorkout2ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.app_workout2_cards, parent, false)
    )


    override fun onBindViewHolder(holder: MyWorkout2ViewHolder, position: Int) {

        val exercise = exerciseCards[position]
        holder.bindData(exercise)

    }

    override fun getItemCount(): Int = exerciseCards.size



}