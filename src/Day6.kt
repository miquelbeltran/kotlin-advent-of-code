import java.io.File

fun solveDay6(input: List<Int>): Int {
    val list = input.toMutableList()
    val set = mutableSetOf<List<Int>>()
    var steps = 0

    while (exitCondition(list, set)) {
        set.add(list)
        reallocate(list)
        steps++
        println(list)
        println(steps)
    }
    return steps
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

fun exitCondition(list: List<Int>, set: Set<List<Int>>): Boolean {
    return !set.contains(list)
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
            .split("\t")
            .map { it.toInt() }
    println(solveDay6(input))
}
