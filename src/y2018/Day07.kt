package y2018

import common.Day
import kotlin.math.max

fun main(args: Array<String>) {
    Day07.solve()
}

object Day07 : Day {

    val timeGlobal = 60
    val workers = 5

    override fun part2(input: List<String>): String {
        val fromStart = "Step ".length
        val toStart = "Step F must be finished before step ".length
        val rules = mutableMapOf<Char, List<Char>>()
        var out = ""
        var pool = mutableSetOf<Char>()
        input.forEach {
            val from = it[fromStart]
            val to = it[toStart]
            rules[to] = (rules[to] ?: emptyList()) + from
            pool.add(from)
            pool.add(to)
        }
        println(rules)

        val poolL = pool.toList().sorted().toMutableList()

        println(poolL)

        var countTime = 0
        val workersTime = Array(workers) { 0 }
        val workersTask = Array(workers) { '.' }

        while (poolL.isNotEmpty() || workersTime.sum() != 0) {

            // for each free worker
            workersTime.forEachIndexed { worker, time ->
                if (time == 0) {
//                    println("free worker $worker")
                    val nextTask = findNextTask(rules, poolL, out)
//                    println("task: $nextTask")
                    if (nextTask != '.') {
                        rules.remove(nextTask)
                        poolL.remove(nextTask)
                        workersTime[worker] = nextTask + timeGlobal - 'A' + 1
                        workersTask[worker] = nextTask
                    }
                }
            }

//            println(workersTime.toList())
            println(workersTask.toList())

            for (i in 0 until workers) {
                workersTime[i] = max(0, workersTime[i] - 1)
                if (workersTime[i] == 0 && workersTask[i] != '.') {
                    out += workersTask[i]
                    workersTask[i] = '.'
                }
            }

            countTime++
        }

        return countTime.toString()
    }

    private fun findNextTask(rules: MutableMap<Char, List<Char>>, poolL: MutableList<Char>, out: String): Char {
        for (current in poolL) {
            if (rules.containsKey(current)) {
                val required = rules[current]!!
                if (out.toCharArray().toList().containsAll(required)) {
                    return current
                }
            } else {
                return current
            }
        }
        return '.'
    }

    override fun part1(input: List<String>): String {
        val fromStart = "Step ".length
        val toStart = "Step F must be finished before step ".length
        val rules = mutableMapOf<Char, List<Char>>()
        var out = ""
        var pool = mutableSetOf<Char>()
        input.forEach {
            val from = it[fromStart]
            val to = it[toStart]
            rules[to] = (rules[to] ?: emptyList()) + from
            pool.add(from)
            pool.add(to)
        }
        println(rules)

        val poolL = pool.toList().sorted().toMutableList()

        println(poolL)

        while (poolL.isNotEmpty()) {
            for (current in poolL) {
                if (rules.containsKey(current)) {
                    val required = rules[current]!!
                    if (out.toCharArray().toList().containsAll(required)) {
                        println("Add $current with rules")
                        out += current
                        rules.remove(current)
                        poolL.remove(current)
                        break
                    }
                } else {
                    println("Add $current no rules")
                    out += current
                    poolL.remove(current)
                    break
                }
            }
            println("pool: $poolL")
            println("rules: $rules")
            println("out $out")
        }
        return out
    }
}