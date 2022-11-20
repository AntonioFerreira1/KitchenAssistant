package com.example.kitchenassistant.ui.myProducts

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kitchenassistant.databinding.FragmentMyProductsBinding
import com.example.kitchenassistant.ui.Product
import com.example.kitchenassistant.ui.Unit
import java.util.*


class MyProductsFragment : Fragment() {

    private var _binding: FragmentMyProductsBinding? = null
    lateinit var allProducts: MutableList<Product>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        allProducts = mutableListOf(
            Product("carne", 1, Unit.KILOGRAM),
            Product("peixe", 2, Unit.KILOGRAM)
        )

        setUpRecycleView()
        setupSVAddMenu()
        binding.btAddProduct.setOnClickListener {
            binding.llAddProductMenu.visibility = View.VISIBLE
        }

        binding.btCancel.setOnClickListener {
            binding.llAddProductMenu.visibility = View.GONE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecycleView() {
        val adapter = ProductsAdapter(allProducts)

        val rvProducts = binding.recyclerView
        rvProducts.adapter = adapter
        rvProducts.layoutManager = LinearLayoutManager(context)

        val sv = binding.svMyProducts
        sv.clearFocus()
        sv.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    val filteredList = allProducts.filter { p ->
                        p.title.lowercase(Locale.ROOT).contains(p0.lowercase(Locale.ROOT))
                    } as MutableList<Product>

                    adapter.setFilteredList(filteredList)
                }
                return false
            }
        })
    }

    private fun setupSVAddMenu() {
        setupSpinner()

        val languages = arrayOf("C", "C++", "Java", "C#", "PHP", "AJAX", "JSON")

        val adapter =
            ArrayAdapter<String>(requireContext(), R.layout.select_dialog_item, languages)

        val acTextView = binding.actvProducts

        acTextView.threshold = 1
        acTextView.setAdapter(adapter)
    }

    private fun setupSpinner() {
        val spinnerItems = Unit.values().map { u -> u.name }
        val spinner = binding.spUnitType
        val adapter = ArrayAdapter(
            requireContext(), R.layout.simple_spinner_dropdown_item, spinnerItems
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}