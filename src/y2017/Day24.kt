package y2017

import common.Day
import kotlin.math.max

fun main(args: Array<String>) {
    Day24.solve()
}

typealias Port = Pair<Int, Int>

object Day24 : Day {


    override fun part1(input: List<String>): String {
        val ports = input.map { parsePort(it) }
        val scores = mutableMapOf<List<Port>, Int>()
        recursive(0, emptyList(), ports, scores)
        return scores.values.max().toString()
    }

    private fun recursive(initial: Int, current: List<Port>, rest: List<Port>, scores: MutableMap<List<Port>, Int>) {
        rest.forEach {
            if (it.first == initial) {
                val scoreList = current + it
                val score = scoreOf(scoreList)
                scores.put(scoreList, score)
                recursive(it.second, scoreList,rest - it, scores)
            }
            if (it.second == initial) {
                val scoreList = current + it
                val score = scoreOf(scoreList)
                scores.put(scoreList, score)
                recursive(it.first, scoreList,rest - it, scores)
            }
        }
    }

    private fun scoreOf(scoreList: List<Port>): Int {
        return scoreList.map { it.first + it.second }.sum()
    }

    private fun parsePort(it: String): Port {
        val splits = it.split("/").map { it.toInt() }
        return Port(splits[0], splits[1])
    }

    override fun part2(input: List<String>): String {
        val ports = input.map { parsePort(it) }
        val scores = mutableMapOf<List<Port>, Int>()
        recursive(0, emptyList(), ports, scores)
        val max = scores.keys.maxBy { it.size }!!.size
        val longest = scores.keys.filter { it.size == max }
        return scores.filter { it.key in longest }.values.max().toString()
    }
}