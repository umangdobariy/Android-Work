package com.example.animationlec_49

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView

class Tweening_Activity : AppCompatActivity() {

    val  ivThumbnail:ImageView
    get() = findViewById(R.id.iv_thumbnail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweening)

        var animation = AnimationUtils.loadAnimation(this,R.anim.move_animation);

        ivThumbnail.setOnClickListener {
            ivThumbnail.startAnimation(animation)
        }

    }
}