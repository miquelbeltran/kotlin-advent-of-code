package y2017

import java.io.File

fun solveDay4(input: String): Boolean {
    val phrase = input.split(" ")
    val uniqueWords = phrase.toSet()
    return phrase.size == uniqueWords.size
}

fun solveDay4Part2(input: String): Boolean {
    val phrase = input.split(" ")
    val uniqueWords = phrase.toSet()
    if (phrase.size != uniqueWords.size) {
        return false
    }
    val maps = phrase.map {
        val map = mutableMapOf<Char, Int>()
        it.forEach {
            map[it] = map[it]?.plus(1) ?: 1
        }
        map
    }
    val uniqueMaps = maps.toSet()
    return phrase.size == uniqueMaps.size

}

fun main(args: Array<String>) {
    val total = File("/Users/miquel/Downloads/input.txt")
            .readLines()
            .filter { solveDay4Part2(it) }
            .count()
    print(total)
}
