package com.example.mad_app085_p6

import android.content.Intent
import android.net.Uri
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

        data class Songs(val title: String, val singer: String, val img: Int,val song: String)
//        val song_list: List<Songs> = listOf(
//            Songs("Jai Shri Ram", "Ajay Atul",R.drawable.song_1,R.raw.song_1),
//            Songs("Khalasi", "Aditya Gadhvi", R.drawable.song_2, R.raw.song_2),
//            Songs("Heeriye", "Arijit Singh", R.drawable.song_3, R.raw.song_3),
//            Songs("Shri Krishna Govind Hare Murari", "Jubin Nautiyal", R.drawable.song_4, R.raw.song_4)
//        )
        val song_list: List<Songs> = listOf(
            Songs("Jai Shri Ram", "Ajay Atul",R.drawable.song_1, "android.resource://com.example.mad_app085_p6/raw/song_1"),
            Songs("Khalasi", "Aditya Gadhvi",R.drawable.song_2, "android.resource://com.example.mad_app085_p6/raw/song_2"),
            Songs("Heeriye", "Arijit Singh",R.drawable.song_3, "android.resource://com.example.mad_app085_p6/raw/song_3"),
            Songs("Shri Krishna Govind Hare Murari", "Jubin Nautiyal",R.drawable.song_1, "android.resource://com.example.mad_app085_p6/raw/song_4")
        )

        val play_btn: ImageButton = findViewById(R.id.btn_play)
        val btn_next: ImageButton = findViewById(R.id.btn_next)
        val btn_prev: ImageButton = findViewById(R.id.btn_prev)
        val txt_songname: TextView = findViewById(R.id.txt_songname)
        val txt_songsinger: TextView = findViewById(R.id.txt_songsinger)
        val song_img: ImageView = findViewById(R.id.img_song)

        var play_state: Boolean = false
        var songIndex: Int = 0

        fun setContent(songIndex:Int){
            txt_songname.setText(song_list[songIndex].title)
            txt_songsinger.setText(song_list[songIndex].singer)
            song_img.setImageResource(song_list[songIndex].img)
        }

        fun songPlay(songIndex:Int,action:String){
            Intent(this,musicService::class.java).putExtra("MusicService",action).apply{
                startService(this)
            }
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