package com.example.silasera.womanWorkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.silasera.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView


class WomanWorkoutUpper : Fragment(), Player.Listener {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var exoView: StyledPlayerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val wwU = inflater.inflate(R.layout.woman_workout_upper, container, false)
        exoView = wwU.findViewById(R.id.upper_player_view)
        setUpPlayer(exoView)

        val button = wwU.findViewById<Button>(R.id.upper_back_button)
        button.setOnClickListener{
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_womanWorkoutUpper_to_womanWorkoutPlans) }
        }
        return wwU

    }



    private fun setUpPlayer(exoView: StyledPlayerView) {
        exoPlayer = ExoPlayer.Builder(this.requireContext()).build()
        exoView.player = exoPlayer
        exoPlayer.addListener(this)

        addMp4Files(exoPlayer)
    }

    private fun addMp4Files(exoPlayer: ExoPlayer) {
        val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
        exoPlayer.addMediaItem(mediaItem)
        exoPlayer.prepare()
    }
}