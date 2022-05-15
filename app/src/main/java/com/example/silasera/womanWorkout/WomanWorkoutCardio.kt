package com.example.silasera.womanWorkout

import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.silasera.R
import com.example.silasera.dataclass.FreePlaylist
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.mikhaellopez.circularprogressbar.CircularProgressBar


class WomanWorkoutCardio : Fragment(), Player.Listener {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var exoView: StyledPlayerView
    private lateinit var stopButton: Button
    private lateinit var playButton: Button
    private lateinit var backButton: Button
    private lateinit var countDownBar: CircularProgressBar
    private lateinit var progressBar: ProgressBar
    private lateinit var countDown: TextView
    private lateinit var exName: TextView
    private var isStop = false
    private lateinit var cardioList: ArrayList<FreePlaylist>
    private lateinit var cardioPlayList: ArrayList<MediaItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        cardioList = ArrayList()
        cardioPlayList = ArrayList()
        cardioPlayList.add(MediaItem.fromUri("https://storage.googleapis.com/silasera-f7482.appspot.com/ci%C4%85g245_1.mp4"))
        cardioPlayList.add(MediaItem.fromUri("https://storage.googleapis.com/silasera-f7482.appspot.com/ci%C4%85g245_1.mp4"))
        cardioPlayList.add(MediaItem.fromUri("https://storage.googleapis.com/silasera-f7482.appspot.com/ci%C4%85g245_1.mp4"))
        cardioPlayList.add(MediaItem.fromUri("https://storage.googleapis.com/silasera-f7482.appspot.com/ci%C4%85g245_1.mp4"))
        cardioList.add(FreePlaylist("Deadlift1", cardioPlayList[0]))
        cardioList.add(FreePlaylist("Deadlift2", cardioPlayList[1]))
        cardioList.add(FreePlaylist("Deadlift3", cardioPlayList[2]))
        cardioList.add(FreePlaylist("Deadlift4", cardioPlayList[3]))


        val wwC = inflater.inflate(R.layout.woman_workout_cardio, container, false)
        exoView = wwC.findViewById(R.id.cardio_player_view)
        progressBar = wwC.findViewById(R.id.cardio_progress_bar)
        setUpPlayer(exoView)
        setUpItems(wwC)






        if (savedInstanceState != null){
            savedInstanceState.getInt("mediaItem").let { restoreMedia ->
                val seekTime = savedInstanceState.getLong("SeekTime")
                exoPlayer.seekTo(restoreMedia, seekTime)
                exoPlayer.play()
            }
        }

        return wwC

    }

    private fun setUpItems(wwC: View) {

        exName = wwC.findViewById(R.id.cardio_ex_name)
        exName.text = cardioList[0].videoName
        countDownBar = wwC.findViewById(R.id.cardio_countdown_bar)
        countDown = wwC.findViewById(R.id.cardio_countdown)
        backButton = wwC.findViewById(R.id.cardio_back_button)
        backButton.setOnClickListener{
            exoPlayer.stop()
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_womanWorkoutCardio_to_womanWorkoutPlans) }
        }
        stopButton = wwC.findViewById(R.id.cardio_stop_button)
        stopButton.setOnClickListener{
            exoPlayer.pause()
            exoView.useController = true
            exoView.showController()
            isStop = true
        }
        playButton = wwC.findViewById(R.id.cardio_start_button)
        playButton.setOnClickListener {
                exoPlayer.play()
                exoView.hideController()
                setCountDown(countDown, countDownBar)
                isStop = false
        }

        }

    private fun setCountDown(countDown: TextView?, countDownBar: CircularProgressBar) {

        object : CountDownTimer(15000, 1000) {
            var progress = 0
            override fun onTick(millisUntilFinished: Long) {

                var totalTime = 15000/1000
                var timeLeft = millisUntilFinished/1000

                progress = (timeLeft*100/totalTime).toInt()
                countDownBar.progress = progress.toFloat()



                countDown?.text = "" + millisUntilFinished/1000
                if (isStop) {
                   cancel()
                }

            }

            override fun onFinish() {
                countDown?.text = "15"
                countDownBar.progress = progress.toFloat()
                exoPlayer.seekToNextMediaItem()
                isStop = true
                exoPlayer.pause()
                when (exoPlayer.currentMediaItemIndex) {
                    0 -> exName.text = cardioList[0].videoName
                    1 -> exName.text = cardioList[1].videoName
                    2 -> exName.text = cardioList[2].videoName
                    3 -> exName.text = cardioList[3].videoName
                }

            }

        }.start()
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
        for (i in cardioPlayList.indices) {
            exoPlayer.addMediaItem(cardioList[i].videoUrl)
        }
        exoPlayer.prepare()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        when (playbackState) {
            Player.STATE_BUFFERING -> {
                exoView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                exoView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        exoPlayer.playWhenReady
    }





}

