package y2018

import org.testng.Assert.*
import org.testng.annotations.Test

class Day01Test {

    @Test
    fun testPart1(){
        val input = listOf("+1", "-2", "+3", "+1")
        val out = Day01.part1(input)
        assertEquals("3", out)
    }

    @Test
    fun testPart10(){
        val input = listOf("+1", "+1", "+1")
        val out = Day01.part1(input)
        assertEquals("3", out)
    }

    @Test
    fun testPart11(){
        val input = listOf("+1", "+1", "-2")
        val out = Day01.part1(input)
        assertEquals("0", out)
    }

    @Test
    fun testPart12(){
        val input = listOf("-1", "-2", "-3")
        val out = Day01.part1(input)
        assertEquals("-6", out)
    }

    @Test
    fun testPart2(){
        val input = listOf("+1", "-2", "+3", "+1")
        val out = Day01.part2(input)
        assertEquals("2", out)
    }

//    -6, +3, +8, +5, -6 first reaches 5 twice.
//    +7, +7, -2, -7, -4 first reaches 14 twice.

    @Test
    fun testPart20(){
        val input = listOf("+1", "-1")
        val out = Day01.part2(input)
        assertEquals("0", out)
    }

    @Test
    fun testPart21(){
        val input = listOf("+3", "+3", "+4", "-2", "-4")
        val out = Day01.part2(input)
        assertEquals("10", out)
    }
}