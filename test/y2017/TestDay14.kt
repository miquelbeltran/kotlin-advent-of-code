package y2017

import org.testng.annotations.Test
import y2017.solveDay14Part1
import y2017.solveDay14Part2
import kotlin.test.assertEquals

class TestDay14 {
    @Test
    fun `flqrgnkx`() {
        val input = "flqrgnkx"
        assertEquals(8108, solveDay14Part1(input))
    }

    @Test
    fun `flqrgnkx part 2`() {
        val input = "flqrgnkx"
        assertEquals(1242, solveDay14Part2(input))
    }
}