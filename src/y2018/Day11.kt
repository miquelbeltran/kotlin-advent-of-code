package y2018

import common.Day
import kotlin.math.min

fun main() {
    val input = 7165
    var maxX = 0
    var maxY = 0
    var maxSiz = 0
    var maxp = Int.MIN_VALUE
    val powerlevels = Array(300) {xi ->
        Array(300) { yi ->
            Day201911.powerLevel(xi, yi, input)
        }
    }


    (1 .. 298).forEach { xi ->
        (1 .. 298).forEach { yi ->
            (1 .. min(300 - xi, 300 - yi)).forEach { siz ->

                val pw = (xi until xi + siz).map { xii ->
                    (yi until yi + siz).map { yii ->
                        powerlevels[xii][yii]
                    }.sum()
                }.sum()

                if (pw > maxp) {
                    maxX = xi
                    maxY = yi
                    maxSiz = siz
                    maxp = pw
                    println("$maxX,$maxY,$maxSiz -> $maxp")
                }
            }
        }
    }
    println("$maxX,$maxY,$maxSiz")
}

object Day201911 : Day {
    override fun part1(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun powerLevel(x: Int, y: Int, input: Int): Int {
        //    Find the fuel cell's rack ID, which is its X coordinate plus 10.
        val id = x + 10
//    Begin with a power level of the rack ID times the Y coordinate.
        val initial = id * y
//    Increase the power level by the value of the grid serial number (your puzzle input).
        val withInput = id * y + input
//    Set the power level to itself multiplied by the rack ID.
        val power = withInput * id
//    Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).
        val hundreds = kotlin.runCatching {
            val string = power.toString()
            val last3 = string.takeLast(3)
            val first = last3.first()
            first.toString().toInt()
        }.getOrDefault(0)
//    Subtract 5 from the power level.
        return hundreds - 5
    }

    fun powerLevel(x: Int, y: Int, input: Int, siz: Int): Int {
        return (x until x + siz).map { xi ->
            (y until y + siz).map { yi ->
                powerLevel(xi, yi, input)
            }.sum()
        }.sum()
    }


}