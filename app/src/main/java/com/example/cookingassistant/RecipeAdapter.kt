package com.example.cookingassistant

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    var recipes : MutableList<Recipe> = ArrayList()
    lateinit var context: Context

    fun RecipeAdapter(recipes: MutableList<Recipe>, context: Context){
        this.recipes = recipes
        this.context = context
    }

    class RecipeViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view){
        val title: TextView
        val description: TextView
        val image: ImageView

        init{
            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
            title = view.findViewById(R.id.recipeTitle)
            description = view.findViewById((R.id.recipeDescription))
            image = view.findViewById(R.id.coverImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_layout, parent, false)
        return RecipeViewHolder(layoutInflater, mListener)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.title.text = recipes[position].title
        holder.description.text = recipes[position].description
        Picasso.get().load(recipes[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}