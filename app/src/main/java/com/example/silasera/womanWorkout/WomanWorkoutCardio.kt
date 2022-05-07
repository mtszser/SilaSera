package com.example.silasera.womanWorkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.silasera.R
import com.google.android.youtube.player.YouTubeBaseActivity


class WomanWorkoutCardio : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val wwC = inflater.inflate(R.layout.woman_workout_cardio, container, false)

        return wwC
    }
}