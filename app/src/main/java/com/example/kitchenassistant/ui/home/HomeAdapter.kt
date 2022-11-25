package com.example.kitchenassistant.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.R
import com.example.kitchenassistant.RecipeActivity
import com.example.kitchenassistant.ui.Recipe


class HomeAdapter(
    val recipes: List<Recipe>
) : RecyclerView.Adapter<HomeAdapter.RecipesViewHolder>() {


    class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView = itemView.findViewById(R.id.tv_title)
        val tv_duration: TextView = itemView.findViewById(R.id.tv_duration)
        val tv_category: TextView = itemView.findViewById(R.id.tv_category)
        val iv_image: ImageView = itemView.findViewById(R.id.iv_recipeImg)
        val context = itemView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_home, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = recipes[position]
        val duration = StringBuilder().append(recipe.duration).append(" min").toString()
        holder.tv_title.text = recipe.title
        holder.tv_duration.text = duration
        holder.tv_category.text = recipe.category

        val id = holder.context.resources.getIdentifier(recipe.img, "drawable", holder.context.packageName)
        val img = ActivityCompat.getDrawable(holder.context, id)
        holder.iv_image.setImageDrawable(img)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.context, RecipeActivity::class.java)

            intent.putExtra("title", recipe.title)
            intent.putExtra("category", recipe.category)
            intent.putExtra("duration", recipe.duration.toString())
            intent.putExtra("ingredients", recipe.ingredients)
            intent.putExtra("steps", recipe.steps)
            intent.putExtra("img", recipe.img)


            it.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return recipes.size
    }


}