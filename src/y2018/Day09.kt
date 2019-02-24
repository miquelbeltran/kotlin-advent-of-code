package y2018

import common.Day

fun main() {
    Day09.solve()
}

object Day09: Day {
    override fun part1(input: List<String>): String {
        val split = input.first().split(" ")
        val players = split.first().toInt()
        val last = split[6].toInt()
        println("players $players")
        println("last $last")

        val list = mutableListOf<Int>()
        list.add(0)
        var index = 0
        var player = 0
        var scores = Array(players) { 0 }

        for (marble in 1 .. last) {
            if (marble % 23 == 0) {
                index -= 7
                if (index < 0) {
                    index = list.size + index
                }
                scores[player] += list[index]
                scores[player] += marble
                list.removeAt(index)
            } else {
                if (list.size == 1) {
                    index = 1
                } else if (list.size == 2) {
                    index = 1
                } else {
                    index = (index + 2)
                    if (index > list.size) {
                        index = 1
                    }
                }
//                println(index)
                list.add(index, marble)
            }
//            println(list)
            player += 1
            player %= players
        }

        return scores.max().toString()
    }

    override fun part2(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}