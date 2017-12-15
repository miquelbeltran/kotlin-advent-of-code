const val MASK = 0x0000FFFF
const val FACTOR_A = 16807
const val FACTOR_B = 48271
const val DIVISOR = 2147483647

fun solveDay15Part1(inputA: Long, inputB: Long): Int {
    var genA = inputA
    var genB = inputB
    var count = 0
    repeat(40_000_000) {
        val a = genA and MASK.toLong()
        val b = genB and MASK.toLong()
        if (a == b) {
            count++
        }
        genA = (genA * FACTOR_A) % DIVISOR
        genB = (genB * FACTOR_B) % DIVISOR
    }
    return count
}

fun solveDay15Part2(inputA: Long, inputB: Long): Int {
    var genA = inputA
    var genB = inputB
    var count = 0
    repeat(5_000_000) {
        genA = nextMultiple(4, genA, FACTOR_A)
        genB = nextMultiple(8, genB, FACTOR_B)
        val a = genA and MASK.toLong()
        val b = genB and MASK.toLong()
        if (a == b) {
            count++
        }
    }
    return count
}

fun nextMultiple(multiple: Int, input: Long, factor: Int): Long {
    var value = (input * factor) % DIVISOR
    while (value % multiple.toLong() != 0L) {
        value = (value * factor) % DIVISOR
    }
    return value
}

fun main(args: Array<String>) {
    val genA = 634L
    val genB = 301L
    println(solveDay15Part1(genA, genB))
    println(solveDay15Part2(genA, genB))
}