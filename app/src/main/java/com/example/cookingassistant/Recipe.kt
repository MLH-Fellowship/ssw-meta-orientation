package com.example.cookingassistant

import java.io.Serializable

data class Recipe (
    val title: String,
    val description: String,
    val image: String,
    val steps: List<String>
    ) : Serializable