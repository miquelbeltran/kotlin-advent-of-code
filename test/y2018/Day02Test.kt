package y2018

import org.testng.Assert.*
import org.testng.annotations.Test

class Day02Test {

    //    abcdef contains no letters that appear exactly two or three times.
    @Test
    fun abcdef() {
        val input = "abcdef"
        val out: Pair<Int, Int> = Day02.count(input)
        assertEquals(out, Pair(0, 0))
    }

    //    bababc contains two a and three b, so it counts for both.
    @Test
    fun bababc() {
        val input = "bababc"
        val out: Pair<Int, Int> = Day02.count(input)
        assertEquals(out, Pair(1, 1))
    }

    //    abbcde contains two b, but no letter appears exactly three times.
    @Test
    fun abbcde() {
        val input = "abbcde"
        val out: Pair<Int, Int> = Day02.count(input)
        assertEquals(out, Pair(1, 0))
    }

    //    abcccd contains three c, but no letter appears exactly two times.
    @Test
    fun abcccd() {
        val input = "abcccd"
        val out: Pair<Int, Int> = Day02.count(input)
        assertEquals(out, Pair(0, 1))
    }

    //    aabcdd contains two a and two d, but it only counts once.
    @Test
    fun aabcdd() {
        val input = "aabcdd"
        val out: Pair<Int, Int> = Day02.count(input)
        assertEquals(out, Pair(1, 0))
    }

    //    abcdee contains two e.
    @Test
    fun abcdee() {
        val input = "abcdee"
        val out: Pair<Int, Int> = Day02.count(input)
        assertEquals(out, Pair(1, 0))
    }

    //    ababab contains three a and three b, but it only counts once.
    @Test
    fun ababab() {
        val input = "ababab"
        val out: Pair<Int, Int> = Day02.count(input)
        assertEquals(out, Pair(0, 1))
    }

    @Test
    fun part2() {
        val input = """abcde
fghij
klmno
pqrst
fguij
axcye
wvxyz""".reader().readLines()

        val out = Day02.part2(input)
        assertEquals(out, "fgij")
    }
}