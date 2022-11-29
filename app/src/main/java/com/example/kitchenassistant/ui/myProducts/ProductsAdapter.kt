package com.example.kitchenassistant.ui.myProducts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenassistant.R
import com.example.kitchenassistant.ui.Product
import com.example.kitchenassistant.ui.allProds

class ProductsAdapter(
    var products: MutableList<Product>
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView = itemView.findViewById(R.id.tv_productTitle)
        val tv_quantity: TextView = itemView.findViewById(R.id.tv_productQuantity)
        val bt_editQuantity: ImageView = itemView.findViewById(R.id.iv_editQuantity)
        val iv_deleteProduct: ImageView = itemView.findViewById(R.id.iv_deleteProduct)
        val iv_img: ImageView = itemView.findViewById(R.id.iv_prodImg)
        val context = itemView.context
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
        holder.tv_quantity.text = curProduct.quantity.toString().plus(
            " ".plus(curProduct.unit.s)
        )

        val imgPath = allProds.filter { it.title.equals(curProduct.title, true) }[0].img
        val id = holder.context.resources.getIdentifier(
            imgPath, "drawable", holder.context.packageName
        )
        val img = ActivityCompat.getDrawable(holder.context, id)
        holder.iv_img.setImageDrawable(img)


        holder.iv_deleteProduct.setOnClickListener {
            this.deleteProduct(position)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setFilteredList(filteredList: MutableList<Product>) {
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
