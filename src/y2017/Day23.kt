package y2017

import common.Day

fun main(args: Array<String>) {
    Day23.solve()
}

object Day23 : Day {
    override fun part1(input: List<String>): String {
        val memory = Memory()
        runProgram(input, memory)
        return memory.mulCount.toString()
    }

    private fun runProgram(input: List<String>, memory: Memory) {
        val program = parseProgram(input).toMutableList()
        do {
            program[memory.pos.toInt()](memory)
        } while (memory.pos < program.size)
    }

    // In this solution I translate the input to code, then investigate a possible optimization
    override fun part2(input: List<String>): String {
        var b : Long = 0L
        var c : Long = 0L
        var d : Long = 0L
        var e : Long = 0L
        var f : Boolean = true
        var h : Long = 0L
//set b 57
//set c b
//jnz a 2
//jnz 1 5
//mul b 100
//sub b -100000
        b = 105_700L
//set c b
//sub c -17000
        c = 122_700L
        do {
//set f 1
            f = false
//set d 2
            d = 2L

            // Optimized solution copied from Reddit
            // finishes in seconds
            while (d < b) {
                if (b % d == 0L) {
                    f = true
                    break
                }
                d++
            }


// Slow original loop code, the break loop helps to speed up things
            // takes like 15 minutes to complete :-(
//            loop@ while (d != b) {
////set e 2
//                e = 2L
//
//                while (e != b) {
////set g d
////mul g e
////sub g b
////jnz g 2
//                    if (d * e == b){
////set f 0
//                        f = true
//                        break@loop
//                    }
////sub e -1
//                    e++
////set g e
////sub g b
////jnz g -8
//                }
////sub d -1
//                d++
////set g d
////sub g b
////jnz g -13
//            }

//jnz f 2
            if (f) {
//sub h -1
                h++
            }
//set g b
//sub g c
//jnz g 2
            if (b == c) {
//jnz 1 3
                return h.toString()
            }
//sub b -17
            b += 17L
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