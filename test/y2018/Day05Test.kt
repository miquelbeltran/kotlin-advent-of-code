package y2018

import org.junit.Assert.*
import org.junit.Test

class Day05Test {

    @Test
    fun `part 1 dabAcCaCBAcCcaDA`() {
        val input = "dabAcCaCBAcCcaDA"
        val out = Day05.part1(input)
        assertEquals("10", out)
    }
}