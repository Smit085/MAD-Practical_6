package com.example.mad_app085_p6

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import kotlin.properties.Delegates

class MusicService: Service() {

    lateinit var mp: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var pausedAt = 0
        if (!this::mp.isInitialized) {
            val songIndex = intent.getIntExtra("SongIndex", -1)
            if (songIndex != -1) {
                Log.i("Song=",songIndex.toString())
                mp = MediaPlayer.create(this, song_list[songIndex].song)
                mp.start()
            }
            else{
                Log.i("SongIndexError","1")
                mp = MediaPlayer.create(this, R.raw.song_1)
                mp.start()
            }
        }
        else{
            mp.stop()
            val songIndex = intent.getIntExtra("SongIndex", -1)
            if (songIndex != -1) {
                Log.i("Song=",songIndex.toString())
                mp = MediaPlayer.create(this, song_list[songIndex].song)
                mp.start()
            }
            else{
                Log.i("SongIndexError","1")
                mp = MediaPlayer.create(this, R.raw.song_1)
                mp.start()
            }
        }
        if(intent!=null){
            val action: String? = intent.getStringExtra("MusicService")
            if(action == "Play" && !mp.isPlaying){
                mp.seekTo(pausedAt)
            }
            else if(action == "Pause" && mp.isPlaying){
                pausedAt = mp.currentPosition
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
        mp.release()
        super.onDestroy()
    }
}