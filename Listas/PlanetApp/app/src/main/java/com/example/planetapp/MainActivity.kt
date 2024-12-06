package com.example.planetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.planetapp.navigation.NavGraph

@ExperimentalMaterial3Api
class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            NavGraph(
                onSettingsClick = {},
                onHelpClick = {}
            )
        }
    }
}