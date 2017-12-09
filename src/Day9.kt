import java.io.File

fun solveDay9(input: String): Int {
    var score = 0
    var index = 0
    var openGroup = 0
    var garbage = false

    while (index < input.length) {
        val char = input[index]
        when (char) {
            '{' -> {
                if (!garbage)
                    openGroup++
            }
            '}' -> {
                if (!garbage) {
                    score += openGroup
                    openGroup--
                }
            }
            '!' -> index++
            '<' -> garbage = true
            '>' -> garbage = false
        }
        index++
    }

    return score
}

fun main(args: Array<String>) {
    val input = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .first()
    println(solveDay9(input))
}
