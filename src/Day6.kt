import java.io.File

fun solveDay6(input: List<Int>): Int {
    val list = input.toMutableList()
    val history = mutableListOf<List<Int>>()

    while (exitCondition(list, history)) {
        history.add(list.toList())
        reallocate(list)
        println(list)
        println(history.size)
    }
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

fun exitCondition(list: List<Int>, history: List<List<Int>>): Boolean {
    val contains = history.contains(list)
    if (contains) {
        println("Diff: ${history.size - history.indexOf(list)}")
    }
    return !contains
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
            .split("\t")
            .map { it.toInt() }
    println(solveDay6(input))
}
