package com.example.silasera.womanWorkout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.silasera.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.ui.StyledPlayerView


class WomanWorkoutCardio : Fragment(), Player.Listener {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var exoView: StyledPlayerView
    private lateinit var stopButton: Button
    private lateinit var playButton: Button
    private lateinit var backButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val wwC = inflater.inflate(R.layout.woman_workout_cardio, container, false)
        exoView = wwC.findViewById(R.id.cardio_player_view)
        setUpPlayer(exoView)
        setUpButtons(wwC)





        if (savedInstanceState != null){
            savedInstanceState.getInt("mediaItem").let { restoreMedia ->
                val seekTime = savedInstanceState.getLong("SeekTime")
                exoPlayer.seekTo(restoreMedia, seekTime)
                exoPlayer.play()
            }
        }

        return wwC

    }

    private fun setUpButtons(wwC: View) {
        backButton = wwC.findViewById(R.id.cardio_back_button)
        backButton.setOnClickListener{
            exoPlayer.pause()
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_womanWorkoutCardio_to_womanWorkoutPlans) }
        }
        stopButton = wwC.findViewById(R.id.cardio_stop_button)
        stopButton.setOnClickListener{
            exoPlayer.pause()
            exoView.useController = true
            exoView.showController()
        }
        playButton = wwC.findViewById(R.id.cardio_start_button)
        playButton.setOnClickListener {
            exoPlayer.play()
            exoView.hideController()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putLong("SeekTime", exoPlayer.currentPosition)
        outState.putInt("mediaItem", exoPlayer.currentMediaItemIndex)
    }


    private fun setUpPlayer(exoView: StyledPlayerView) {
        exoPlayer = ExoPlayer.Builder(this.requireContext()).build()
        exoView.player = exoPlayer
        exoPlayer.addListener(this)
        addMp4Files(exoPlayer)
        exoView.useController = false
    }


    private fun addMp4Files(exoPlayer: ExoPlayer) {
        val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
        exoPlayer.addMediaItem(mediaItem)
        exoPlayer.prepare()
    }





}

