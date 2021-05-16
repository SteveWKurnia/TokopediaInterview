package com.tokopedia.filter.view.pojo

data class Product(
        var id: String,
        var name: String,
        var imageUrl: String,
        var priceInt: Int,
        var discountPercentage: Int,
        var slashedPriceInt: Int,
        var shop: Shop
)
