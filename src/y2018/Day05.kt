package y2018

import common.Day
import kotlin.math.min

fun main() {
    Day05.solve()
}

object Day05: Day {
    override fun part1(input: List<String>): String {
        var s = input.first()
        var newS = ""
        while (true) {
            newS = runOperation(s)
            if (newS == s) {
//                println(s)
                return s.length.toString()
            }
//            println(newS.length)
            s = newS
        }
    }

    private fun runOperation(s: String): String {
        val len = s.length
        for (index in 0 until s.length - 1) {
            val c1 = s[index]
            val c2 = s[index + 1]
//            println("c1: $c1, c2: $c2")
            if ((c1.isUpperCase() && c2.isLowerCase()) ||
                    (c1.isLowerCase() && c2.isUpperCase())) {
//                println("up and down")
                if (c1.toLowerCase() == c2.toLowerCase()) {
//                    println("$c1 == $c2")
                    val head = if (index > 0) {
                        s.substring(0 until index)
                    } else {
                        ""
                    }
                    val newS = head + s.substring(index + 2)
                    if (len - 2 != newS.length) {
                        error("NOPE")
                    }
//                    println(newS)
                    return newS
                }
            }
        }
        return s
    }

    override fun part2(input: List<String>): String {
        val s = input.first()
        var num = Int.MAX_VALUE
        for (c in 'a' .. 'z') {
            val newS = s.replace(c.toString(), "").replace(c.toUpperCase().toString(), "")
            num = min(num, part1(newS).toInt())
        }
        return num.toString()
    }

}