package com.example.cookingassistant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class RecipeFragment : Fragment(),View.OnClickListener  {
    private lateinit var queue : RequestQueue
    private var games: JSONArray= JSONArray()
    private lateinit var adapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var llm: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        games=JSONArray()
        load()
    }
    fun load(){
        var url ="https://raw.githubusercontent.com/MLH-Fellowship/ssw-meta-orientation/sebastian/recipes.json"
        queue =Volley.newRequestQueue(this.context)
        var jsonArrayRequest= JsonArrayRequest(

            Request.Method.GET,
            url,
            null,
            {response->
                for(i in 0 until response.length()){

                    val actual= response.getJSONObject(i)

                    games.put(actual)
                }
                adapter = RecipeAdapter(games,this)
                recyclerView.adapter = adapter
            },
            {error->
                Log.wtf("JSON", error.toString())
            }

        )

        queue.add(jsonArrayRequest)
        llm = LinearLayoutManager(this.context)
        llm.orientation = LinearLayoutManager.VERTICAL


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var view = inflater.inflate(R.layout.recipe_fragment, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager=llm
        return view
    }

    override fun onClick(p0: View) {
        //val position = recyclerView.getChildLayoutPosition(p0)
        //val intent= Intent(this.context, GameDetailActivity::class.java)
        //intent.putExtra("position", position)
        //startActivity(intent)
    }

}