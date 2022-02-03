package com.example.cookingassistant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity(){
    lateinit var recipeFragment: RecipeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recipeFragment = RecipeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView, recipeFragment, TAG_FRAGMENTO)

        transaction.commit()

    }

    companion object {

        private const val TAG_FRAGMENTO = "fragment"
    }
}