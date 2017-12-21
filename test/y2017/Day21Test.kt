package y2017

import org.testng.Assert.*
import org.testng.annotations.Test

class Day21Test {
    @Test
    fun `part 1`() {
        val rules = """../.# => ##./#../...
.#./..#/### => #..#/..../..../#..#"""
        assertEquals(Day21.part1(rules.lines()), "12")
    }
}