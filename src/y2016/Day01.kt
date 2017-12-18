package y2016

import common.Day
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    Day01.solve()
}

object Day01 : Day {

    val directions = arrayOf(
            Pair(0, 1),
            Pair(-1, 0),
            Pair(0, -1),
            Pair(1, 0)
    )

    override fun part1(input: List<String>): String {
        val moves = input.first().split(", ")
        var currentDir = 0
        return moves.fold(Pair(0, 0)) { (x, y), move ->
            currentDir = calculateNextDirection(move, currentDir)
            val times = move.drop(1).toInt()
            Pair(x + directions[currentDir].first * times, y + directions[currentDir].second * times)
        }.toAbsolute()
    }

    private fun Pair<Int, Int>.toAbsolute() = toList()
            .map { it.absoluteValue }
            .sum()
            .toString()

    private fun calculateNextDirection(move: String, currentDir: Int): Int {
        var currentDir1 = currentDir
        when (move[0]) {
            'R' -> currentDir1++
            'L' -> currentDir1--
        }
        if (currentDir1 >= 4) {
            currentDir1 -= 4
        } else if (currentDir1 < 0) {
            currentDir1 += 4
        }
        return currentDir1
    }

    override fun part2(input: List<String>): String {
        val moves = input.first().split(", ")
        var currentDir = 0
        val locations = mutableListOf<Pair<Int, Int>>(Pair(0, 0))
        for (move in moves) {
            currentDir = calculateNextDirection(move, currentDir)
            val times = move.drop(1).toInt()
            repeat(times) {
                val (x, y) = locations.last()
                val newloc = Pair(x + directions[currentDir].first, y + directions[currentDir].second)
                if (locations.contains(newloc)) {
                    return newloc.toAbsolute()
                }
                locations.add(newloc)
            }
        }
        return ""
    }

}