package y2017

import java.io.File

fun solveDay9(input: String): Pair<Int, Int> {
    var score = 0
    var openGroup = 0
    var garbage = false
    var garbageCount = 0

    val it = input.iterator()
    while (it.hasNext()) {
        val char = it.nextChar()
        when {
            garbage -> when (char) {
                '>' -> garbage = false
                '!' -> it.nextChar()
                else -> garbageCount++
            }
            else -> when (char) {
                '{' -> openGroup++
                '}' -> score += openGroup--
                '!' -> it.nextChar()
                '<' -> garbage = true
            }
        }
    }

    return Pair(score, garbageCount)
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
    println(solveDay9(input))
}
