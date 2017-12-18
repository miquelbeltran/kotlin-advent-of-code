package y2016

import common.Day

fun main(args: Array<String>) {
    Day02.solve()
}

object Day02 : Day {

    override fun part1(input: List<String>): String {
        val matrix = arrayOf(
                arrayOf(1, 2, 3),
                arrayOf(4, 5, 6),
                arrayOf(7, 8, 9)
        )

        var x = 1
        var y = 1

        return input.map {
            it.forEach {
                when (it) {
                    'U' -> if (y > 0) y--
                    'D' -> if (y < 2) y++
                    'L' -> if (x > 0) x--
                    'R' -> if (x < 2) x++
                }
            }
            matrix[y][x].toString()
        }.joinToString(separator = "")
    }

    override fun part2(input: List<String>): String {
        val matrix = arrayOf(
                arrayOf("X", "X", "1", "X", "X"),
                arrayOf("X", "2", "3", "4", "X"),
                arrayOf("5", "6", "7", "8", "9"),
                arrayOf("X", "A", "B", "C", "X"),
                arrayOf("X", "X", "D", "X", "X")
        )

        var x = 0
        var y = 2

        return input.map {
            it.forEach {
                when (it) {
                    'U' -> if (y > 0 && matrix[y - 1][x] != "X") y--
                    'D' -> if (y < 4 && matrix[y + 1][x] != "X") y++
                    'L' -> if (x > 0 && matrix[y][x - 1] != "X") x--
                    'R' -> if (x < 4 && matrix[y][x + 1] != "X") x++
                }
            }
            matrix[y][x].toString()
        }.joinToString(separator = "")
    }

}
