package y2018

import common.Day

fun main(args: Array<String>) {
    Day01.solve()
}

object Day01 : Day {
    override fun part1(input: List<String>): String {
        return input.map { it.toInt() }.sum().toString()
    }

    override fun part2(input: List<String>): String {
        val set = mutableSetOf<Int>(0)

        var initial = 0
        while (true) {
            initial = input.fold(initial) { acc, item ->

                val out = item.toInt() + acc

                if (set.contains(out))
                    return out.toString()
                set.add(out)

                out
            }
        }
    }

}