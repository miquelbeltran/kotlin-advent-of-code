package common

import java.io.File


interface Day {

    fun part1(input: String) = part1(listOf(input))
    fun part2(input: String) = part2(listOf(input))

    fun part1(input: List<String>): String
    fun part2(input: List<String>): String

    fun solve() {
        val input = File("/Users/miquel/Downloads/input.txt").readLines()
        println(part1(input))
        println(part2(input))
    }
}

