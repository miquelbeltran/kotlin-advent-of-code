package y2016

import org.testng.Assert.*
import org.testng.annotations.Test

class Day04Test {

    @Test
    fun isRealOrNot() {
        val input = "aaaaa-bbb-z-y-x-123[abxyz]"
        assertTrue(Day04.isReal(input))
    }
}