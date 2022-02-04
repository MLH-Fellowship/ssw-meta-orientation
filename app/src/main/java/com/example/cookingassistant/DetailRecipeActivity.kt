package com.example.cookingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailRecipeActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    lateinit var title: TextView
    lateinit var stepDisplayer: TextView
    lateinit var stepTitle: TextView
    private var steps: MutableList<String> = ArrayList()
    private var indexSteps = 0
    lateinit var recipe: Recipe
    lateinit var backButton: Button
    lateinit var nextButton: Button
    lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_recipe)

        //get intent object, and data from intent
        val intent = getIntent()
        val recipe = intent.getSerializableExtra("recipe") as? Recipe

        //initialize views
        title = findViewById(R.id.titleRecipe)
        stepDisplayer = findViewById(R.id.stepsTextView)
        stepTitle = findViewById(R.id.stepTitle)
        image = findViewById(R.id.recipeCoverImage)
        //backButton = findViewById(R.id.backButton)
        //nextButton = findViewById(R.id.nextButton)
        progressBar = findViewById(R.id.progressBar)

        if (recipe != null) {
            //init text and images
            steps.addAll(recipe.steps)
            title.text = recipe.title
            stepTitle.text = "Ingredients"
            stepDisplayer.text = steps[indexSteps]
            Picasso.get().load(recipe.image).into(image)
            progressBar.max = steps.size
            progressBar.progress = 0
        }
    }

    fun onClickNext(view: View) {
        println("onClickNext")
        indexSteps = indexSteps + 1
        if( indexSteps >= steps.size){
            indexSteps = 0
            stepTitle.text = "Ingredients"
        }
        else{
            stepTitle.text = "Step #${indexSteps}"
        }
        stepDisplayer.text = steps[indexSteps]
        progressBar.progress = indexSteps +1
    }
    fun onClickBack(view: View) {
        println("onClickBack")
        indexSteps = indexSteps - 1

        if( indexSteps < 0){
            indexSteps = steps.size - 1
        }
        else if(indexSteps != 0){
            stepTitle.text = "Step #${indexSteps}"
        }
        else{
            stepTitle.text = "Ingredients"
        }
        stepDisplayer.text = steps[indexSteps]
        progressBar.progress = indexSteps +1
    }

    fun onRecord(view: View) {}
}