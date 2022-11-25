package com.example.kitchenassistant

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val b = intent.extras

        val title: TextView = findViewById(R.id.tv_r_title)
        val category: TextView = findViewById(R.id.tv_r_category)
        val duration: TextView = findViewById(R.id.tv_r_duration)
        val ingredients: TextView = findViewById(R.id.tv_ingredients)
        val steps: TextView = findViewById(R.id.tv_steps)
        val img: ShapeableImageView = findViewById(R.id.imageView3)


        if (b != null) {
            title.text = b.getString("title")
            category.text = b.getString("category")
            duration.text = b.getString("duration")
            ingredients.text = b.getString("ingredients")
            steps.text = b.getString("steps")
        }
    }
}