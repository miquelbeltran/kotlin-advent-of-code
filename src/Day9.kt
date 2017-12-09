import java.io.File

fun solveDay9(input: String, part2: Boolean = false): Int {
    var score = 0
    var index = 0
    var openGroup = 0
    var garbage = false
    var garbageCount = 0

    while (index < input.length) {
        val char = input[index]
        when (char) {
            '{' -> {
                if (!garbage) {
                    openGroup++
                } else {
                    garbageCount++
                }
            }
            '}' -> {
                if (!garbage) {
                    score += openGroup
                    openGroup--
                } else {
                    garbageCount++
                }
            }
            '!' -> index++
            '<' -> {
                if (garbage) {
                    garbageCount++
                }
                garbage = true
            }
            '>' -> garbage = false
            else -> if (garbage) {
                garbageCount++
            }
        }
        index++
    }

    if (part2) {
        return garbageCount
    }
    return score
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
    println(solveDay9(input))
    println(solveDay9(input, true))
}
