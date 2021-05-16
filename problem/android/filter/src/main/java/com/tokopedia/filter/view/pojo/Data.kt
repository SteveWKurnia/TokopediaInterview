package com.tokopedia.filter.view.pojo

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("data")
        var products: ProductWrapper
)
