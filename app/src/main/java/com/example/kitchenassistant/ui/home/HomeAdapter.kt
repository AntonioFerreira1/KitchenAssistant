package com.example.kitchenassistant.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.R
import com.example.kitchenassistant.ui.Recipe


class RecipesAdapter(
    val recipes: List<Recipe>
) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {


    class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView = itemView.findViewById(R.id.tv_title)
        val tv_duration: TextView = itemView.findViewById(R.id.tv_duration)
        val tv_category: TextView = itemView.findViewById(R.id.tv_category)
        val iv_image: ImageView = itemView.findViewById(R.id.iv_recipeImg)
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
    }

    override fun getItemCount(): Int {
        return recipes.size
    }


}