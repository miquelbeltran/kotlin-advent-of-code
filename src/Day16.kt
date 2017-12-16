import java.io.File

fun solveDay16Part1(ops: String, input: String): String {
    return ops
            .split(",")
            .fold(input) { value, op ->
                println(op)
                println(value)
                consume(op, value)
            }
}

fun consume(op: String, input: String): String {
    return when (op[0]) {
        's' -> spin(op, input)
        'x' -> exchange(op, input)
        'p' -> partner(op, input)
        else -> throw IllegalArgumentException("Unknown $op")
    }
}

fun spin(op: String, input: String): String {
    val spin = op.drop(1).toInt()
    return input.takeLast(spin) + input.dropLast(spin)
}

fun exchange(op: String, input: String): String {
    val values = op.drop(1).split("/").map { it.toInt() }
    return flipCharsAt(input, values[0], values[1])
}


fun partner(op: String, input: String): String {
    val values = op.drop(1).split("/").map { it.first() }
    val posA = input.indexOf(values[0])
    val posB = input.indexOf(values[1])
    return flipCharsAt(input, posA, posB)
}

private fun flipCharsAt(input: String, posA: Int, posB: Int): String {
    val chars = input.toMutableList()
    val temp = chars[posA]
    chars[posA] = chars[posB]
    chars[posB] = temp
    return chars.joinToString(separator = "")
}

fun main(args: Array<String>) {
    val ops = File("/Users/miquel/Downloads/input.txt").readLines().first()
    val input = "abcdefghijklmnop"
    println(solveDay16Part1(ops, input))

}
