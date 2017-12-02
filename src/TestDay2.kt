import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay2 {
    @Test
    fun `calculate checksum`() {
        val input = """
5 1 9 5
7 5 3
2 4 6 8
"""
        assertEquals(18, solveDay2(input))

    }

}


