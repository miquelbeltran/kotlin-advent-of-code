import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay17 {
    @Test
    fun `part 1`() {
        val steps = 3
        val out = solveDay17Part1(steps)
        assertEquals(638, out)
    }
}

