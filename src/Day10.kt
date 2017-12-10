import java.io.File

fun solveDay10(list: MutableList<Int>, lengths: List<Int>): Int {
    var pos = 0
    var skip = 0

    lengths.forEach {
        val to = pos + it
        println("to: $to")
        // reverse the list from pos - to
        val reversed = mutableListOf<Int>()
        for (it in pos until to) {
            reversed.add(list[it % list.size])
        }
        reversed.reverse()
        println("reversed: $reversed")
        var revIdx = 0
        for (i in pos until to) {
            list[i % list.size] = reversed[revIdx]
            revIdx++
        }
        println("list: $list")
        pos += it + skip
        skip++
    }

    return list[0] * list[1]
}

fun main(args: Array<String>) {
    val list = (0 until 256).toMutableList()
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
            .split(",")
            .map { it.toInt() }
    println(solveDay10(list, input))
}
