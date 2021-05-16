package com.tokopedia.filter.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.tokopedia.filter.R
import com.tokopedia.filter.view.pojo.Data
import com.tokopedia.filter.view.pojo.Product
import kotlinx.android.synthetic.main.activity_product.*
import java.io.IOException
import java.util.*

class ProductActivity : AppCompatActivity(), FilterDialogFragment.FilterInteraction, ProductContract.View {

    private lateinit var presenter: ProductPresenter

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        presenter = ProductPresenter(this.applicationContext, this)
    }

    override fun setData(listProduct: List<Product>) {
        adapter.updateData(listProduct)
    }

    override fun onTopTwoShopLocationReceived(locations: List<String>) {
        setupOpenFilterDialogFragment(locations)
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        setupPresenter()
    }

    private fun setupPresenter() {
        presenter.loadData()
        presenter.getTopTwoShopLocation()
    }

    private fun setupOpenFilterDialogFragment(locations: List<String>) {
        fab_filter?.setOnClickListener {
            FilterDialogFragment.show(supportFragmentManager, locations[0], locations[1])
        }
    }

    private fun setupRecycler() {
        adapter = ProductAdapter()
        product_list?.apply {
            adapter = this@ProductActivity.adapter
            layoutManager = GridLayoutManager(context, 2,GridLayoutManager.VERTICAL,false)
        }
    }

    override fun onSave(bundle: Bundle) {
        val filterList = bundle.getStringArrayList("checked_chips").orEmpty()
        val minPrice = bundle.getInt("min_price")
        val maxPrice = bundle.getInt("max_price")

        presenter.getFilteredData(filterList, minPrice, maxPrice)
    }

}