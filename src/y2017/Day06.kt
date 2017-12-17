package y2017

import java.io.File

fun solveDay6(input: List<Int>): Int {
    val list = input.toMutableList()
    val history = mutableListOf<List<Int>>()

    while (!history.contains(list)) {
        history.add(list.toList())
        reallocate(list)
    }

    println("Diff: ${history.size - history.indexOf(list)}")
    return history.size
}

fun reallocate(list: MutableList<Int>) {
    var max = list.max() ?: 0
    var index = list.indexOf(max)
    list[index] = 0
    while (max > 0) {
        index = (index + 1) % list.size
        list[index]++
        max--
    }
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
            .split("\t")
            .map { it.toInt() }
    println(solveDay6(input))
}
