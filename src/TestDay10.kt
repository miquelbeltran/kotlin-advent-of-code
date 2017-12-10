import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay10 {
    @Test
    fun `0, 1, 2, 3, 4, and were given input lengths of 3, 4, 1, 5`() {
        val list = mutableListOf(0, 1, 2, 3, 4)
        val lengths = listOf(3, 4, 1, 5)

        assertEquals(12, solveDay10(list, lengths))
        
    }

}

