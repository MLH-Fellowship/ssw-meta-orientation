package com.example.cookingassistant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var recipes: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler: RecyclerView = findViewById(R.id.rv)
        val adapter: RecipeAdapter = RecipeAdapter()

        adapter.RecipeAdapter(recipes(), this)


        recycler.hasFixedSize()
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        adapter.setOnItemClickListener(object : RecipeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "You clicked on item no. $position",Toast.LENGTH_SHORT).show()
                val recipes = recipes()

                //Start intent for new Activity and pass data

                val intent = Intent(this@MainActivity, DetailRecipeActivity::class.java)
                intent.putExtra("recipe", recipes[position])

                startActivity(intent)
            }

        })
    }
    private fun recipes(): MutableList<Recipe>{
        var recipesModels : MutableList<Recipe> = ArrayList()
        recipesModels.add(
            Recipe(
                "Lasagna",
                "Total: 3 hrs 15 mins\n" + "Servings: 12",
                "https://insanelygoodrecipes.com/wp-content/uploads/2021/01/Homemade-Ground-Beef-Lasagna-with-Melted-Cheese.png",
                listOf(
                            "1 pound sweet Italian sausage\n" +
                            "¾ pound lean ground beef\n" +
                            "½ cup minced onion\n" +
                            "2 cloves garlic, crushed\n" +
                            "1 (28 ounce) can crushed tomatoes\n" +
                            "2 (6 ounce) cans tomato paste\n" +
                            "2 (6.5 ounce) cans canned tomato sauce\n" +
                            "½ cup water\n" +
                            "2 tablespoons white sugar\n" +
                            "1 ½ teaspoons dried basil leaves\n" +
                            "½ teaspoon fennel seeds\n" +
                            "1 teaspoon Italian seasoning\n" +
                            "1 ½ teaspoons salt, divided, or to taste\n" +
                            "¼ teaspoon ground black pepper\n" +
                            "4 tablespoons chopped fresh parsley\n" +
                            "12 lasagna noodles\n" +
                            "16 ounces ricotta cheese\n" +
                            "1 egg\n" +
                            "¾ pound mozzarella cheese, sliced\n" +
                            "¾ cup grated Parmesan cheese\"",
                    "In a Dutch oven, cook sausage, ground beef, onion, and garlic over medium heat until well browned. Stir in crushed tomatoes, tomato paste, tomato sauce, and water. Season with sugar, basil, fennel seeds, Italian seasoning, 1 teaspoon salt, pepper, and 2 tablespoons parsley. Simmer, covered, for about 1 1/2 hours, stirring occasionally.",
                    "Bring a large pot of lightly salted water to a boil. Cook lasagna noodles in boiling water for 8 to 10 minutes. Drain noodles, and rinse with cold water. In a mixing bowl, combine ricotta cheese with egg, remaining parsley, and 1/2 teaspoon salt.",
                    "Preheat oven to 375 degrees F (190 degrees C).",
                    "To assemble, spread 1 1/2 cups of meat sauce in the bottom of a 9x13-inch baking dish. Arrange 6 noodles lengthwise over meat sauce. Spread with one half of the ricotta cheese mixture. Top with a third of mozzarella cheese slices. Spoon 1 1/2 cups meat sauce over mozzarella, and sprinkle with 1/4 cup Parmesan cheese. Repeat layers, and top with remaining mozzarella and Parmesan cheese. Cover with foil: to prevent sticking, either spray foil with cooking spray, or make sure the foil does not touch the cheese.",
                    "Bake in preheated oven for 25 minutes. Remove foil, and bake an additional 25 minutes. Cool for 15 minutes before serving."
                )
            )
        )
        recipesModels.add(
            Recipe(
                "Homemade Sushi Rolls",
                "Total: 1 hr 50 mins\n"+ "Servings: 4",
                "https://mahatmarice.com/wp-content/uploads/2019/05/Vegetarian-Sushi-Rolls.jpg",
                listOf(
                    "⅔ cup water\n" +
                            "⅓ cup medium-grain white rice, uncooked\n" +
                            "6 tablespoons rice vinegar\n" +
                            "4 sheets nori (dry seaweed)\n" +
                            "4 ounces smoked salmon, cut into strips\n" +
                            "2 ounces cold cream cheese, cut into thin strips\n" +
                            "4 scallions, sliced into thin strips",
                    "Bring water and rice to a boil in a saucepan. Reduce heat to medium-low, cover, and simmer until rice is tender and water has been absorbed, 20 to 25 minutes. Remove from the heat and cool, about 10 minutes. Toss with vinegar.",
                    "Place 1 sheet of nori on a rolling mat, layer with 1/4 of the rice, and press rice out to the edges. Lay 1/4 of the salmon, 1/4 of the cream cheese, and 1/4 of the scallions in the center. Roll up carefully and wrap in plastic wrap. Repeat with remaining nori, rice, salmon, cream cheese, and scallions. Place in the refrigerator until chilled, at least 1 hour.",
                    "Remove sushi rolls from the refrigerator, discard plastic wrap, and cut each roll into 6 slices. Layer all rolls on a platter."
                )
            )
        )
        recipesModels.add(
            Recipe(
                "Tacos de carnitas",
                "Total: 60 mins\n" + "Servings: 6",
                "https://images-gmi-pmc.edge-generalmills.com/3fe11881-11da-46fa-8dc6-b6a29816bbd2.jpg",
                listOf(
                    "2 1/2\n" +
                            "lbs boneless country-style pork ribs, cut into 2-inch pieces\n" +
                            "2 1/2\n" +
                            "teaspoons of salt\n" +
                            "one\n" +
                            "teaspoon garlic powder\n" +
                            "1 1/2\n" +
                            "teaspoons of pepper\n" +
                            "one\n" +
                            "Cup of water\n" +
                            "4\n" +
                            "diced tomatillos\n" +
                            "4\n" +
                            "diced roma tomatoes\n" +
                            "one\n" +
                            "small white onion, diced\n" +
                            "one\n" +
                            "minced garlic clove\n" +
                            "two\n" +
                            "chopped serrano peppers\n" +
                            "1/3\n" +
                            "cup chopped cilantro\n" +
                            "one\n" +
                            "tablespoon of olive oil\n" +
                            "one\n" +
                            "teaspoon ground oregano\n" +
                            "juice of 1 lime\n" +
                            "Juice of 1 large orange\n" +
                            "juice of 1 lemon\n" +
                            "1/4\n" +
                            "cup of lard or vegetable fat\n" +
                            "One 1-inch cinnamon stick\n" +
                            "two\n" +
                            "mashed or diced avocados\n" +
                            "12\n" +
                            "corn tortillas or 24 for double tortilla tacos",
                    "Place the pork in a large heavy-duty pot and season with 1 teaspoon salt, 1 teaspoon garlic powder, and 1 teaspoon pepper. Add 1 cup of water. Cover the pot but leave it slightly open on one side. Let it simmer at just above medium heat for about 20 minutes or until all the water evaporates. Do not touch the meat while it is steaming.",
                    "In a medium bowl, combine tomatillos, tomatoes, white onion, garlic, serrano peppers, cilantro, olive oil, oregano, juice of 1 lime, 1 teaspoon salt, and 1/2 teaspoon salt. Pepper. Stir well to mix and taste if it's salty. Cover the bowl and reserve the mixture.",
                    "In a mug, mix the freshly squeezed orange juice and the juice of 1/2 lemon. When the water from the carnitas has evaporated, add the juice mixture and let it cook.",
                    "When all the juice evaporates, remove the lid, add the lard and the cinnamon stick. Cook pork until nicely browned, turning as needed. Remove it from the oil with a slotted spoon and place it on a plate covered with paper towels. Let cool slightly before carving the meat and placing it on a covered tray.",
                    "In a small bowl, combine avocados, remaining lemon juice, and 1/2 teaspoon salt. Stir well and taste if it is salty. Heat tortillas on a large grill or comal and keep warm in a tortilla warmer or wrap in foil. Serve the tacos with salsa and fresh avocados."
                )
            )
        )
        recipesModels.add(
            Recipe(
                "Ramen Noodle Soup",
                "Total: 15 mins\n" +
                        "Servings: 2",
                "https://cdn.apartmenttherapy.info/image/upload/f_jpg,q_auto:eco,c_fill,g_auto,w_1500,ar_1:1/k%2Farchive%2F4228906cb4af7dccaf0c7809e5e354a1a6f693b0",
                listOf(
                    "3 ½ cups vegetable broth\n" +
                            "1 (3.5 ounce) package ramen noodles with dried vegetables\n" +
                            "2 teaspoons soy sauce\n" +
                            "½ teaspoon chili oil\n" +
                            "½ teaspoon minced fresh ginger root\n" +
                            "1 teaspoon sesame oil\n" +
                            "2 green onions, sliced",
                    "In a medium saucepan combine broth and noodles. Cover and bring to a boil over high heat; stir to break up noodles. Reduce heat to medium and add soy sauce, chili oil and ginger. Simmer, uncovered, for 10 minutes. Stir in sesame oil and garnish with green onions."
                )
            )
        )


        return recipesModels
    }
}