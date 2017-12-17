package y2017

fun solveDay14Part1(input: String): Int {
    val hashes = (0 until 128).map {
        val list = (0 until 256).toMutableList()
        solveDay10Part2(list, "$input-$it")
    }
    println(hashes)
    val total = hashes.map {
        it.map {
            it.toString().toInt(16).toString(2).filter { it == '1' }.count()
        }.sum()
    }.sum()
    return total
}

fun solveDay14Part2(input: String): Int {
    val hashes = (0 until 128).map {
        val list = (0 until 256).toMutableList()
        solveDay10Part2(list, "$input-$it")
    }
    val matrix = hashes.map {
        it.flatMap {
            it.toString().toInt(16).toString(2).padStart(4, '0').map { it.toString().replace("1", "X") }
        }.toMutableList()
    }.toMutableList()
    println(matrix)
    var region = 1
    for (i in 0 until 128) {
        for (j in 0 until 128) {
            val cur = matrix[i][j]
            if (cur == "X") {
                // start exploration
                explore(matrix, i, j, region)
                region++
            }
        }
    }

    return region - 1
}

fun explore(matrix: MutableList<MutableList<String>>, i: Int, j: Int, region: Int) {
    matrix[i][j] = region.toString()
    if (i + 1 < 128) {
        val next = matrix[i + 1][j]
        if (next == "X") {
            explore(matrix, i + 1, j, region)
        }
    }
    if (j + 1 < 128) {
        val next = matrix[i][j + 1]
        if (next == "X") {
            explore(matrix, i, j + 1, region)
        }
    }
    if (i - 1 >= 0) {
        val next = matrix[i - 1][j]
        if (next == "X") {
            explore(matrix, i - 1, j, region)
        }
    }
    if (j - 1 >= 0) {
        val next = matrix[i][j - 1]
        if (next == "X") {
            explore(matrix, i, j - 1, region)
        }
    }
}

fun main(args: Array<String>) {
    println(solveDay14Part1("wenycdww"))
    println(solveDay14Part2("wenycdww"))
}
