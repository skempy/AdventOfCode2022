package day05

import readInputAsPairs

fun main() {

    fun part1(input: List<Pair<String, String>>): Int {
        return 0
    }

    fun part2(input: List<Pair<String, String>>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsPairs("Day05", "_test", ',')
    println("Test Part1: ${part1(testInput)}")
    check(part1(testInput) == 2)

    val input = readInputAsPairs("Day05", breakPoint = ',')
    println("Actual Part1: ${part1(input)}")
    check(part1(input) == 524)

    println("Test Part2: ${part2(testInput)}")
    check(part2(testInput) == 4)
    println("Actual Part2: ${part2(input)}")
    check(part2(input) == 798)
}
