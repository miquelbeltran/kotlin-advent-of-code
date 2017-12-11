import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay10 {
    @Test
    fun `0, 1, 2, 3, 4, and were given input lengths of 3, 4, 1, 5`() {
        val list = mutableListOf(0, 1, 2, 3, 4)
        val lengths = listOf(3, 4, 1, 5)

        assertEquals(12, solveDay10(list, lengths))
        
    }

    @Test
    fun `part 2 example empty string`() {
        val list = (0 until 256).toMutableList()
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", solveDay10Part2(list, ""))

    }

    @Test
    fun `calculate xor`() {
        val input = mutableListOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)
        assertEquals(mutableListOf(64), calculateXor(input))

    }

    @Test
    fun `AoC 2017`() {
        val input = "AoC 2017"
        val list = (0 until 256).toMutableList()
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", solveDay10Part2(list, input))
    }
}

