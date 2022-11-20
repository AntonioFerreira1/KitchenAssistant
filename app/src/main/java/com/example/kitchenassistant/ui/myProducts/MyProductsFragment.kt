package com.example.kitchenassistant.ui.myProducts

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.databinding.FragmentMyProductsBinding
import com.example.kitchenassistant.ui.Product
import com.example.kitchenassistant.ui.Unit
import java.util.*


class MyProductsFragment : Fragment() {

    private var _binding: FragmentMyProductsBinding? = null
    private lateinit var allProducts: MutableList<Product>
    private lateinit var rvProducts: RecyclerView
    private lateinit var rvProductsAdapter: ProductsAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        allProducts = mutableListOf(
            Product("carne", 1, Unit.KILOGRAM), Product("peixe", 2, Unit.KILOGRAM)
        )

        setUpRecycleView()
        setupSVAddMenu()
        binding.btAddProduct.setOnClickListener {
            binding.llAddProductMenu.visibility = View.VISIBLE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecycleView() {
        rvProductsAdapter = ProductsAdapter(allProducts)
        rvProducts = binding.recyclerView

        rvProducts.adapter = rvProductsAdapter
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

                    rvProductsAdapter.setFilteredList(filteredList)
                }
                return false
            }
        })
    }

    private fun setupSVAddMenu() {
        val unitSpinner = setupSpinner()
        val productACTV = setupProductSelector()
        val amountET = binding.etAmount

        binding.btConfirm.setOnClickListener {
            unitSpinner.selectedItem
            productACTV.text

            if (validateFields()) {
                val newProd = Product(
                    productACTV.text.toString(),
                    amountET.text.toString().toInt(),
                    Unit.valueOf(unitSpinner.selectedItem.toString())
                )
                rvProductsAdapter.addProduct(newProd)
            }

        }

        binding.btCancel.setOnClickListener {
            binding.llAddProductMenu.visibility = View.GONE
            binding.actvProducts.text.clear()
            binding.etAmount.text.clear()
            //binding.spUnitType
        }
    }

    private fun setupProductSelector(): AutoCompleteTextView {
        val languages = arrayOf("C", "C++", "Java", "C#", "PHP", "AJAX", "JSON")

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.select_dialog_item, languages)

        binding.actvProducts.threshold = 1
        binding.actvProducts.setAdapter(adapter)
        return binding.actvProducts
    }

    private fun setupSpinner(): Spinner {
        val spinner = binding.spUnitType
        val spinnerItems = Unit.values().map { u -> u.name }
        val adapter = ArrayAdapter(
            requireContext(), R.layout.simple_spinner_dropdown_item, spinnerItems
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        return spinner
    }

    private fun validateFields(): Boolean {
        return true
    }
}