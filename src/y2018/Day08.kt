package y2018

import common.Day

fun main() {
    Day08.solve()
}

object Day08 : Day {
    override fun part1(input: List<String>): String {
        val list = input.first().split(" ").map { it.toInt() }
//        println(list)

//        return sumMetadata(list).toString()
//        return exploreChild(list).first.toString()
        return iterative(list).toString()
    }

    enum class Node {
        N_CHILDREN,
        N_METADATA,
        METADATA
    }

    private fun iterative(list: List<Int>): Int {
        var current = Node.N_CHILDREN
        var total = 0
        val childrenStack = mutableListOf<Int>()
        val metadataStack = mutableListOf<Int>()
        for (index in 0 until list.size) {

            println("current: $current")

            when (current) {
                Node.N_CHILDREN -> {
                    childrenStack.add(list[index])
                    current = Node.N_METADATA
                }
                Node.N_METADATA -> {
                    metadataStack.add(list[index])
                    if (childrenStack.last() == 0) {
                        // has no children
                        current = Node.METADATA
                        childrenStack.removeAt(childrenStack.size - 1)
                    } else {
                        childrenStack[childrenStack.size - 1]--
                        current = Node.N_CHILDREN
                    }
                }
                Node.METADATA -> {
                    total += list[index]
                    metadataStack[metadataStack.size - 1]--
                    if (metadataStack.last() == 0) {
                        metadataStack.removeAt(metadataStack.size - 1)
                        if (childrenStack.isNotEmpty()) {
                            if (childrenStack.last() != 0) {
                                current = Node.N_CHILDREN
                                childrenStack[childrenStack.size - 1]--
                            } else {
                                childrenStack.removeAt(childrenStack.size - 1)
                            }
                        }
                    }
                }
            }

//            println("index: $index")
            println("item: ${list[index]}")
            println("childrenStack: $childrenStack")
            println("metadataStack: $metadataStack")
            println()
        }

        return total
    }

    private fun exploreChild(list: List<Int>): Pair<Int, Int> {

        println(list.take(10))

        val childNodes = list[0]
        val metadataEntries = list[1]

        if (childNodes == 0) {
            return Pair(list.slice(2 until 2 + metadataEntries).sum(), 2 + metadataEntries)
        }

        var index = 0
        var total = 0

        repeat(childNodes) {
            val pair = exploreChild(list.drop(2 + index))
//            println(pair)
            total += pair.first
            index += pair.second
        }

        total += list.takeLast(metadataEntries).sum()

        return Pair(total, index)
    }

    private fun sumMetadata(list: List<Int>): Int {

        println(list)

        if (list.isEmpty()) return 0

        val childNodes = list[0]
        val metadataEntries = list[1]
        var metadataThisNode = list.takeLast(metadataEntries).sum()

        println("childNodes $childNodes")
        println("metadataEntries $metadataEntries")
        println("metadataThisNode $metadataThisNode")

        val children = list.subList(2, list.size - metadataEntries)
//        println("Children: ${children.size}")

        var index = 0
        repeat(childNodes) {
            println("current child $it")
//            println("index $index children $children")
            val size = calculateChildSize(children, index)
//            println("size $size")
//            println("size $size index $index children ${children.size}")
            metadataThisNode += sumMetadata(children.subList(index, index + size))
            index += size
//            println("index $index")
        }

        return metadataThisNode
    }

    private fun calculateChildSize(list: List<Int>, index: Int): Int {
//        println("calculateChildSize")

//        println(list)
//        println(index)

        val childNodes = list[index]
        val metadataEntries = list[index + 1]

        println("childSize childNodes $childNodes")
        println("childSize metadataEntries $metadataEntries")

        if (childNodes == 0) {
            return 2 + metadataEntries
        } else {
            return 2 + metadataEntries + calculateChildSize(list, index + 2)
        }
    }

    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}