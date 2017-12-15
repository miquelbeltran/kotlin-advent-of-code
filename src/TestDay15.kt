import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay15 {
    @Test
    fun `Day 15 Part 1`() {
        val genA = 65L
        val genB = 8921L
        val count = solveDay15Part1(genA, genB)
        assertEquals(588, count)
    }

    @Test
    fun `Day 15 Part 2`() {
        val genA = 65L
        val genB = 8921L
        val count = solveDay15Part2(genA, genB)
        assertEquals(309, count)
    }
}

