package y2018

import org.junit.Assert.*
import org.junit.Test

class Day07Test {
    @Test
    fun part1() {
        val input = """Step C must be finished before step A can begin.
Step C must be finished before step F can begin.
Step A must be finished before step B can begin.
Step A must be finished before step D can begin.
Step B must be finished before step E can begin.
Step D must be finished before step E can begin.
Step F must be finished before step E can begin.""".lines()
        val out = Day07.part1(input)
        assertEquals("CABDFE", out)
    }
}