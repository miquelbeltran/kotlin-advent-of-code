import java.io.File

class MazeSolver(input: List<String>, val isPart2: Boolean = false) {
    val lines = input.filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()
    var pos = 0
    var steps = 0

    fun runStep() {
        steps++
        val offset = lines[pos]
        val increment = if (part2Condition()) {
            -1
        } else {
            1
        }
        lines[pos] += increment
        pos += offset
    }

    private fun part2Condition() = isPart2 && lines[pos] >= 3

    fun isExit(): Boolean = pos >= lines.size
}

fun main(args: Array<String>) {
    val lines = File("/Users/miquel/Downloads/input.txt")
            .readLines()
    val solver = MazeSolver(lines, true)
    while (!solver.isExit()) {
        solver.runStep()
    }
    println(solver.steps)
}
