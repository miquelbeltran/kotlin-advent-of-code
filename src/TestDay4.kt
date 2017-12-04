import org.testng.annotations.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestDay4 {
    @Test
    fun `aa bb cc dd ee is valid`() {
        val input = "aa bb cc dd ee"
        assertTrue(solveDay4(input))
    }

    @Test
    fun `aa bb cc dd aaa is valid`() {
        val input = "aa bb cc dd aaa"
        assertTrue(solveDay4(input))
    }

    @Test
    fun `aa bb cc dd aa is not valid`() {
        val input = "aa bb cc dd aa"
        assertFalse(solveDay4(input))
    }

}