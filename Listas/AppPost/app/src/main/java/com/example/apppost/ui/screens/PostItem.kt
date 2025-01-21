package com.example.apppost.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apppost.data.models.Post
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostItem(post: Post, onDelete: (Int) -> Unit, onEdit:(Post) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Green
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = post.content, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(
                    onClick = {showDialog = true},
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Deletar")
                }

                Button(onClick = {onEdit(post)}, colors = ButtonDefaults.buttonColors(Color.Gray)) {
                    Text(text = "Editar")
                }
            }
        }
    }

    if(showDialog){
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text("Confirmar Exclusão")
            },
            text = {
                Text(text = "Tem certeza que deseja deletar este post ?")
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete(post.id)
                        showDialog = false
                    }
                ) {
                    Text("Sim")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

