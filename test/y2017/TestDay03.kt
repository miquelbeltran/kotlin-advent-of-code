package y2017

import org.testng.annotations.Test
import y2017.solveDay3
import kotlin.test.assertEquals

class TestDay03 {
    @Test
    fun `Data from square 1 is carried 0 steps`() {
        assertEquals(0, solveDay3(1))
        assertEquals(1, solveDay3(2))
        assertEquals(2, solveDay3(3))
        assertEquals(1, solveDay3(4))
        assertEquals(2, solveDay3(5))
        assertEquals(1, solveDay3(6))
        assertEquals(2, solveDay3(7))
        assertEquals(1, solveDay3(8))
        assertEquals(2, solveDay3(9))
    }

    @Test
    fun `Data from square 12 is carried 3 steps`() {
        assertEquals(3, solveDay3(12))
    }

    @Test
    fun `Data from square 23 is carried 2 steps`() {
        assertEquals(2, solveDay3(23))
    }

    @Test
    fun `Data from square 1024 is carried 31 steps`() {
        assertEquals(31, solveDay3(1024))
    }
}

