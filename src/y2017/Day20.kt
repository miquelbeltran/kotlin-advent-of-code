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

    private fun Particle.distance(): Long = p.abs()

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

        // Brute force because I can't math
        repeat(10_000) {
            particles.forEach { it.update() }
        }

        return particles.map { it.distance() }.withIndex().minBy { it.value }!!.index.toString()
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
        val particles = parse(input).toMutableList()

        // brute force because I can't math
        repeat(1_000) {
            particles.forEach { it.update() }

            val positions = mutableSetOf<Vec>()
            val toRemove = mutableSetOf<Vec>()
            particles.forEach {
                // If it is already there, then mark to delete
                if (positions.contains(it.p)) {
                    toRemove.add(it.p)
                }
                // Store each unique position
                positions.add(it.p)
            }
            // delete all particles that match
            toRemove.forEach { pos ->
                particles.removeAll {
                    it.p == pos
                }
            }
        }

        return particles.size.toString()
    }

}



