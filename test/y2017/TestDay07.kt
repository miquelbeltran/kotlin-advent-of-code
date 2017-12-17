package y2017

import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay07 {

    @Test
    fun `solve day 7 part 1`() {
        val input = """pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)
"""
        val lines = input.lines().filter { it.isNotEmpty() }.map { parseNode(it) }
        println(lines)
        val top = solveDay7(lines)
        assertEquals("tknk", top.name)

    }

    @Test
    fun `solve day 7 part 2`() {
        val input = """pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)
"""
        val lines = input.lines().filter { it.isNotEmpty() }.map { parseNode(it) }
        val value = solveDay7Part2(lines)
        assertEquals(60, value)

    }

}

