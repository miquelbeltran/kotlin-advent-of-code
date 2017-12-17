package y2017
fun solveDay17Part1(steps: Int): Int {
    val list = mutableListOf(0)
    var pos = 0
    (1..2017).forEach {
        pos = ((pos + steps) % (list.size)) + 1
        list.add(pos, it)
    }
    return list[pos + 1]
}

// Assume that Zero is always at pos 0
fun solveDay17Part2(steps: Int): Int {
    var pos = 0
    var nextToZ = 0
    (1..50_000_000).forEach {
        pos = ((pos + steps) % (it)) + 1
        if (pos == 1) {
            nextToZ = it
        }
    }
    return nextToZ
}

fun main(args: Array<String>) {
    val steps = 377
    println(solveDay17Part1(377))
    println(solveDay17Part2(377))
}
