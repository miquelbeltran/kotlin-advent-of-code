package y2017

import org.testng.annotations.Test
import y2017.solveDay11
import kotlin.test.assertEquals

/*
ne,ne,ne is 3 steps away.
ne,ne,sw,sw is 0 steps away (back where you started).
ne,ne,s,s is 2 steps away (se,se).
se,sw,se,sw,sw is 3 steps away (s,s,sw).
 */

class TestDay11 {
    @Test
    fun `ne,ne,ne is 3 steps away`() {
        val input = "ne,ne,ne"
        assertEquals(3, solveDay11(input))
    }

    @Test
    fun `ne,ne,sw,sw is 0 steps away`() {
        val input = "ne,ne,sw,sw"
        assertEquals(0, solveDay11(input))
    }

    @Test
    fun `ne,ne,s,s is 2`() {
        val input = "ne,ne,s,s"
        assertEquals(2, solveDay11(input))
    }

    @Test
    fun `se,sw,se,sw,sw is 3`() {
        val input = "se,sw,se,sw,sw"
        assertEquals(3, solveDay11(input))
    }
}

