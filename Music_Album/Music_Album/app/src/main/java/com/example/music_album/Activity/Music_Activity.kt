package com.example.music_album.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.music_album.Adoptors.Artict_Adoptor
import com.example.music_album.Adoptors.Music_Adoptor
import com.example.music_album.Modal.Album
import com.example.music_album.Modal.Aritct
import com.example.music_album.R
import com.example.music_album.databinding.ActivityMusicBinding

class Music_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding

     var musiclist = mutableListOf<Album>()
  private  lateinit var Madoptor: Music_Adoptor


     //secomd view data
    var artistlist = mutableListOf<Aritct>()
   private lateinit var Aadoptor: Artict_Adoptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addData()

        Madoptor = Music_Adoptor(this, musiclist)



        var manager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.recView.layoutManager = manager
        binding.recView.adapter = Madoptor

        Aadoptor = Artict_Adoptor(this,artistlist)

        var amanager =GridLayoutManager(this,2)
        binding.recView2.layoutManager = amanager

       // var mn = StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL)

        binding.recView2.adapter = Aadoptor

    }

    private fun addData() {
        musiclist.add(Album(1, "Covers Vol.2", R.drawable.s1))
        musiclist.add(Album(2, "I'm Gonna Bee[500 mil]", R.drawable.s3))
        musiclist.add(Album(3, "True Love", R.drawable.s4))
        musiclist.add(Album(4, "Song Tital", R.drawable.s5))
        musiclist.add(Album(5, "Sig Nat Ura", R.drawable.s4))
        musiclist.add(Album(6, "Covers Vol.2", R.drawable.s3))
        musiclist.add(Album(7, "I'm Gonna Bee[500 mil]", R.drawable.s4))
        musiclist.add(Album(9, "True Love", R.drawable.s1))
        musiclist.add(Album(10, "Song Tital", R.drawable.s3))
        musiclist.add(Album(11, "Sig Nat Ura", R.drawable.s4))


        //second

        artistlist.add(Aritct(1, "Covers Vol.2", R.drawable.s1))
        artistlist.add(Aritct(2, "I'm Gonna Bee[500 mil]", R.drawable.s3))
        artistlist.add(Aritct(3, "True Love", R.drawable.s4))
        artistlist.add(Aritct(4, "Song Tital", R.drawable.s5))
        artistlist.add(Aritct(5, "Sig Nat Ura", R.drawable.s4))
        artistlist.add(Aritct(6, "Covers Vol.2", R.drawable.s3))
        artistlist.add(Aritct(7, "I'm Gonna Bee[500 mil]", R.drawable.s4))
        artistlist.add(Aritct(9, "True Love", R.drawable.s1))
        artistlist.add(Aritct(10, "Song Tital", R.drawable.s3))
        artistlist.add(Aritct(11, "Sig Nat Ura", R.drawable.s4))
    }
}