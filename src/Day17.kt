
fun solveDay17Part1(steps: Int): Int {
    val list = mutableListOf(0)
    var pos = 0
    (1..2017).forEach {
        pos = ((pos + steps) % (list.size)) + 1
        list.add(pos, it)
    }
    println(list.subList(pos, pos+2))
    return list[pos + 1]
}

fun main(args: Array<String>) {
    val steps = 377
    println(solveDay17Part1(377))
}
