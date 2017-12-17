package y2017

import java.io.File

fun solveDay8(lines: List<String>): Int {
    val register = mutableMapOf<String, Int>()
    val instruction = lines.map(::parseInstruction)
    var max = Integer.MIN_VALUE
    instruction.forEach {
        val toCompare = register[it.conditionRegister] ?: 0
        val out = it.condition(toCompare, it.conditionValue)
        if (out) {
            register[it.register] = it.operation((register[it.register] ?: 0), it.value)
        }
        println(register)
        max = Math.max(max, register.values.max() ?: max)
    }
    println("All time max: $max")
    return register.values.max()!!
}

enum class Operation {
    INC, DEC
}

operator fun Operation.invoke(a: Int, b: Int): Int {
    return when (this) {
        Operation.INC -> a + b
        Operation.DEC -> a - b
    }
}

fun String.toOperation(): Operation {
    return when (this) {
        "inc" -> Operation.INC
        "dec" -> Operation.DEC
        else -> throw IllegalArgumentException("Unknown $this")
    }
}

enum class Comparator {
    GREATER, LESS, GREATER_EQUAL, EQUAL, LESS_EQUAL, DIFF
}

operator fun Comparator.invoke(toCompare: Int, conditionValue: Int): Boolean {
    return when (this) {
        Comparator.GREATER -> toCompare > conditionValue
        Comparator.LESS -> toCompare < conditionValue
        Comparator.GREATER_EQUAL -> toCompare >= conditionValue
        Comparator.EQUAL -> toCompare == conditionValue
        Comparator.LESS_EQUAL -> toCompare <= conditionValue
        Comparator.DIFF -> toCompare != conditionValue
    }
}

fun String.toComparator(): Comparator {
    return when (this) {
        ">" -> Comparator.GREATER
        "<" -> Comparator.LESS
        ">=" -> Comparator.GREATER_EQUAL
        "==" -> Comparator.EQUAL
        "<=" -> Comparator.LESS_EQUAL
        "!=" -> Comparator.DIFF
        else -> throw IllegalArgumentException("Unknown $this")
    }
}

data class Instruction(
        val register: String,
        val operation: Operation,
        val value: Int,
        val conditionRegister: String,
        val condition: Comparator,
        val conditionValue: Int
)

fun parseInstruction(line: String): Instruction {
    val split = line.split(" ")
    return Instruction(
            register = split[0],
            operation = split[1].toOperation(),
            value = split[2].toInt(),
            conditionRegister = split[4],
            condition = split[5].toComparator(),
            conditionValue = split[6].toInt()
    )
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .filter { it.isNotEmpty() }
    println(solveDay8(input))
}