package day03

import readInputAsList

fun main() {

    var priority = 1
    val priorities: Map<Char, Int> = (('a'..'z') + ('A'..'Z')).associateWith { priority++ }

    fun part1(input: List<String>): Int {
        return input
            .map { it.chunked(it.length / 2) }
            .map { Pair(it[0].toSet(), it[1].toSet()) }
            .sumOf { priorities[(it.first intersect it.second).single()]!! }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toSet() }
            .chunked(3)
            .sumOf { priorities[(it[0] intersect it[1] intersect it[2]).single()]!! }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsList("Day03", "_test")
    println("Test Part1: ${part1(testInput)}")
    println("Test Part2: ${part2(testInput)}")

    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInputAsList("Day03")
    println("Actual Part1: ${part1(input)}")
    println("Actual Part2: ${part2(input)}")

    check(part1(input) == 7811)
    check(part2(input) == 2639)
}

