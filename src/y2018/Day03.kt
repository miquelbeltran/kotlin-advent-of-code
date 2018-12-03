package y2018

import common.Day
import kotlin.math.min

fun main(args: Array<String>) {
    Day03.solve()
}

data class Rectange(
        val x: Int,
        val y: Int,
        val w: Int,
        val h: Int
)

object Day03 : Day {


    override fun part1(input: List<String>): String {

        val rectangles = input.map { it.toRectangle() }
        val maxX = rectangles.maxBy { it.x + it.w }?.let { it.x + it.w } ?: 0
        val maxY = rectangles.maxBy { it.y + it.h }?.let { it.y + it.h } ?: 0
        val workarea = MutableList(maxX) {
            MutableList(maxY) {
                "."
            }
        }

        rectangles.forEachIndexed { index, rectangle ->
            for (i in rectangle.x until rectangle.x + rectangle.w) {
                for (j in rectangle.y until rectangle.y + rectangle.h) {
                    val current = workarea[i][j]
                    if (current == ".") {
                        workarea[i][j] = index.toString()
                    } else {
                        workarea[i][j] = "X"
                    }
                }
            }
        }

        return workarea.sumBy { it.count { it == "X" } }.toString()


    }

    override fun part2(input: List<String>): String {
        val rectangles = input.map { it.toRectangle() }
        val maxX = rectangles.maxBy { it.x + it.w }?.let { it.x + it.w } ?: 0
        val maxY = rectangles.maxBy { it.y + it.h }?.let { it.y + it.h } ?: 0
        val workarea = MutableList(maxX) {
            MutableList(maxY) {
                "."
            }
        }

        rectangles.forEachIndexed { index, rectangle ->
            for (i in rectangle.x until rectangle.x + rectangle.w) {
                for (j in rectangle.y until rectangle.y + rectangle.h) {
                    val current = workarea[i][j]
                    if (current == ".") {
                        workarea[i][j] = index.toString()
                    } else {
                        workarea[i][j] = "X"
                    }
                }
            }
        }

        rectangles.forEachIndexed { index, rectangle ->
            var overlapped = false
            for (i in rectangle.x until rectangle.x + rectangle.w) {
                for (j in rectangle.y until rectangle.y + rectangle.h) {
                    val current = workarea[i][j]
                    if (current == "X") {
                        overlapped = true
                    }
                }
            }
            if (!overlapped) {
                return (index + 1).toString()
            }
        }

        error("Not found")
    }

}

fun String.toRectangle(): Rectange {
    val xPos = indexOf("@") + 2
    var space = indexOf(",", xPos)
    val x = substring(xPos until space).toInt()

    val yPos = space + 1
    space = indexOf(":", xPos)
    val y = substring(yPos until space).toInt()

    val wPos = space + 2
    space = indexOf("x", wPos)
    val w = substring(wPos until space).toInt()

    val hPos = space + 1
    val h = substring(hPos until length).toInt()

    return Rectange(x, y, w, h)
}
