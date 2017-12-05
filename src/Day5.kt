import java.io.File

class MazeSolver(input: List<String>) {
    val lines = input.filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()
    var pos = 0
    var steps = 0

    fun runStep() {
        steps++
        val offset = lines[pos]
        lines[pos]++
        pos += offset
    }

    fun isExit(): Boolean = pos >= lines.size
}

fun main(args: Array<String>) {
    val lines = File("/Users/miquel/Downloads/input.txt")
            .readLines()
    val solver = MazeSolver(lines)
    while (!solver.isExit()) {
        solver.runStep()
    }
    println(solver.steps)
}
