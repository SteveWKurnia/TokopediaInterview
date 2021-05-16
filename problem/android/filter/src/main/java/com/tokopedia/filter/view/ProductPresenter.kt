package com.tokopedia.filter.view

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.tokopedia.filter.R
import com.tokopedia.filter.view.pojo.Data
import com.tokopedia.filter.view.pojo.Product
import java.io.IOException

class ProductPresenter constructor(
        private val context: Context,
        private val view: ProductContract.View
): ProductContract.Presenter {

    override fun getFilteredData(filterList: List<String>, minPrice: Int, maxPrice: Int) {
        val productList = parseJSON(readJSON())
        val filteredProductList = mutableListOf<Product>()
        val topTwoShop = getTopTwoLocal()
        val tempList = mutableListOf<Product>()

        if (!filterList.isNullOrEmpty()) {
            Log.d("testing123", "All filter")
            for (filter in filterList) {
                if (filter == "Other") {
                    filteredProductList.addAll(productList.filter { it.shop.city != topTwoShop[0] && it.shop.city != topTwoShop[1] })
                } else {
                    filteredProductList.addAll(productList.filter { it.shop.city == filter })
                }
            }
            tempList.addAll(filteredProductList.filter { it.priceInt in minPrice until maxPrice })
        } else if (filterList.isNullOrEmpty()){
            Log.d("testing123", "Price filter")
            tempList.addAll(productList.filter { it.priceInt in minPrice until maxPrice })
        }
        view.setData(tempList)
    }

    override fun loadData() {
        view.setData(parseJSON(readJSON()))
    }

    override fun getTopTwoShopLocation() {
        view.onTopTwoShopLocationReceived(getTopTwoLocal())
    }

    private fun getTopTwoLocal(): List<String> {
        val productList = parseJSON(readJSON())
        val hashTable = mutableMapOf<String, Int?>()
        for (element in productList) {
            val value = hashTable[element.shop.city] ?: 0
            hashTable[element.shop.city] = value + 1
        }
        val sortedList = hashTable.toSortedMap(compareByDescending { hashTable[it] }).toList()
        return listOf(sortedList[0].first, sortedList[1].first)
    }

    private fun readJSON(): String? {
        val json: String?
        try {
            val iStream = context.resources.openRawResource(R.raw.products)
            val buffer = ByteArray(iStream.available())
            iStream.use { it.read(buffer) }

            json = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }

    private fun parseJSON(json: String?): List<Product> {
        val a = Gson().fromJson(json, Data::class.java)
        val jsonData = listOf(a)

        return jsonData[0].products.productList
    }
}