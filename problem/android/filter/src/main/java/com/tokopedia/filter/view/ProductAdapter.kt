package com.tokopedia.filter.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokopedia.filter.R
import com.tokopedia.filter.view.UtilityHelper.formatPrice
import com.tokopedia.filter.view.pojo.Product
import kotlinx.android.synthetic.main.main_item.view.*

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var data = mutableListOf<Product>()

    fun updateData(newData: List<Product>) {
        data.let {
            it.clear()
            it.addAll(newData)
        }
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.apply {
                Glide.with(this).load(product.imageUrl).into(iv_item_image)
                tv_item_name?.text = product.name
                tv_item_price?.text = product.priceInt.formatPrice()
                tv_shop_loc?.text = product.shop.city
                if (product.discountPercentage == 0) {
                    tv_item_discount?.visibility = View.GONE
                } else {
                    val discText = "${product.discountPercentage} Off!"
                    tv_item_discount?.apply {
                        visibility = View.VISIBLE
                        text = discText
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}