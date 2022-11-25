package com.example.kitchenassistant.ui.myRecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.databinding.FragmentMyRecipesBinding
import com.example.kitchenassistant.ui.Recipe
import com.example.kitchenassistant.ui.allRecipes
import java.util.*


class MyRecipesFragment : Fragment() {

    private var _binding: FragmentMyRecipesBinding? = null

    private lateinit var recipes: MutableList<Recipe>
    private lateinit var rvRecipes: RecyclerView
    private lateinit var rvRecipesAdapter: MyRecipesAdapter
    private lateinit var deletedRecipe: Recipe


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpRecycleView()

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecycleView() {
        val aux = ArrayList<Recipe>()
        var i = 0
        while (i < 10) {
            for (r in allRecipes) if (aux.add(r)) i++
        }

        recipes = aux

        rvRecipes = binding.rvMyRecipes
        rvRecipesAdapter = MyRecipesAdapter(recipes)
        rvRecipes.adapter = rvRecipesAdapter
        rvRecipes.layoutManager = LinearLayoutManager(context)

        binding.svMyRecipes.clearFocus()

        binding.svMyRecipes.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    val filteredList = recipes.filter { p ->
                        p.title.lowercase(Locale.ROOT).contains(p0.lowercase(Locale.ROOT))
                    } as MutableList<Recipe>

                    rvRecipesAdapter.setFilteredList(filteredList)
                }
                return false
            }
        })

        ItemTouchHelper(simpleCallback).attachToRecyclerView(rvRecipes)

    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val startPosition = viewHolder.adapterPosition
            val endPosition = target.adapterPosition

            Collections.swap(recipes, startPosition, endPosition)
            recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    }


}