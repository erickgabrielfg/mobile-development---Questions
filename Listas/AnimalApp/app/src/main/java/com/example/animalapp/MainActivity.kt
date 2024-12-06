package com.example.animalapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.ui.unit.dp
import com.example.animalapp.ui.theme.AnimalAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimalAppTheme {
                AnimalApp()
            }
        }
    }
}

@Composable
fun AnimalAppMenu(onOptionSelected: (String) -> Unit){
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {expanded = false}
    ) {
        DropdownMenuItem(
            text = {Text("Monkey")},
            onClick = {
                expanded = false
                onOptionSelected("Monkey")
            }
        )
        DropdownMenuItem(
            text = { Text("Suricate") },
            onClick = {
                expanded = false
                onOptionSelected("Suricate")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onAnimalSelected: (String) -> Unit){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "AnimalApp"
                    )
                },
                actions = {
                    AnimalAppMenu(onOptionSelected = onAnimalSelected)
                }
            )

        }
    ){ paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center
        ){
            Text("Escolha algum animal")
        }
    }
}

@Composable
fun AnimalScreen(animal: String){
    val context = LocalContext.current

    val imageRes = if (animal == "monkey") R.drawable.monkey_img else R.drawable.suricate_img
    val soundRes = if (animal == "monkey") R.raw.monkey_sound else R.raw.suricate_sound
    val videoRes = if (animal == "monkey") R.raw.monkey_video else R.raw.suricate_video

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Image of $animal",
            modifier = Modifier.size(200.dp).clip(CircleShape)
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(
            onClick = {
                try {
                    val media = MediaPlayer.create(context, soundRes)
                    if(media != null){
                        media.start()
                        media.setOnCompletionListener {
                            media.release()
                        }
                    }
                }
                catch (e:Exception){
                    println("erro")
                }
            }
        ) {
            Text("Mostrar som do animal")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(context, VideoPlayerActivity::class.java)
                intent.putExtra("Video", videoRes)
                context.startActivity(intent)
            }
        ) {
            Text("Reproduzir vídeo")
        }
    }
}

@Composable
fun AnimalApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home"){
            HomeScreen(onAnimalSelected = {
                animal -> navController.navigate("animal/$animal")
            })
        }
        composable("animal/{animal}", arguments = listOf(navArgument("animal") { type = NavType.StringType })) { backStackEntry ->
            val animal = backStackEntry.arguments?.getString("animal") ?: "monkey"
            AnimalScreen(animal)
        }
    }
}