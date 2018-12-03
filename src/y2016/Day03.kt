package y2016

import common.Day

fun main(args: Array<String>) {
    Day03.solve()
}

object Day03 : Day {
    override fun part1(input: List<String>): String {
        return input.map {
            val sides = it.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            if (sides.none { it.toDouble() >= sides.sum().toDouble() / 2.toDouble() }) 1 else 0
        }.sum().toString()
    }

    override fun part2(input: List<String>): String {
        var counter = 0
        val parsed = input.map {
            it.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        }
        for (index in 0 until parsed.size step 3) {
            val triangle0 = listOf(parsed[index][0], parsed[index + 1][0], parsed[index + 2][0])
            val triangle1 = listOf(parsed[index][1], parsed[index + 1][1], parsed[index + 2][1])
            val triangle2 = listOf(parsed[index][2], parsed[index + 1][2], parsed[index + 2][2])
            counter += if (triangle0.none { it.toDouble() >= triangle0.sum().toDouble() / 2.toDouble() }) 1 else 0
            counter += if (triangle1.none { it.toDouble() >= triangle1.sum().toDouble() / 2.toDouble() }) 1 else 0
            counter += if (triangle2.none { it.toDouble() >= triangle2.sum().toDouble() / 2.toDouble() }) 1 else 0
        }
        return counter.toString()
    }

}
