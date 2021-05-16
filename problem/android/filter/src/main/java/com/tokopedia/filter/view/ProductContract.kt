package com.tokopedia.filter.view

import com.tokopedia.filter.view.pojo.Product
import java.util.ArrayList

interface ProductContract {

    interface View {
        fun setData(listProduct: List<Product>)

        fun onTopTwoShopLocationReceived(locations: List<String>)
    }

    interface Presenter {
        fun loadData()

        fun getTopTwoShopLocation()

        fun getFilteredData(filterList: List<String>, minPrice: Int, maxPrice: Int)
    }

}