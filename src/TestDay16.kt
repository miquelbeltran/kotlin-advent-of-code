import org.testng.annotations.Test
import kotlin.coroutines.experimental.suspendCoroutine
import kotlin.test.assertEquals

class TestDay16 {
    @Test
    fun `spin`() {
        // Spin, written sX, makes X programs move from the end to the front,
        // but maintain their order otherwise. (For example, s3 on abcde produces cdeab).
        val op = "s3"
        val input = "abcde"
        assertEquals("cdeab", consume(op, input))

    }

    @Test
    fun `spin 1`() {
        val op = "s1"
        val input = "abcde"
        assertEquals("eabcd", consume(op, input))

    }

    @Test
    fun `exchange`() {
        // Exchange, written xA/B, makes the programs at positions A and B swap places.
        val op = "x3/4"
        val input = "eabcd"
        assertEquals("eabdc", consume(op, input))
    }

    @Test
    fun `partner`() {
        // Partner, written pA/B, makes the programs named A and B swap places.
        val op = "pe/b"
        val input = "eabdc"
        assertEquals("baedc", consume(op, input))
    }

    @Test
    fun `full program`() {
        val ops = "s1,x3/4,pe/b"
        val input = "abcde"
        val output = "baedc"
        assertEquals(output, solveDay16Part1(ops, input))
    }

}
