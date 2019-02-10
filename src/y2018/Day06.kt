package y2018

import common.Day
import kotlin.math.abs

fun main(args: Array<String>) {
    Day06.solve()
}

object Day06: Day {
    override fun part1(input: List<String>): String {
        val list = input.map {
            val split = it.split(", ")
            val first = split.first().toInt()
            val second = split.last().toInt()
             Pair(first, second)
        }
        val minX = list.minBy { it.first }!!.first
        val minY = list.minBy { it.second }!!.second
        val maxX = list.maxBy { it.first }!!.first
        val maxY = list.maxBy { it.second }!!.second
        println("$minX, $minY, $maxX, $maxY")

        val array = Array(maxX + 1) {
            Array(maxY + 1) { '.' }
        }

        val counter = mutableMapOf<Char, Int>()

        for (iX in 0 .. maxX) {
            for (iY in 0 .. maxY) {
                array[iX][iY] = closest(list, iX, iY, counter)
//                print(array[iX][iY])
            }
//            println()
        }

        // remove infinites
        for (iX in 0 .. maxX) {
            for (iY in 0 .. maxY) {
                if (iX == 0 || iY == 0 || iX == maxX || iY == maxY) {
                    val char = array[iX][iY]
                    counter.remove(char)
                }
            }
        }

//        println(counter)

        return counter.maxBy { it.value }!!.value.toString()
    }

    private fun closest(list: List<Pair<Int, Int>>, iX: Int, iY: Int, counter: MutableMap<Char, Int>): Char {
        var char = '.'
        var minDistance = Int.MAX_VALUE
        list.forEachIndexed { index, pair ->
            val distance = abs(pair.first - iX) + abs(pair.second - iY)
//            if (distance == 0) {
//                return 'A' + index
//            }
            if (distance == minDistance) {
                char = '.'
            } else if (distance < minDistance) {
                char = 'a' + index
                minDistance = distance
            }
        }

        counter[char] = (counter[char] ?: 0) + 1

        return char
    }

    override fun part2(input: List<String>): String {
        val list = input.map {
            val split = it.split(", ")
            val first = split.first().toInt()
            val second = split.last().toInt()
            Pair(first, second)
        }
        val maxX = list.maxBy { it.first }!!.first
        val maxY = list.maxBy { it.second }!!.second
        val array = Array(maxX + 1) {
            Array(maxY + 1) { Int.MAX_VALUE }
        }

        for (iX in 0 .. maxX) {
            for (iY in 0 .. maxY) {
                array[iX][iY] = calculateDistances(list, iX, iY)
                print("${array[iX][iY]} ")
            }
            println()
        }

        return array.sumBy { it.count { it < 10_000 } }.toString()
    }

    private fun calculateDistances(list: List<Pair<Int, Int>>, iX: Int, iY: Int): Int {
        return list.fold(0) { acc, pair ->
            acc + abs(pair.first - iX) + abs(pair.second - iY)
        }
    }

}