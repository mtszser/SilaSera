package com.example.silasera.womanWorkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.silasera.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView


class WomanWorkoutCardio : Fragment(), Player.Listener {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var exoView: StyledPlayerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val wwC = inflater.inflate(R.layout.woman_workout_cardio, container, false)
        exoView = wwC.findViewById(R.id.cardio_player_view)
        setUpPlayer(exoView)



        return wwC

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
