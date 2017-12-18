package y2016

import org.testng.annotations.Test
import kotlin.test.assertEquals

internal class Day02Test {

    val input = """ULL
RRDDD
LURDL
UUUUD"""

    @Test
    fun part1() {
        assertEquals("1985", Day02.part1(input.lines()))
    }

    @Test
    fun part2() {
        assertEquals("5DB3", Day02.part2(input.lines()))
    }

}