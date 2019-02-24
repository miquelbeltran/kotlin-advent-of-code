package y2018

import common.Day

fun main() {
    Day08.solve()
}

object Day08 : Day {
    override fun part1(input: List<String>): String {
        val list = input.first().split(" ").map { it.toInt() }
        return Node.of(list.iterator()).total.toString()
    }

    override fun part2(input: List<String>): String {
        val list = input.first().split(" ").map { it.toInt() }
        return Node.of(list.iterator()).total2.toString()
    }

    // Totally copied from https://todd.ginsberg.com/post/advent-of-code/2018/day8/
    // I didn't solve this alone
    private data class Node(
            val children: List<Node>,
            val metadata: List<Int>
    ) {
        companion object {
            fun of(values: Iterator<Int>): Node {
                val numChildren = values.next()
                val numMetada = values.next()
                val children = (0 until numChildren).map { Node.of(values) }
                val metadata = (0 until numMetada).map { values.next() }
                return Node(children, metadata)
            }
        }

        val total: Int = metadata.sum() + children.sumBy {
            it.total
        }

        val total2: Int = if (children.isEmpty()) {
            metadata.sum()
        } else {
            metadata.sumBy {
                children.getOrNull(it - 1)?.total2 ?: 0
            }
        }

    }
}