package y2017

import org.testng.annotations.Test
import kotlin.test.assertEquals


class Day20Test {

    @Test
    fun `part 1`() {
        val input = """p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>
p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>"""
        assertEquals("0", Day20.part1(input.lines()))
    }

}