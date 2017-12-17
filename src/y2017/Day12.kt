package y2017

import java.io.File

fun solveDay12(input: String): Int {
    val programs: List<List<Int>> = input.lines().parsePrograms()

    println(programs)

    val visited = visit(0, programs, emptySet())

    return visited.size
}

private fun List<String>.parsePrograms(): List<List<Int>> {
    return map {
        val splits = it.split(" ")
        splits.drop(2).map {
            it.replace(",", "").toInt()
        }
    }
}

fun visit(i: Int, programs: List<List<Int>>, visited: Set<Int>): Set<Int> {
    val linked = programs[i]
    val new = linked.subtract(visited)
    if (new.isEmpty()) {
        return visited
    } else {
        val newlyVisited: Set<Int> = new + visited
        return new.flatMap {
            visit(it, programs, newlyVisited)
        }.toSet()
    }
}

fun main(args: Array<String>) {

    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .parsePrograms()

    // Solution Part 1
    println(visit(0, input, emptySet()).size)

    // Solution Part 2
    val visited = mutableSetOf<Int>()
    var groups = 0
    for (i in 0 until input.size) {
        if (!visited.contains(i)) {
            visited.addAll(visit(i, input, visited))
            groups++
        }
    }
    println(groups)

}