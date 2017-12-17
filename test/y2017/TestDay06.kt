package y2017

import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay06 {

    @Test
    fun `solve sample`() {
        val input = "0 2 7 0".split(" ").map { it.toInt() }
        assertEquals(5, solveDay6(input))
    }
}
