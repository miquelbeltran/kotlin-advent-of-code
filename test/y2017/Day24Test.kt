package y2017

import org.testng.annotations.Test

import org.testng.Assert.*

class Day24Test {
    val input = """0/2
2/2
2/3
3/4
3/5
0/1
10/1
9/10"""
    @Test
    fun testPart1() {
        assertEquals("31", Day24.part1(input.lines()))
    }

    @Test
    fun testPart2() {
        assertEquals("19", Day24.part2(input.lines()))
    }

}