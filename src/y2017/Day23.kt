package y2017

import common.Day

fun main(args: Array<String>) {
    Day23.solve()
}

object Day23 : Day {
    override fun part1(input: List<String>): String {
        return ""
        val memory = Memory()
        runProgram(input, memory)
        return memory.mulCount.toString()
    }

    private fun runProgram(input: List<String>, memory: Memory) {
        val program = parseProgram(input).toMutableList()
        do {
            program[memory.pos.toInt()](memory)
            println(memory)
        } while (memory.pos < program.size)
    }

    override fun part2(input: List<String>): String {
        var a = 0
        var b = 0
        var c = 0
        var d = 0
        var e = 0
        var f = 0
        var g = 0
        var h = 0
//set b 57
        b = 57
//set c b
//jnz a 2
//jnz 1 5
//mul b 100
        b *= 100
//sub b -100000
        b -= -100_000
//set c b
        c = b
//sub c -17000
        c -= -17_000
        do {
//set f 1
            f = 1
//set d 2
            d = 2
            do {
//set e 2
                e = 2
                do {
//set g d
                    g = d
//mul g e
                    g *= e
//sub g b
                    g -= b
//jnz g 2
                    if (g == 0) {
//set f 0
                        f = 0
                    }
//sub e -1
                    e -= 1
//set g e
                    g = e
//sub g b
                    g -= b
//jnz g -8
                } while (g != 0)
//sub d -1
                d -= -1
//set g d
                g = d
//sub g b
                g -= b
//jnz g -13
            } while (g != 0)
//jnz f 2
            if (f == 0) {
//sub h -1
                h -= -1
            }
//set g b
            g = b
//sub g c
            g -= c
//jnz g 2
            if (g == 0) {
//jnz 1 3
                return h.toString()
            }
//sub b -17
            b -= 17
//jnz 1 -23
        } while (true)
    }

    private fun parseProgram(input: List<String>): List<Inst23> {
        return input.map {
            val parts = it.split(" ")
            when (parts[0]) {
                "set" -> Inst23.set(parts[1], parts[2])
                "sub" -> Inst23.sub(parts[1], parts[2])
                "mul" -> Inst23.mul(parts[1], parts[2])
                "jnz" -> Inst23.jnz(parts[1], parts[2])
                else -> throw IllegalArgumentException("Unknown $it")
            }
        }
    }

    internal data class Memory(
            var pos: Long = 0,
            val map: MutableMap<String, Long> = mutableMapOf(),
            var mulCount: Int = 0
    )

    internal sealed class Inst23 {

        //    set X Y sets register X to the value of Y.
        class set(val register: String, val value: String) : Inst23() {
            override fun invoke(memory: Memory) {
//                println("set: $register ${get(memory.map, value)}")
                memory.map[register] = get(memory.map, value)
                memory.pos++
            }
        }

        //    sub X Y decreases register X by the value of Y.
        class sub(val register: String, val value: String) : Inst23() {
            override fun invoke(memory: Memory) {
//                println("sub: $register ${get(memory.map, value)}")
                memory.map[register] = (memory.map[register] ?: 0) - get(memory.map, value)
                memory.pos++
            }
        }

        //    mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
        class mul(val register: String, val value: String) : Inst23() {
            override fun invoke(memory: Memory) {
//                println("mul: $register ${get(memory.map, value)}")
                memory.map[register] = (memory.map[register] ?: 0) * get(memory.map, value)
                memory.pos++
                memory.mulCount++
            }
        }

        class jnz(val register: String, val value: String) : Inst23() {
            override fun invoke(memory: Memory) {
//                println("jnz: $register ${get(memory.map, value)}")
                val x = get(memory.map, register)
                val offset = get(memory.map, value)
                if (x != 0L) {
                    memory.pos += offset
                } else {
                    memory.pos++
                }
            }
        }

        abstract operator fun invoke(memory: Memory)

        internal fun get(map: Map<String, Long>, value: String): Long {
            return value.toLongOrNull() ?: map[value] ?: 0
        }

    }
}