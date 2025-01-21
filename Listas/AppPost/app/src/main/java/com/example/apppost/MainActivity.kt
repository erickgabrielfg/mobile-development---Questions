package com.example.apppost

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.apppost.ui.screens.PostScreen
import com.example.apppost.ui.screens.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    var selectTab by remember { mutableIntStateOf(0) }

    Scaffold (
        topBar = {
            TopAppBar(title = {"Post App"}, Modifier.background(color = MaterialTheme.colorScheme.onSurface))
        },
        bottomBar = {
            BottomNavigation (
                backgroundColor = Color.Red
            ){
                BottomNavigationItem(
                    selected = selectTab == 0,
                    onClick = { selectTab = 0},
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuários", tint = Color.White)},
                    label = {Text("Usuários", color = Color.White)}
                )

                BottomNavigationItem(
                    selected = selectTab == 1,
                    onClick = { selectTab = 1},
                    icon = { Icon(Icons.Default.List, contentDescription = "Posts", tint = Color.White)},
                    label = {Text("Posts", color = Color.White)}
                )
            }
        }
    ){
        when(selectTab){
            0 -> UserScreen()
            1 -> PostScreen()
        }
    }
}