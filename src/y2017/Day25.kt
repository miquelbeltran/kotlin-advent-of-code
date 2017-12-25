package y2017

import common.Day

fun main(args: Array<String>) {
    Day25.solve()
}

enum class State {
    A, B, C, D, E, F
}

object Day25 : Day {
    override fun part1(input: List<String>): String {
        val tape = mutableMapOf<Int, Int>()
        tape.put(0, 0)
        var pos = 0
        var state = State.A
        repeat(12964419) {
           when (state) {
               State.A -> {
                   if (tape[pos] != 1) {
                       tape[pos] = 1
                       pos++
                       state = State.B
                   } else {
                       tape[pos] = 0
                       pos++
                       state = State.F
                   }
               }
               State.B -> {
                   if (tape[pos] != 1) {
                       tape[pos] = 0
                       pos--
                       state = State.B
                   } else {
                       tape[pos] = 1
                       pos--
                       state = State.C
                   }
               }
               State.C -> {
                   if (tape[pos] != 1) {
                       tape[pos] = 1
                       pos--
                       state = State.D
                   } else {
                       tape[pos] = 0
                       pos++
                       state = State.C
                   }
               }
               State.D -> {
                   if (tape[pos] != 1) {
                       tape[pos] = 1
                       pos--
                       state = State.E
                   } else {
                       tape[pos] = 1
                       pos++
                       state = State.A
                   }
               }
               State.E -> {
                   if (tape[pos] != 1) {
                       tape[pos] = 1
                       pos--
                       state = State.F
                   } else {
                       tape[pos] = 0
                       pos--
                       state = State.D
                   }
               }
               State.F -> {
                   if (tape[pos] != 1) {
                       tape[pos] = 1
                       pos++
                       state = State.A
                   } else {
                       tape[pos] = 0
                       pos--
                       state = State.E
                   }
               }
           }
        }
        return tape.values.count { it == 1 }.toString()
    }

    override fun part2(input: List<String>): String {
        return ""
    }
}