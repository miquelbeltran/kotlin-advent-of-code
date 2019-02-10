package y2018

import org.junit.Assert.*
import org.testng.annotations.Test

class Day06Test {
    @Test
    fun `part 1 sample`() {
        val input = """1, 1
1, 6
8, 3
3, 4
5, 5
8, 9""".lines()

        val out = Day06.part1(input)
        assertEquals("17", out)

        val out2 = Day06.part2(input)
       assertEquals("16", out2)
    }


}