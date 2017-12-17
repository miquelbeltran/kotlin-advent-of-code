package y2017

import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestDay13 {

    @Test
    fun `solve Day 13 Part 1`() {
        val input = """0: 3
1: 2
4: 4
6: 4"""
        assertEquals(24, solveDay13Part1(input.lines()))
    }

    @Test
    fun `position at picosecond`() {
        val input = """0: 3
1: 2
4: 4
6: 4"""
        val map = input.lines().linesToMap()
        assertTrue(isDetected(0, 0, map))
        assertFalse(isDetected(1, 0, map))
        assertTrue(isDetected(4, 0, map))
    }

}

