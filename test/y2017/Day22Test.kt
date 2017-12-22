package y2017

import org.testng.Assert.*
import org.testng.annotations.Test

class Day22Test {
    @Test
    fun `part 1`() {
        val input = """..#
#..
...""".lines()
        assertEquals(Day22.part1(input), "5587")

    }
}