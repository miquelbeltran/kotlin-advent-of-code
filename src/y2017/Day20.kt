package y2017

import common.Day
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    Day20.solve()
}

object Day20: Day {

    internal data class Vec(var x: Long, var y: Long, var z: Long)

    internal data class Particle(
            val p: Vec,
            val v: Vec,
            val a: Vec
    )

    private fun Vec.abs(): Long = x.absoluteValue + y.absoluteValue + z.absoluteValue

    private fun Particle.update() {
        v.add(a)
        p.add(v)
    }

    private fun Vec.add(vec: Vec) {
        x += vec.x
        y += vec.y
        z += vec.z
    }

    private fun String.parse(): List<Long> = drop(3).dropLast(1).split(",").map { it.trim().toLong() }

    override fun part1(input: List<String>): String {
        val particles = parse(input)

        // The lowest acceleration will stay closer to the 0,0,0
        return particles.map { it.a.abs() }.withIndex().minBy { it.value }!!.index.toString()
    }

    private fun parse(input: List<String>): List<Particle> {
        return input.map {
            val parts = it.split(", ")
            val pL = parts[0].parse()
            val p = Vec(pL[0], pL[1], pL[2])
            val vL = parts[1].parse()
            val v = Vec(vL[0], vL[1], vL[2])
            val aL = parts[2].parse()
            val a = Vec(aL[0], aL[1], aL[2])
            Particle(p, v, a)
        }
    }

    override fun part2(input: List<String>): String {
        var particles = parse(input)

        // brute force because I can't math
        repeat(1_000) {
            particles.forEach { it.update() }
            particles = particles.groupBy { it.p }
                    .filter { it.value.size == 1 }
                    .map { it.value.first() }
        }

        return particles.size.toString()
    }

}



