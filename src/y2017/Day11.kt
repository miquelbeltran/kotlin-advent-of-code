package y2017

import java.io.File

fun solveDay11(input: String): Int {
    var ne = 0
    var n = 0
    var nw = 0
    var se = 0
    var s = 0
    var sw = 0
    var max = 0

    input.split(",")
            .forEach {
                when (it) {
                    "ne" -> {
                        if (sw > 0) {
                            sw--
                        } else if (nw > 0) {
                            nw--
                            n++
                        } else {
                            ne++
                        }
                    }
                    "n" -> {
                        if (s > 0) {
                            s--
                        } else if (se > 0) {
                            se--
                            ne++
                        } else if (sw > 0) {
                            sw--
                            nw++
                        } else {
                            n++
                        }
                    }
                    "nw" -> {
                        if (se > 0) {
                            se--
                        } else if (ne > 0) {
                            ne--
                            n++
                        } else {
                            nw++
                        }
                    }
                    "se" -> {
                        if (nw > 0) {
                            nw--
                        } else if (sw > 0) {
                            sw--
                            s++
                        } else {
                            se++
                        }
                    }
                    "s" -> {
                        if (n > 0) {
                            n--
                        } else if (ne > 0) {
                            ne--
                            se++
                        } else if (nw > 0) {
                            nw--
                            sw++
                        } else {
                            s++
                        }
                    }
                    "sw" -> {
                        if (ne > 0) {
                            ne--
                        } else if (se > 0) {
                            se--
                            s++
                        } else {
                            sw++
                        }
                    }
                    else -> println("Unknown $it")
                }

                max = Math.max(max, ne + n + nw + se + s + sw)
            }

    println("$ne $n $nw $se $s $sw")
    println(max)

    return ne + n + nw + se + s + sw
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
    println(solveDay11(input))
}
