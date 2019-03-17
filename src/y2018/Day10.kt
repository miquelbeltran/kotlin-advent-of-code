package y2018

import common.Day
import kotlin.math.min

fun main() {
    Day10.solve()
}

object Day10: Day {
    override fun part1(input: List<String>): String {
//        println(input)
        val pairs = input.map {
            val x = it.substringAfter("position=<").substringBefore(",").trim().toInt()
            val y = it.substringAfter(",").substringBefore(">").trim().toInt()
            val xv = it.substringAfter("velocity=<").substringBefore(",").trim().toInt()
            val yv = it.substringAfterLast(",").substringBefore(">").trim().toInt()
            Pair(Pair(x, y), Pair(xv, yv))
        }
//        println(pairs)

        var minDiffX = Int.MAX_VALUE
        var minDiffY = Int.MAX_VALUE
        var times = 0

        while (true) {
            val current = calculate(pairs, times)

            val maxX = current.maxBy { it.first }!!.first
            val minX = current.minBy { it.first }!!.first
            val maxY = current.maxBy { it.second }!!.second
            val minY = current.minBy { it.second }!!.second

            val diffX = maxX - minX
            val diffY = maxY - minY

//            println("$minX, $minY, $maxX, $maxY")
//            println("$diffX, $diffY")

            minDiffX = min(diffX, minDiffX)
            minDiffY = min(diffY, minDiffY)

            if (minDiffX != diffX) {
                break
            }

            times++
        }

        times--
        val final = calculate(pairs, times)

        val maxX = final.maxBy { it.first }!!.first
        val minX = final.minBy { it.first }!!.first
        val maxY = final.maxBy { it.second }!!.second
        val minY = final.minBy { it.second }!!.second

        val array = Array(maxX - minX + 1) {
            Array(maxY - minY + 1) { '.'}
        }

        final.forEach {
            array[it.first - minX][it.second - minY] = '#'
        }

        for (i in array) {
            for (j in i) {
                print(j)
            }
            println()
        }



        return times.toString()
    }

    private fun calculate(pairs: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>, times: Int): List<Pair<Int, Int>> {
        return pairs.map {
            val newX = it.first.first + it.second.first * times
            val newY = it.first.second + it.second.second * times
            Pair(newX, newY)
        }
    }

    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}