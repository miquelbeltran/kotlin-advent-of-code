package y2018

import common.Day
import java.text.SimpleDateFormat
import java.util.*

object Day04: Day {
    override fun part1(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    enum class Command {
        BEGIN, SLEEP, WAKE
    }

    data class Log(
            val date: Date,
            val guard: String,
            val command: Command
    )

    /**
     * returns: Map with Guard Id and minutes asleep
     */
    fun minutesAsleep(input: List<String>): Map<String, Int> {
        val logs = input.map { it.toLog() }
        val sorted = logs.sortedBy { it.date }

        sorted

        return mapOf()
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
            command = command
    )
}
