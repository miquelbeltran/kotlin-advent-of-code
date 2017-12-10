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

fun solveDay10Part2(list: MutableList<Int>, lengths: String): String {
    var pos = 0
    var skip = 0

    val lengthsChars: List<Int> = lengths.map { it.toInt() } + arrayOf(17, 31, 73, 47, 23)

    println(lengthsChars)

    repeat(64) {
        lengthsChars.forEach {
            val to = pos + it
            val reversed = mutableListOf<Int>()
            for (it in pos until to) {
                reversed.add(list[it % list.size])
            }
            reversed.reverse()
            var revIdx = 0
            for (i in pos until to) {
                list[i % list.size] = reversed[revIdx]
                revIdx++
            }
            pos += it + skip
            skip++
        }
    }
    val out = calculateXor(list)
    return out.map { String.format("%02X", it) }.joinToString("").toLowerCase()
}

fun calculateXor(list: MutableList<Int>): List<Int> {
    return list.chunked(16)
            .map {
                it.fold(0) { acc, i -> acc xor i }
            }
}

fun main(args: Array<String>) {
    val list = (0 until 256).toMutableList()
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
    println(solveDay10Part2(list, input))
}
