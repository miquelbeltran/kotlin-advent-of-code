import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay12 {
    @Test
    fun `part 1 test`() {
        val input = """0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5"""
        assertEquals(6, solveDay12(input))

    }

}

