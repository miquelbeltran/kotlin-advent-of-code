package y2017

import org.testng.annotations.Test
import kotlin.test.assertEquals

class Day19Test {

    @Test
    fun `part 1`() {
        val input = """     |
     |  +--+
     A  |  C
 F---|----E|--+
     |  |  |  D
     +B-+  +--+
"""
        assertEquals("ABCDEF", Day19.part1(input.lines()))
    }

}