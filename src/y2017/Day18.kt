package y2017

import java.io.File

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt").readLines()
    println(solveDay18Part1(input))
}

fun solveDay18Part1(input: List<String>): Long {
    val program = parseProgram(input)
    val memory = Memory()
    while (!memory.recovered) {
        program[memory.pos.toInt()](memory)
        println(memory)
    }
    return memory.played
}

private fun parseProgram(input: List<String>): List<Inst18> {
    return input.map {
        val parts = it.split(" ")
        when (parts[0]) {
            "snd" -> Inst18.snd(parts[1])
            "set" -> Inst18.set(parts[1], parts[2])
            "add" -> Inst18.add(parts[1], parts[2])
            "mul" -> Inst18.mul(parts[1], parts[2])
            "mod" -> Inst18.mod(parts[1], parts[2])
            "rcv" -> Inst18.rcv(parts[1])
            "jgz" -> Inst18.jgz(parts[1], parts[2])
            else -> throw IllegalArgumentException("Unknown $it")
        }
    }
}

internal data class Memory(
        var pos: Long = 0,
        var played: Long = 0,
        val map: MutableMap<String, Long> = mutableMapOf(),
        var recovered: Boolean = false
)

internal sealed class Inst18 {

    //    snd X plays a sound with a frequency equal to the value of X.
    class snd(val register: String) : Inst18() {
        override fun invoke(memory: Memory) {
            memory.pos++
            memory.played = get(memory.map, register)
            println("play: ${memory.played} ")
        }
    }

    //    set X Y sets register X to the value of Y.
    class set(val register: String, val value: String) : Inst18() {
        override fun invoke(memory: Memory) {
            println("set: $register ${get(memory.map, value)}")
            memory.map[register] = get(memory.map, value)
            memory.pos++
        }
    }

    //    add X Y increases register X by the value of Y.
    class add(val register: String, val value: String) : Inst18() {
        override fun invoke(memory: Memory) {
            println("add: $register ${get(memory.map, value)}")
            memory.map[register] = (memory.map[register] ?: 0) + get(memory.map, value)
            memory.pos++
        }
    }

    //    mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
    class mul(val register: String, val value: String) : Inst18() {
        override fun invoke(memory: Memory) {
            println("mul: $register ${get(memory.map, value)}")
            memory.map[register] = (memory.map[register] ?: 0) * get(memory.map, value)
            memory.pos++
        }
    }

    //    mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
    class mod(val register: String, val value: String) : Inst18() {
        override fun invoke(memory: Memory) {
            println("mod: $register ${get(memory.map, value)}")
            memory.map[register] = (memory.map[register] ?: 0) % get(memory.map, value)
            memory.pos++
        }
    }

    //    rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
    class rcv(val register: String) : Inst18() {
        override fun invoke(memory: Memory) {
            println(memory.map)
            println("rcv: $register")
            if (memory.map[register] != 0L) {
                memory.recovered = true
            }
            memory.pos++
        }
    }

    //    jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous instruction, and so on.)
    class jgz(val register: String, val value: String) : Inst18() {
        override fun invoke(memory: Memory) {
            println("jgz: $register ${get(memory.map, value)}")
            val x = get(memory.map, register)
            val offset = get(memory.map, value)
            if (x > 0) {
                memory.pos += offset
            } else {
                memory.pos++
            }
        }
    }

    abstract operator fun invoke(memory: Memory)

    internal fun get(map: Map<String, Long>, value: String): Long {
        try {
            return value.toLong()
        } catch (e: Exception) {
            return map[value] ?: 0
        }
    }

}
