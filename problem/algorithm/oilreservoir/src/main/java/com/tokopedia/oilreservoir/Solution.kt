package com.tokopedia.oilreservoir

/**
 * Created by fwidjaja on 2019-09-24.
 */
object Solution {

    fun collectOil(height: IntArray): Int {
        var totalOil = 0
        var startBoulder = 0
        var endBoulder = 0

        for (i in height.indices) {
            for (j in i downTo 0) { // Check startBoulder
                startBoulder = maxOf(startBoulder, height[j])
            }
            for (j in i until height.size-1) { // Check endBoulder
                endBoulder = maxOf(endBoulder, height[j])
            }
            // Get current oil count
            if (minOf(startBoulder,endBoulder) >= height[i]) {
                totalOil += minOf(startBoulder, endBoulder) - height[i]
            }
        }
        return totalOil
    }
}
