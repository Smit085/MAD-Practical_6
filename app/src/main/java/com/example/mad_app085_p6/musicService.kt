package com.example.mad_app085_p6

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import kotlin.math.log

class musicService: Service() {

    lateinit var mp: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (!this::mp.isInitialized) {
            mp = MediaPlayer.create(this, R.raw.song_1)
        }
        else{
            mp.seekTo(0)
        }
        if(intent!=null){
            val action: String? = intent.getStringExtra("MusicService")
            if(action == "Play" && !mp.isPlaying){
                mp.seekTo(0)
                mp.start()
            }
            else if(action == "Pause" && mp.isPlaying){
                mp.seekTo(0)
                mp.pause()
            }
        }
        else{
            mp.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        mp.stop()
        super.onDestroy()
    }
}