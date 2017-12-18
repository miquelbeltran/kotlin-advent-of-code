package y2017

import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay18 {
    @Test
    fun `Day 18 Part 1`() {
        val input = """set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2"""
        assertEquals(4, solveDay18Part1(input.lines()))
    }
}

