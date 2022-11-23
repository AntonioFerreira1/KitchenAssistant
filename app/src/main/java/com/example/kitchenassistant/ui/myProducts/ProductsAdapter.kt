package com.example.kitchenassistant.ui.myProducts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.R
import com.example.kitchenassistant.ui.Product

class ProductsAdapter(
    var products: MutableList<Product>
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView = itemView.findViewById<TextView>(R.id.tv_productTitle)
        val tv_quantity: TextView = itemView.findViewById<TextView>(R.id.tv_productQuantity)
        //val bt_editQuantity = itemView.findViewById<Button>(R.id.bt_editQuantity)
        val bt_deleteProduct = itemView.findViewById<Button>(R.id.bt_deleteProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val curProduct = products[position]
        holder.tv_title.text = curProduct.title
        holder.tv_quantity.text = curProduct.quantity.toString()

        holder.bt_deleteProduct.setOnClickListener {
            this.deleteProduct(position)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setFilteredList(filteredList: MutableList<Product>){
        this.products = filteredList
        notifyDataSetChanged()
    }

    fun addProduct(product: Product) {
        products.add(product)
        notifyItemInserted(products.size - 1)
    }

    fun deleteProduct(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
    }


}
