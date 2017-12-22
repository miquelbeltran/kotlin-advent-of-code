package y2017

import common.Day

typealias Dir = Pair<Int, Int>
typealias Pos = Pair<Int, Int>

fun main(args: Array<String>) {
    Day22.solve()
}

object Day22 : Day {

    const val INFECTED = '#'
    const val CLEAN = '.'
    const val WEAK = 'W'
    const val FLAG = 'F'


    val UP = Dir(-1, 0)
    val DOWN = Dir(1, 0)
    val LEFT = Dir(0, -1)
    val RIGHT = Dir(0, 1)

    var bursts = 10_000

    override fun part1(input: List<String>): String {
        var dir = UP
        val map = mutableMapOf<Pos, Char>()
        loadMap(map, input)
        var pos = Pos(input.size / 2, input.size / 2)
        var count = 0
        println(pos)
        repeat(bursts) {
            when (map[pos]) {
                INFECTED -> {
                    dir = right(dir)
                    map[pos] = CLEAN
                }
                else -> {
                    dir = left(dir)
                    map[pos] = INFECTED
                    count++
                }
            }
            pos += dir
        }
        return count.toString()
    }

    private fun loadMap(map: MutableMap<Pos, Char>, input: List<String>) {
        val size = input.size
        for (i in 0 until size)
            for (j in 0 until size) {
                map[Pos(i, j)] = input[i][j]
            }
    }

    private fun left(dir: Dir): Dir {
        return when (dir) {
            UP -> LEFT
            LEFT -> DOWN
            DOWN -> RIGHT
            RIGHT -> UP
            else -> throw IllegalStateException("Unknown $dir")
        }
    }

    private fun right(dir: Dir): Dir {
        return when (dir) {
            UP -> RIGHT
            LEFT -> UP
            DOWN -> LEFT
            RIGHT -> DOWN
            else -> throw IllegalStateException("Unknown $dir")
        }
    }

    private operator fun Pos.plus(dir: Dir): Pos {
        return Pos(first + dir.first, second + dir.second)
    }

    override fun part2(input: List<String>): String {
        var dir = UP
        val map = mutableMapOf<Pos, Char>()
        loadMap(map, input)
        var pos = Pos(input.size / 2, input.size / 2)
        var count = 0

        repeat(10_000_000) {
            when (map[pos]) {
                INFECTED -> {
                    dir = right(dir)
                    map[pos] = FLAG
                }
                WEAK -> {
                    map[pos] = INFECTED
                    count++
                }
                FLAG -> {
                    dir = dir.reverse()
                    map[pos] = CLEAN
                }
                else -> {
                    dir = left(dir)
                    map[pos] = WEAK
                }
            }
            pos += dir
        }

        return count.toString()
    }

    private fun Dir.reverse(): Dir {
        return Dir(first * -1, second * -1)
    }
}

