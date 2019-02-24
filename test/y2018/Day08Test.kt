package y2018

import org.junit.Assert.*
import org.testng.annotations.Test

class Day08Test {

    @Test
    fun part1() {
        val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"
        val out = Day08.part1(input)
        assertEquals("138", out)
    }

    @Test
    fun partTest() {
        val input = "0 1 99"
        val out = Day08.part1(input)
        assertEquals("99", out)
    }

    @Test
    fun partTest2() {
        val input = "1 1 0 1 99 1"
        val out = Day08.part1(input)
        assertEquals("100", out)
    }

    @Test
    fun partTest3() {
        val input = "2 1 0 1 99 0 1 1 1"
        val out = Day08.part1(input)
        assertEquals("101", out)
    }

    @Test
    fun part2() {
        val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"
        val out = Day08.part2(input)
        assertEquals("66", out)
    }
}