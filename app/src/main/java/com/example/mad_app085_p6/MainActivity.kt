package com.example.mad_app085_p6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val play_btn: ImageButton = findViewById(R.id.btn_play)
        val btn_next: ImageButton = findViewById(R.id.btn_next)
        val btn_prev: ImageButton = findViewById(R.id.btn_prev)
        val txt_songname: TextView = findViewById(R.id.txt_songname)
        val txt_songsinger: TextView = findViewById(R.id.txt_songsinger)
        val song_img: ImageView = findViewById(R.id.img_song)

        var play_state: Boolean = false
        var songIndex: Int = 0

        fun setContent(songIndex:Int){
            txt_songname.text = song_list[songIndex].title
            txt_songsinger.text = song_list[songIndex].singer
            song_img.setImageResource(song_list[songIndex].img)
        }

        fun songPlay(songIndex:Int,action:String){
            val playIntent = Intent(this, MusicService::class.java)
            playIntent.putExtra("MusicService", action)
            playIntent.putExtra("SongIndex", songIndex)
            startService(playIntent)

//            Intent(this,MusicService::class.java).putExtra("Song",songIndex).apply{
//                startService(this)
//            }
//            Intent(this,MusicService::class.java).putExtra("MusicService",action).apply{
//                startService(this)
//            }
            setContent(songIndex)
        }

        play_btn.setOnClickListener{
            if(!play_state){
                songPlay(songIndex,"Play")
                play_btn.setImageResource(R.drawable.baseline_pause_circle_12)
                play_state = true
            }
            else{
                songPlay(songIndex,"Pause")
                play_btn.setImageResource(R.drawable.baseline_play_circle_12)
                play_state = false
            }
        }

        btn_next.setOnClickListener{
            val list_len = song_list.size
            songIndex = (songIndex + 1) % list_len
            play_btn.setImageResource(R.drawable.baseline_pause_circle_12)
            play_state = true
            songPlay(songIndex,"Play")
        }

        btn_prev.setOnClickListener {
            val list_len = song_list.size
            songIndex = (songIndex - 1 + list_len) % list_len
            play_btn.setImageResource(R.drawable.baseline_pause_circle_12)
            play_state = true
            songPlay(songIndex,"Play")
        }
    }
}