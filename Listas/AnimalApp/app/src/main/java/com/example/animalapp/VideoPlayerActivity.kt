package com.example.animalapp

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class VideoPlayerActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val video = intent.getIntExtra("video", R.raw.monkey_video)

            AndroidView(
                factory = {
                    context -> VideoView(context).apply{
                        setVideoURI(Uri.parse("android.resource://$packageName/$video"))
                        start()
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}