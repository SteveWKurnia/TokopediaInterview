package com.tokopedia.climbingstairs

object Solution {
    fun climbStairs(n: Int): Long {
        var numberOfSteps: Long = 0
        var firstNumber: Long = 0
        var secondNumber: Long = 1

        for (i in 0 until n) {
            numberOfSteps = firstNumber + secondNumber
            firstNumber = secondNumber
            secondNumber = numberOfSteps
        }

        return numberOfSteps
    }
}
