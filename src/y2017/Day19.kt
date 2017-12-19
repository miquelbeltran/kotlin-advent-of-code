package y2017

import common.Day

fun main(args: Array<String>) {
    Day19.solve()
}

object Day19: Day {

    const val V = '|'
    const val H = '-'
    const val X = '+'
    const val EMPTY = ' '
    const val AT = '@'

    sealed class Dir(val first: Int, val second: Int) {
        object Up: Dir(-1, 0)
        object Down: Dir(1, 0)
        object Left: Dir(0, -1)
        object Right: Dir(0, 1)
    }

    override fun part1(input: List<String>): String {
        var row = 0
        var col = input[0].indexOf('|')
        var dir: Dir = Dir.Down
        var out = ""
        var steps = 0
        while (true) {

            do {
                out = updateOutput(input, row, col, out)

                row += dir.first
                col += dir.second
                steps++

                if (input[row][col] == EMPTY) {
                    println(steps)
                    return out
                }
            } while (notInCross(input, row, col))

            dir = newDirection(dir, input, row, col)
        }
    }

    private fun updateOutput(input: List<String>, row: Int, col: Int, out: String): String {
        return if (foundChar(input, row, col)) {
             out + input[row][col]
        } else out
    }

    private fun newDirection(dir: Dir, input: List<String>, row: Int, col: Int): Dir {
        return when (dir) {
            Dir.Up, Dir.Down -> leftOrRight(input, row, col)
            Dir.Left, Dir.Right -> upOrDown(input, row, col)
        }
    }

    private fun upOrDown(input: List<String>, row: Int, col: Int) =
            if (nextDirIs(input, row + 1, col)) Dir.Down else Dir.Up

    private fun leftOrRight(input: List<String>, row: Int, col: Int) =
            if (nextDirIs(input, row, col + 1)) Dir.Right else Dir.Left

    private fun foundChar(input: List<String>, row: Int, col: Int) =
            input[row][col] !in listOf(V, H, X, EMPTY)

    private fun notInCross(input: List<String>, row: Int, col: Int) =
            input[row][col] != X

    private fun nextDirIs(input: List<String>, row: Int, col: Int): Boolean {
        if (row >= input.size) return false
        if (col >= input[row].length) return false
        return input[row][col] != EMPTY
    }

    override fun part2(input: List<String>): String {
        // solved in Part 1
        return ""
    }

}