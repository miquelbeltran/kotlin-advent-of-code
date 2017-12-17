package y2017

import org.testng.annotations.Test
import y2017.solveDay17Part1
import kotlin.test.assertEquals

class TestDay17 {
    @Test
    fun `part 1`() {
        val steps = 3
        val out = solveDay17Part1(steps)
        assertEquals(638, out)
    }
}

