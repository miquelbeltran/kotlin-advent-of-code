import java.io.File

data class Node(
        val name: String,
        val weight: Int,
        val children: List<String> = emptyList(),
        val nodes: MutableList<Node> = mutableListOf(),
        var total: Int = 0
)

fun parseNode(line: String): Node {
    val splits = line.split(" ")
    val children = if (splits.size == 2) {
        emptyList<String>()
    } else {
        splits.drop(3).map { it.replace(",", "") }
    }
    return Node(
            name = splits[0],
            weight = splits[1].replace("(", "").replace(")", "").toInt(),
            children = children
    )
}

fun solveDay7(lines: List<Node>): Node {
    val allChildren = lines.flatMap { it.children }
    return lines.find {
        !allChildren.contains(it.name)
    }!!
}

fun solveDay7Part2(lines: List<Node>): Int {
    // Find the top node
    val top = solveDay7(lines)
    // Build a tree from the top node
    buildTree(top, lines)
    // Calculate the total weights
    calculateWeights(top, lines)
    // Find the one which is wrong
    return checkEquilibrium(top)
}

fun checkEquilibrium(top: Node, corrected: Int = 0): Int {
    // Group weights by frequency
    val ws = top.nodes.groupingBy { it.total }.eachCount()

    // Only 1 group == balanced, found solution
    if (ws.size == 1) {
        return corrected
    }

    // Not balanced, see who is wrong
    // Find the wrong weight one
    val wrongWeight = ws.filterValues { it == 1 }.keys.take(1).first()
    // Take the good weight value
    val goodWeight = ws.filterValues { it > 1 }.keys.take(1).first()
    // Find the wrong node
    val wrong = top.nodes.find { it.total == wrongWeight }!!
    // Calculate the right corrected weight for that node
    val newWeight = wrong.weight - wrongWeight + goodWeight

    // See if the children can still be fixed
    return checkEquilibrium(wrong, newWeight)
}

fun buildTree(top: Node, lines: List<Node>) {
    top.children.forEach { childName ->
        val child = lines.find { it.name == childName }!!
        top.nodes.add(child)
        buildTree(child, lines)
    }
}

fun calculateWeights(top: Node, lines: List<Node>) {
    top.total = top.weight + top.nodes
            .map { child ->
                calculateWeights(child, lines)
                child.total
            }
            .sum()
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .filter { it.isNotEmpty() }
            .map { parseNode(it) }
    println(solveDay7(input).name)
    println(solveDay7Part2(input))
}

