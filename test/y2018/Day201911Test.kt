package y2018

import org.junit.Test

import org.junit.Assert.*

class Day201911Test {

    @Test
    fun powerLevel() {
        assertEquals(4, Day201911.powerLevel(3,5, 8))
        assertEquals(-5, Day201911.powerLevel(122,79, 57))
        assertEquals(0, Day201911.powerLevel(217,196, 39))
        assertEquals(4, Day201911.powerLevel(101,153, 71))
    }

    @Test
    fun powerLevel3x3() {
        assertEquals(29, Day201911.powerLevel(33,45, 18, 3))
        assertEquals(30, Day201911.powerLevel(21,61, 42, 3))
    }
}