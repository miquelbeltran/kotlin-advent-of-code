package y2017

import java.io.File

fun List<String>.linesToMap(): Map<Int, Int> {
    return map { it.split(": ") }.associateBy({ it[0].toInt() }, { it[1].toInt() })
}

fun isDetected(delay: Int, layer: Int, map: Map<Int, Int>): Boolean {
    val depth = map[layer]!!
    return (layer + delay) % ((depth - 1)*2) == 0
}

fun solveDay13Part1(lines: List<String>): Int {
    val map = lines.linesToMap()
    var security = 0
    map.forEach { layer, depth ->
        if (isDetected(0, layer, map)) {
            println("l: $layer d: $depth")
            security += layer * depth
        }
    }
    return security
}

fun solveDay13Part2(lines: List<String>): Int {
    val map = lines.linesToMap()
    var delay = 0
    while (true) {
        if (checkIfDetected(map, delay)) {
            delay++
        } else {
            return delay
        }
    }
}

private fun checkIfDetected(map: Map<Int, Int>, delay: Int): Boolean {
    for ((layer, depth) in map)
        if (isDetected(delay, layer, map)) {
            println("! at $layer with $delay")
            return true
        }
    return false
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt").readLines()
    println(solveDay13Part1(input))
    println(solveDay13Part2(input))
}
