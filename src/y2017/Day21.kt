package y2017

import common.Day

fun main(args: Array<String>) {
    Day21.times = 5
    Day21.solve()
}

object Day21: Day {

    val start = """.#.
..#
###""".lines().map { it.toList() }

    var times = 2

    override fun part1(input: List<String>): String {

        var current = start
        val rules = mutableMapOf<List<List<Char>>, List<List<Char>>>()

        input.forEach {
            //.#./..#/### => #..#/..../..../#..#
            val (inp, output) = it.split(" => ")
            val pattern = inp.split("/").map { it.toList() }
            val out = output.split("/").map { it.toList() }
            rules[pattern] = out
        }

        printM(current)
        repeat(times) {
            if (current.size % 2 == 0) {

                val size = (current.size / 2) * 3
                current = replaceBlocks(current, 2, rules, size)

            } else if (current.size % 3 == 0) {

                val size = (current.size / 3) * 4
                current = replaceBlocks(current, 3, rules, size)
            }

            println("Current:")
            printM(current)
        }

        return current.map { it.count { it == '#' } }.sum().toString()
    }

    private fun replaceBlocks(current: List<List<Char>>, inputBlockSize: Int, rules: MutableMap<List<List<Char>>, List<List<Char>>>, size: Int): List<List<Char>> {
        val new = List(size) { MutableList(size, { '.' }) }
        val inputSize = current.size
        val numBlocks = inputSize / inputBlockSize
        val outputBlockSize = inputBlockSize + 1
        val outputSize = new.size
        val numOutputBlocks = outputSize / outputBlockSize

        // for my own sanity
        assert(numBlocks == numOutputBlocks)

        for (blockX in 0 until numBlocks)
            for (blockY in 0 until numBlocks) {

                val inPosX = blockX * inputBlockSize
                val inPosY = blockY * inputBlockSize

                val replace = matchRule(rules, current.slice(inPosX until (inputBlockSize + inPosX)).map { it.slice(inPosY until (inputBlockSize + inPosY)) })

                val outPosX = blockX * outputBlockSize
                val outPosY = blockY * outputBlockSize

                for (i in 0 until replace.size)
                    for (j in 0 until replace.size) {
                        new[i + outPosX][j + outPosY] = replace[i][j]
                    }
            }

        return new
    }

    private fun matchRule(rules: MutableMap<List<List<Char>>, List<List<Char>>>, map: List<List<Char>>): List<List<Char>> {
        println("Input:")
        printM(map)
        var pattern = map
        var rotations = 0
        while (true) {
            val out = rules[pattern]
            if (out != null) {
                println("Found pattern:")
                printM(out)
                return out
            }
            // rotate
            val newPattern = List(pattern.size) { MutableList(pattern.size) { '.' }}

            // manual rotation (clockwise)
            if (pattern.size == 2) {
                newPattern[0][0] = pattern[1][0]
                newPattern[0][1] = pattern[0][0]
                newPattern[1][0] = pattern[1][1]
                newPattern[1][1] = pattern[0][1]
                rotations++
            }

            // manual rotation (clockwise)
            if (pattern.size == 3) {
                newPattern[0][0] = pattern[2][0]
                newPattern[0][1] = pattern[1][0]
                newPattern[0][2] = pattern[0][0]

                newPattern[1][0] = pattern[2][1]
                newPattern[1][1] = pattern[1][1]
                newPattern[1][2] = pattern[0][1]

                newPattern[2][0] = pattern[2][2]
                newPattern[2][1] = pattern[1][2]
                newPattern[2][2] = pattern[0][2]
                rotations++
            }
            pattern = newPattern

            // flip
            if (rotations == 4) {
                rotations = 0
                pattern = pattern.map { it.asReversed() }
            }
        }
    }

    private fun printM(new: List<List<Char>>) {
        new.forEach {
            println(it.joinToString(separator = ""))
        }
        println()
    }

    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
