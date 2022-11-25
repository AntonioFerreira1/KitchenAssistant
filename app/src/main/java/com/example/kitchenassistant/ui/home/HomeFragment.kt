package com.example.kitchenassistant.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.databinding.FragmentHomeBinding
import com.example.kitchenassistant.ui.Recipe
import com.example.kitchenassistant.ui.allRecipes

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recommended: List<Recipe>
    private lateinit var rv_recommended: RecyclerView
    private lateinit var recommendedAdapter: HomeAdapter

    private lateinit var popular: List<Recipe>
    private lateinit var rv_popular: RecyclerView
    private lateinit var popularAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecommended()
        setupPopular()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecommended() {
        val aux = ArrayList<Recipe>()
        var i = 0
        while (i < 10) {
            for (r in allRecipes) if (aux.add(r)) i++
        }
        recommended = aux

        rv_recommended = binding.rvRecommended
        recommendedAdapter = HomeAdapter(recommended)
        rv_recommended.adapter = recommendedAdapter
        rv_recommended.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setupPopular() {
        val aux = ArrayList<Recipe>()
        var i = 0
        while (i < 10) {
            for (r in allRecipes) if (aux.add(r)) i++
        }

        popular = aux

        rv_popular = binding.rvPopular
        popularAdapter = HomeAdapter(popular)
        rv_popular.adapter = popularAdapter
        rv_popular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }


}