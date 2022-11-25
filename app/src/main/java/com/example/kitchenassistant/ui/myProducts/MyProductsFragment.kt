package com.example.kitchenassistant.ui.myProducts

import android.R
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.databinding.FragmentMyProductsBinding
import com.example.kitchenassistant.ui.Product
import com.example.kitchenassistant.ui.Unit
import com.google.android.material.snackbar.Snackbar
import java.util.*
import com.example.kitchenassistant.R as R1


class MyProductsFragment : Fragment() {

    private var _binding: FragmentMyProductsBinding? = null
    private lateinit var products: MutableList<Product>
    private lateinit var rvProducts: RecyclerView
    private lateinit var rvProductsAdapter: ProductsAdapter
    private lateinit var deletedProduct: Product


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        products = mutableListOf(
            Product("carne", 1, Unit.KILOGRAM), Product("peixe", 2, Unit.KILOGRAM)
        )

        setUpRecycleView()
        binding.fabAddProduct.setOnClickListener {
            showAddProductDialog()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setUpRecycleView() {
        rvProductsAdapter = ProductsAdapter(products)
        rvProducts = binding.rvMyProducts

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
                    val filteredList = products.filter { p ->
                        p.title.lowercase(Locale.ROOT).contains(p0.lowercase(Locale.ROOT))
                    } as MutableList<Product>

                    rvProductsAdapter.setFilteredList(filteredList)
                }
                return false
            }
        })

        ItemTouchHelper(simpleCallback).attachToRecyclerView(rvProducts)

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

            Collections.swap(products, startPosition, endPosition)
            recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition

            when (direction) {
                ItemTouchHelper.LEFT -> {
                    deletedProduct = products.get(position)
                    rvProductsAdapter.deleteProduct(position)

                    Snackbar.make(
                        rvProducts, "ola", Snackbar.LENGTH_LONG
                    ).setAction("Undo", View.OnClickListener {
                        products.add(position, deletedProduct)
                        rvProducts.adapter?.notifyItemInserted(position)
                    }).show()
                }
            }
        }

    }

    private fun showAddProductDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R1.layout.custom_dialog)

        val actvProduct = dialog.findViewById<AutoCompleteTextView>(R1.id.actv_products)
        val spinner = dialog.findViewById<Spinner>(R1.id.sp_unitType)
        val etAmount = dialog.findViewById<EditText>(R1.id.et_Amount)


        setupProductSelector(actvProduct)
        setupSpinner(spinner)
        setupDialogButtons(dialog, actvProduct, spinner, etAmount)

        dialog.show()
    }

    private fun setupDialogButtons(
        dialog: Dialog, actvProducts: AutoCompleteTextView, spinner: Spinner, etAmount: EditText
    ) {
        dialog.findViewById<Button>(R1.id.bt_confirm).setOnClickListener {
            if (validateFields(dialog, actvProducts, spinner, etAmount)) {
                val newProd = Product(
                    actvProducts.text.toString(),
                    etAmount.text.toString().toInt(),
                    Unit.valueOf(spinner.selectedItem.toString())
                )
                rvProductsAdapter.addProduct(newProd)
                dialog.dismiss()
            }
        }

        dialog.findViewById<Button>(R1.id.bt_Cancel).setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun setupProductSelector(actvProduct: AutoCompleteTextView) {
        val languages = arrayOf("C", "C++", "Java", "C#", "PHP", "AJAX", "JSON")

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.select_dialog_item, languages)

        actvProduct.threshold = 1
        actvProduct.setAdapter(adapter)
    }

    private fun setupSpinner(spinner: Spinner): Spinner {
        val spinnerItems = Unit.values().map { u -> u.name }
        val adapter = ArrayAdapter(
            requireContext(), R.layout.simple_spinner_dropdown_item, spinnerItems
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        return spinner
    }

    private fun validateFields(
        dialog: Dialog, actvProducts: AutoCompleteTextView, spinner: Spinner, etAmount: EditText
    ): Boolean {
        if (actvProducts.text.isEmpty()) {

            return false
        }
        if (etAmount.text.isEmpty()) {

            return false
        }
        return true
    }
}