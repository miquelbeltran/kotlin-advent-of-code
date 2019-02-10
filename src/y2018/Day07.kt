package y2018

import common.Day

fun main(args: Array<String>) {
    Day07.solve()
}

object Day07: Day {
    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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