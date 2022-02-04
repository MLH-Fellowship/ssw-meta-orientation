package com.example.cookingassistant

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.app.ActivityCompat.requestPermissions
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.cookingassistant.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import org.vosk.Recognizer
import org.vosk.android.SpeechService
import org.vosk.android.StorageService
import java.io.IOException
import java.lang.Exception

class DetailRecipeActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    lateinit var title: TextView
    lateinit var stepDisplayer: TextView
    lateinit var stepTitle: TextView
    private var steps: MutableList<String> = ArrayList()
    private var indexSteps = 0
    lateinit var progressBar: ProgressBar
    //voice control
    private lateinit var binding: ActivityMainBinding
    private lateinit var service: SpeechService
    private val REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_recipe)

        //get intent object, and data from intent
        val intent = getIntent()
        val recipe = intent.getSerializableExtra("recipe") as? Recipe

        //initialize views
        if (recipe != null) {
            initViews(recipe)
        }

        //validate permissions for voice recording
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED -> {
                loadService()
            }
            shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO) -> {
                // Show educational notification explaining why the permission is needed
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_CODE)
            }
        }
    }

    fun loadService() {
        StorageService.unpack(this, "model-en-us", "model",
            { model ->
                try {
                    val rec = Recognizer(model, 16000.0f)
                    service = SpeechService(rec, 16000.0f)
                    println("Recognizer and SpeechService initialized")
                } catch (e: IOException) {
                    println("Error: " + e.message)
                }
            }
        ) { e -> println("Failed to unpack the model: " + e.message + " ###") }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    println("Permissions granted")
                    loadService()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    fun initViews(recipe: Recipe){
        title = findViewById(R.id.titleRecipe)
        stepDisplayer = findViewById(R.id.stepsTextView)
        stepTitle = findViewById(R.id.stepTitle)
        image = findViewById(R.id.recipeCoverImage)
        progressBar = findViewById(R.id.progressBar)
        //init text and images
        steps.addAll(recipe.steps)
        title.text = recipe.title
        stepTitle.text = "Ingredients"
        stepDisplayer.text = steps[indexSteps]
        Picasso.get().load(recipe.image).into(image)
        progressBar.max = steps.size
        progressBar.progress = 0
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

    fun onRecord(view: View) {
        //start listening
        try{
            service.startListening(CommandListener(), -1)
        }catch (e: Exception){
            println(e.toString())
        }
    }
}