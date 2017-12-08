import com.sun.org.apache.xml.internal.security.utils.ElementCheckerImpl
import org.testng.annotations.Test
import kotlin.test.assertEquals

/*
--- Day 8: I Heard You Like Registers ---

You receive a signal directly from the CPU. Because of your recent assistance with jump instructions, it would like you to compute the result of a series of unusual register instructions.

Each instruction consists of several parts: the register to modify, whether to increase or decrease that register's value, the amount by which to increase or decrease it, and a condition. If the condition fails, skip the instruction without modifying the register. The registers all start at 0. The instructions look like this:

b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10
These instructions would be processed as follows:

Because a starts at 0, it is not greater than 1, and so b is not modified.
a is increased by 1 (to 1) because b is less than 5 (it is 0).
c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
c is increased by -20 (to -10) because c is equal to 10.
After this process, the largest value in any register is 1.

You might also encounter <= (less than or equal to) or != (not equal to). However, the CPU doesn't have the bandwidth to tell you what all the registers are named, and leaves that to you to determine.
 */

class TestDay8 {

    @Test
    fun `solve day 8 part 1`() {
        val input = """b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10"""
        val lines = input.lines()
        val out = solveDay8(lines)
        assertEquals(1, out)
    }
}

fun solveDay8(lines: List<String>): Int {
    val register = mutableMapOf<String, Int>()
    val instruction = lines.map(::parseInstruction)
    instruction.forEach {
        val toCompare = register[it.conditionRegister] ?: 0
        val out = it.condition(toCompare, it.conditionValue)
        if (out) {
            register[it.register] = it.operation((register[it.register] ?: 0), it.value)
        }
        println(register)
    }
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


