package com.example.zooapp.model

import com.example.zooapp.R

data class Animal(
    val id: Int,
    val name: String,
    val species: String,
    val image: Int,
    val description: String,
    val curiosities: String,
    val isFavorite: Boolean = false
)

val animalList = listOf(
    Animal(
        id = 1,
        name = "Cavalo",
        species = "Equus ferus caballus",
        image = R.drawable.horse_img,
        description = "O cavalo é um mamífero herbívoro domesticado e amplamente utilizado pelo homem.",
        curiosities = "Os cavalos podem dormir tanto em pé quanto deitados."
    ),
    Animal(
        id = 2,
        name = "Cobra",
        species = "Serpentes",
        image = R.drawable.snake_img,
        description = "As cobras são répteis sem patas, conhecidas por sua habilidade de rastejar.",
        curiosities = "As cobras podem detectar vibrações no solo usando o corpo."
    ),
    Animal(
        id = 3,
        name = "Girafa",
        species = "Giraffa camelopardalis",
        image = R.drawable.giraffe_img,
        description = "A girafa é o animal terrestre mais alto do mundo, com um pescoço longo e manchas únicas.",
        curiosities = "O coração de uma girafa pode pesar até 11 kg para bombear sangue até a cabeça."
    ),
    Animal(
        id = 4,
        name = "Tigre",
        species = "Panthera tigris",
        image = R.drawable.tiger_img,
        description = "O tigre é o maior felino do mundo e é conhecido por suas listras características.",
        curiosities = "As listras de cada tigre são únicas, como impressões digitais."
    )
)
