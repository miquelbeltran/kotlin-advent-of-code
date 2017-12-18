package y2016

import org.testng.annotations.Test
import kotlin.test.assertEquals


class Day01Test {
    @Test
    fun testPart1() {
        assertEquals(5, Day01.part1("R2, L3").toInt())
        assertEquals(2, Day01.part1("R2, R2, R2").toInt())
        assertEquals(12, Day01.part1("R5, L5, R5, R3").toInt())
    }

    @Test
    fun testPart2() {
        assertEquals(4, Day01.part2("R8, R4, R4, R8").toInt())
    }

}