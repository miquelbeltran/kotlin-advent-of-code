package y2018

import org.junit.Assert.*
import org.testng.annotations.Test

class Day09Test {

    @Test
    fun example() {
        val out = Day09.part1("9 players; last marble is worth 25 points")
        assertEquals("32", out)
    }

    @Test
    fun test1() {
//        : high score is 8317
//        13 players; last marble is worth 7999 points: high score is 146373
//        17 players; last marble is worth 1104 points: high score is 2764
//        21 players; last marble is worth 6111 points: high score is 54718
//        30 players; last marble is worth 5807 points: high score is 37305

        val out = Day09.part1("10 players; last marble is worth 1618 points")
        assertEquals("8317", out)
    }
}