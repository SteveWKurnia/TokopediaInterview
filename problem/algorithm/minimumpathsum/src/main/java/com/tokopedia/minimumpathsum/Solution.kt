package com.tokopedia.minimumpathsum

object Solution {

    data class Node (
        var x: Int,
        var y: Int,
        var weight: Int
    )

    fun minimumPathSum(matrix: Array<IntArray>): Int {
        val visited = Array(matrix.size) { IntArray(matrix[0].size) }

        var solution = 0

        val ySize = matrix.size
        val xSize = matrix[0].size

        val startX = 0
        val startY = 0

        val endX = matrix[0].size-1
        val endY = matrix.size-1

        val xDir = intArrayOf(-1, 0, 1, 0)
        val yDir = intArrayOf(0, -1, 0, 1)

        visited[startY][startX] = 1

        val list = mutableListOf<Node>()
        list.add(Node(startX, startY, matrix[startY][startX]))

        while (list.isNotEmpty()) {
            val currNode = list[0]
            list.remove(currNode)
            for (i in 0 until 4) {
                val newX = currNode.x + xDir[i]
                val newY = currNode.y + yDir[i]
                if (newX < 0 || newX >= xSize || newY < 0 || newY >= ySize) continue
                if (visited[newY][newX] == 1) continue
                if (newY == endY && newX == endX) {
                    solution = currNode.weight + matrix[newY][newX]
                    break
                }
                list.add(Node(newX, newY, currNode.weight + matrix[newY][newX]))
                visited[newY][newX] = 1
            }
            list.sortBy { it.weight }
        }

        return solution
    }

}
