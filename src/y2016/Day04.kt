package y2016

import common.Day

object Day04: Day {
    override fun part1(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun isReal(input: String): Boolean {
        val lastDash = input.indexOfLast { it == '-' }
        val word = input.substring(0 until lastDash).replace("-", "")
        println(word)

        val counts = word.groupingBy { it }.eachCount().entries
        println(counts)

        val freqs = counts.sortedWith (Comparator { o1, o2 ->
            if (o1.value != o2.value) {
                o2.value - o1.value
            } else {
                o1.key - o2.key
            }
        })
        println(freqs)

        val top5 = freqs.take(5).map { it.key }.joinToString(separator = "")
        println(top5)


        TODO()
    }
}