package y2018

import common.Day
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    Day04.solve()
}

object Day04 : Day {
    override fun part1(input: List<String>): String {
        val all = bestMinute(input)
        val best = all.maxBy { it.value.values.sum() }!!.key
        println("Best: $best")
        val bestMinute = all[best]!!.maxBy { it.value }!!.key
        println("Best Minute: $bestMinute")
        return (best.substring(1).toInt() * bestMinute).toString()
    }

    override fun part2(input: List<String>): String {
        val all = bestMinute(input)
        val best = all.maxBy { it.value.maxBy { it.value }!!.value }!!.key
        println("Best: $best")
        val bestMinute = all[best]!!.maxBy { it.value }!!.key
        println("Best Minute: $bestMinute")
        return (best.substring(1).toInt() * bestMinute).toString()
    }

    enum class Command {
        BEGIN, SLEEP, WAKE
    }

    data class Log(
            val date: Date,
            val guard: String,
            val command: Command,
            val minute: Int
    )

    fun bestMinute(input: List<String>): MutableMap<String, MutableMap<Int, Int>> {
        val logs = input.map { it.toLog() }
        val sorted = logs.sortedBy { it.date }
        val guardsTotals = mutableMapOf<String, MutableMap<Int, Int>>()

        var currentGuard = ""
        var minute: Int = 0

        for (log in sorted) {
            when (log.command) {
                Command.BEGIN -> {
                    currentGuard = log.guard
                }
                Command.SLEEP -> {
                    minute = log.minute
                }
                Command.WAKE -> {
                    val map = guardsTotals[currentGuard] ?: mutableMapOf()
                    for (m in minute..log.minute) {
                        map[m] = (map[m] ?: 0) + 1
                    }
//                    println(map)
                    guardsTotals[currentGuard] = map
                }
            }
        }

        return guardsTotals
    }
}

val parser = SimpleDateFormat("yyyy-MM-dd HH:mm")

fun String.toLog(): Day04.Log {
    val commandString = substring(18 until length)
    val command = when {
        commandString.contains("Guard") -> Day04.Command.BEGIN
        commandString.contains("falls") -> Day04.Command.SLEEP
        commandString.contains("wakes") -> Day04.Command.WAKE
        else -> error("Cannot parse $this")
    }
    val guard = if (command == Day04.Command.BEGIN) {
        val start = indexOf("#")
        substring(start until indexOf(" ", startIndex = start))
    } else ""

    return Day04.Log(
            date = parser.parse(substring(1 until 17)),
            guard = guard,
            command = command,
            minute = substring(15 until 17).toInt()
    )
}
