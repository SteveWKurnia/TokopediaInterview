package com.tokopedia.filter.view.pojo

import com.google.gson.annotations.SerializedName

data class ProductWrapper(
        @SerializedName("products")
        var productList: List<Product>
)
