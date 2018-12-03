package y2018

import org.testng.Assert.*
import org.testng.annotations.Test

class Day03Test {

    @Test
    fun part1() {
        val input = """#1 @ 1,3: 4x4
#2 @ 3,1: 4x4
#3 @ 5,5: 2x2""".reader().readLines()

        val output = Day03.part1(input)

        assertEquals(output, "4")
    }

    @Test
    fun rectangleParsing() {
        val input = "#1 @ 1,3: 4x4"
        val expected = Rectange(1, 3, 4, 4)
        val actual = input.toRectangle()

        assertEquals(actual, expected)
    }
}