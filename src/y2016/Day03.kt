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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
