package y2017

import java.lang.Math.ceil
import java.lang.Math.sqrt

enum class Direction(val step: Int) {
    N(1), S(-1), W(-1), E(1)
}

fun solveDay3(input: Int): Int {
    var dir = Direction.E
    var current = 1
    var stepsV = 0
    var stepsH = 0
    var changeDir = 1
    var onDir = 0

    while (input != current) {
        when (dir) {
            Direction.N, Direction.S -> stepsV += dir.step
            Direction.W, Direction.E -> stepsH += dir.step
        }
        current++
        onDir++
        if (onDir == changeDir) {
            dir = nextDirection(dir)
            if (dir in listOf(Direction.W, Direction.E)) {
                changeDir++
            }
            onDir = 0
        }
    }

    return Math.abs(stepsH) + Math.abs(stepsV)
}

fun nextDirection(dir: Direction): Direction {
    return when (dir) {
        Direction.N -> Direction.W
        Direction.S -> Direction.E
        Direction.W -> Direction.S
        Direction.E -> Direction.N
    }
}

fun solveDay3Part2(input: Int): Int {
    val maxGrid = ceil(sqrt(input.toDouble())).toInt()
    val grid = MutableList(maxGrid, { MutableList(maxGrid, { 0 }) })
    var x = maxGrid / 2
    var y = maxGrid / 2
    grid[x][y] = 1

    var dir = Direction.E
    var changeDir = 1
    var onDir = 0

    while (input >= grid[x][y]) {
        when (dir) {
            Direction.N, Direction.S -> y += dir.step
            Direction.W, Direction.E -> x += dir.step
        }
        grid[x][y] = sumOfAdjacents(grid, x, y)
        onDir++
        if (onDir == changeDir) {
            dir = nextDirection(dir)
            if (dir in listOf(Direction.W, Direction.E)) {
                changeDir++
            }
            onDir = 0
        }
    }
    return grid[x][y]
}

fun sumOfAdjacents(grid: MutableList<MutableList<Int>>, x: Int, y: Int): Int {
    return grid[x - 1][y - 1] +
            grid[x][y - 1] +
            grid[x + 1][y - 1] +
            grid[x - 1][y] +
            grid[x + 1][y] +
            grid[x - 1][y + 1] +
            grid[x][y + 1] +
            grid[x + 1][y + 1]
}

fun main(args: Array<String>) {
    print(solveDay3Part2(277678))
}