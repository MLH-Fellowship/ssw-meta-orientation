package com.example.cookingassistant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class RecipeAdapter(private var recipes : JSONArray,private var listener : View.OnClickListener)  : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        lateinit var title : TextView
        lateinit var description : TextView
        lateinit var coverImage : ImageView
        init {
            title = itemView.findViewById(R.id.recipeTitle)
            description =itemView.findViewById(R.id.recipeDescription)
            coverImage =itemView.findViewById(R.id.coverImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_layout, parent, false)
        val dvh = RecipeViewHolder(view)
        view.setOnClickListener(listener)
        return dvh
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        var aux=recipes.getJSONObject(position)
        holder.title.text = aux["title"].toString()
        holder.description.text = aux["description"].toString()
        //holder.coverImage.text = aux["anio"].toString()

    }

    override fun getItemCount(): Int {
        return recipes.length()
    }

}