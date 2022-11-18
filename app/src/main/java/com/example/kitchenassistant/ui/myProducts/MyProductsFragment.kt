package com.example.kitchenassistant.ui.myProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.R
import com.example.kitchenassistant.databinding.FragmentMyProductsBinding
import com.example.kitchenassistant.ui.Product
import com.example.kitchenassistant.ui.Unit

class MyProductsFragment : Fragment() {

    private var _binding: FragmentMyProductsBinding? = null
    private lateinit var rvProducts: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpRecycleView()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setUpRecycleView(){
        val adapter = ProductsAdapter(mutableListOf(
            Product("carne", 1, Unit.KILOGRAM),
            Product("peixe", 2, Unit.KILOGRAM)
        ))
        rvProducts = binding.recyclerView
        rvProducts.adapter = adapter
        rvProducts = binding.recyclerView
        rvProducts.layoutManager = LinearLayoutManager(context)
    }

}