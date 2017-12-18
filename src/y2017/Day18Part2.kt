package y2017

import java.io.File

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt").readLines()
    println(Day18Part2.solve(input))
}

object Day18Part2 {

    fun solve(input: List<String>): Long {
        val program = parseProgram(input)
        val buffer0 = mutableListOf<Long>()
        val buffer1 = mutableListOf<Long>()
        val memory0 = Memory(sending = buffer0, receiving = buffer1)
        val memory1 = Memory(sending = buffer1, receiving = buffer0)
        memory0.map["p"] = 0
        memory1.map["p"] = 1
        while (!(memory0.deadlock && memory1.deadlock)) {
            program[memory0.pos.toInt()](memory0)
            program[memory1.pos.toInt()](memory1)
        }
        return memory1.sent
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

    private data class Memory(
            var pos: Long = 0,
            val map: MutableMap<String, Long> = mutableMapOf(),
            val sending: MutableList<Long>,
            val receiving: MutableList<Long>,
            var deadlock: Boolean = false,
            var sent: Long = 0
    )

    private sealed class Inst18 {

        // snd X sends the value of X to the other program.
        // These values wait in a queue until that program is ready to receive them.
        // Each program has its own message queue, so a program can never receive a message it sent.
        class snd(val register: String) : Inst18() {
            override fun invoke(memory: Memory) {
                memory.pos++
                val value = get(memory.map, register)
                println("send: $value ")
                memory.sending.add(value)
                memory.sent++
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

        // rcv X receives the next value and stores it in register X.
        // If no values are in the queue, the program waits for a value to be sent to it.
        // Programs do not continue to the next instruction until they have received a value.
        // Values are received in the order they are sent.
        class rcv(val register: String) : Inst18() {
            override fun invoke(memory: Memory) {
                println(memory.map)
                println("rcv: $register")
                if (memory.receiving.isEmpty()) {
                    memory.deadlock = true
                } else {
                    memory.deadlock = false
                    memory.pos++
                    memory.map[register] = memory.receiving.first()
                    memory.receiving.removeAt(0)
                }
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

}
