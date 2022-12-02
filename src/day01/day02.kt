package day01

import readInputAsString

fun main() {

    data class Elf(val calories: List<Int>)

    fun part1(input: String): Int {
        val elves = input.split("\n\n").map { group -> Elf(group.split("\n").map { it.toInt() }) }
        return elves.maxOf { it.calories.sum() }
    }

    fun part2(input: String): Int {
        val elves = input.split("\n\n").map { group -> Elf(group.split("\n").map { it.toInt() }) }
        return elves.map { it.calories.sum() }.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsString("Day01", "_test")
    println("TestPart1: ${part1(testInput)}")
    println("TestPart1: ${part2(testInput)}")

    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInputAsString("Day01")
    println("Actual Part1: ${part1(input)}")
    println("Actual Part2: ${part2(input)}")
}
