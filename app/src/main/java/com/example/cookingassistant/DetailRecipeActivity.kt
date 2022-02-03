package com.example.cookingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_recipe)

        //get intent object, and data from intent
        val intent = getIntent()
        val recipe = intent.getSerializableExtra("recipe") as? Recipe

        //form one textview

        val detail : TextView = findViewById(R.id.detailRecipe)
        if (recipe != null) {
            detail.text = recipe.title
        }
    }
}