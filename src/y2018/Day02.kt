package y2018

import common.Day

fun main(args: Array<String>) {
    Day02.solve()
}

object Day02 : Day {
    override fun part1(input: List<String>): String {
        val pairs = input.map { count(it) }
        val sumA = pairs.sumBy { it.first }
        val sumB = pairs.sumBy { it.second }
        return (sumA * sumB).toString()
    }

    override fun part2(input: List<String>): String {
        // IDs have this length
        val length = input.first().length
        // For each position of the ID
        for (index in 0 until length) {
            // remove the char on that position
            val trimmedInput = input.map { it.removeRange(index, index + 1) }
            // find all ids that are repeated exactly 2 times
            val repeated = trimmedInput.groupingBy { it }.eachCount().filter { it.value == 2 }
            // if found any
            if (repeated.isNotEmpty()) {
                // return the ID
                return repeated.keys.first()
            }
        }
        error("No ID found")
    }

    fun count(input: String): Pair<Int, Int> {
        val count = input.groupingBy { it }.eachCount()
        val times2 = if (count.values.any { it == 2 }) 1 else 0
        val times3 = if (count.values.any { it == 3 }) 1 else 0
        return Pair(times2, times3)
    }
}